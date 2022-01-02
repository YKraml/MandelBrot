package main.java.model;

import main.java.main.History;
import main.java.main.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Graph {

    private static final Color[] GRADIANT = new Color[]{
            new Color(66, 30, 15),
            new Color(25, 7, 26),
            new Color(9, 1, 47),
            new Color(4, 4, 73),
            new Color(0, 7, 100),
            new Color(12, 44, 138),
            new Color(24, 82, 177),
            new Color(57, 125, 209),
            new Color(134, 181, 229),
            new Color(211, 236, 248),
            new Color(241, 233, 191),
            new Color(248, 201, 95),
            new Color(255, 170, 0),
            new Color(204, 128, 0),
            new Color(153, 87, 0),
            new Color(106, 52, 3)
    };

    private int getDepthFrom(ImaginaryNumber startNumber, ImaginaryNumber c) {

        ImaginaryNumber current = startNumber;
        for (int k = 0; k < Settings.getMaxDepth(); k++) {
            current = MyMath.add(MyMath.mult(current, current), c);

            if (current.getLength().doubleValue() < Math.pow(10, -10)) {
                return k;
            }

            if (current.getLength().intValue() > 2) {
                return k;
            }
        }

        return -1;
    }

    public Color getColorFrom(ImaginaryNumber startNumber, ImaginaryNumber c) {
        int depth = getDepthFrom(c, startNumber);
        return depth < 0 ? Color.BLACK : GRADIANT[(depth + 2) % GRADIANT.length];
    }

    public synchronized void calcImage(BufferedImage image, BigDecimal zoomX, BigDecimal zoomY, BigDecimal worldXOffset, BigDecimal worldYOffset) {

        AtomicBoolean finishedDrawing = new AtomicBoolean(true);

        Set<Point> bigPixels = new HashSet<>();
        for (int i = 0; i < image.getWidth() / Settings.getRATIO() + 1; i++) {
            if (Settings.isChanged()) {
                return;
            }
            for (int j = 0; j < image.getHeight() / Settings.getRATIO() + 1; j++) {
                bigPixels.add(new Point(i * Settings.getRATIO(), j * Settings.getRATIO()));
            }
        }

        //Male BigPixels
        bigPixels.forEach(screenPoint -> {
            if (Settings.isChanged()) {
                finishedDrawing.set(false);
                return;
            }

            MyPoint worldPoint = MyMath.calcScreenToWorld(new MyPoint(screenPoint.x + Settings.getRATIO() * 0.5, screenPoint.y+ Settings.getRATIO() * 0.5));
            Color color = Settings.isCVariable() ? getColorFrom(worldPoint.toImaginaryNumber(), Settings.getNonVariable()) : getColorFrom(Settings.getNonVariable(), worldPoint.toImaginaryNumber());

            for (int i = 0; i < Settings.getRATIO(); i++) {
                for (int j = 0; j < Settings.getRATIO(); j++) {
                    int pixelX = screenPoint.x + i;
                    int pixelY = screenPoint.y + j;
                    if (pixelX < image.getWidth() && pixelY < image.getHeight() && pixelX >= 0 && pixelY >= 0) {
                        image.setRGB(pixelX, pixelY, color.getRGB());
                    }
                }
            }
        });

        //Finde BigPixels, die neu gemalt werden müssen.
        Set<Point> bigPixelsToBeRedrawn = bigPixels.stream().filter(screenPoint -> {
            Point[] bigPixelNeighbors = new Point[]{
                    new Point(screenPoint.x - Settings.getRATIO(), screenPoint.y - Settings.getRATIO()), new Point(screenPoint.x, screenPoint.y - Settings.getRATIO()), new Point(screenPoint.x + Settings.getRATIO(), screenPoint.y - Settings.getRATIO()),
                    new Point(screenPoint.x - Settings.getRATIO(), screenPoint.y), new Point(screenPoint.x, screenPoint.y), new Point(screenPoint.x + Settings.getRATIO(), screenPoint.y),
                    new Point(screenPoint.x - Settings.getRATIO(), screenPoint.y + Settings.getRATIO()), new Point(screenPoint.x, screenPoint.y + Settings.getRATIO()), new Point(screenPoint.x + Settings.getRATIO(), screenPoint.y + Settings.getRATIO())
            };

            Set<Integer> neighboringColors = new LinkedHashSet<>();
            if (screenPoint.x < image.getWidth() && screenPoint.y < image.getHeight() && screenPoint.x >= 0 && screenPoint.y >= 0) {
                neighboringColors.add(image.getRGB(screenPoint.x, screenPoint.y));
            }
            for (Point p : bigPixelNeighbors) {
                if (image.getWidth() > p.x && image.getHeight() > p.y && p.x >= 0 && p.y >= 0) {
                    int foundColor = image.getRGB(p.x, p.y);
                    if (!neighboringColors.contains(foundColor)) {
                        return true;
                    }
                    neighboringColors.add(foundColor);
                }
            }

            return false;
        }).collect(Collectors.toSet());


        //Male gewisse BigPixels neu.
        bigPixelsToBeRedrawn.parallelStream().forEach(bigPixel -> {
            if (Settings.isChanged()) {
                finishedDrawing.set(false);
                return;
            }

            for (int k = 0; k < Settings.getRATIO() * Settings.getRATIO(); k++) {
                Point screenPoint = new Point(bigPixel.x + k / Settings.getRATIO(), bigPixel.y + k % Settings.getRATIO());
                MyPoint worldPoint = MyMath.calcScreenToWorld(MyPoint.valueOf(screenPoint));
                Color color = Settings.isCVariable() ? getColorFrom(worldPoint.toImaginaryNumber(), Settings.getNonVariable()) : getColorFrom(Settings.getNonVariable(), worldPoint.toImaginaryNumber());

                if (screenPoint.x < image.getWidth() && screenPoint.y < image.getHeight() && screenPoint.x >= 0 && screenPoint.y >= 0) {
                    image.setRGB(screenPoint.x, screenPoint.y, color.getRGB());
                }
            }
        });

        if (finishedDrawing.get()) {
            addImageToHistoryAndSaveToDrive(image);
        }

    }

    private void addImageToHistoryAndSaveToDrive(BufferedImage image) {
        BufferedImage b = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics g = b.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        History.addImage(b);
        saveImage(b);
    }

    private void saveImage(BufferedImage bufferedImage) {
        new Thread(() -> {
            File outputfile = new File("images/mandelbrot_"
                    + Settings.getWIDTH()
                    + "x"
                    + Settings.getHEIGHT()
                    + "_"
                    + MyMath.bigDeziToString(Settings.getWorldXOffset())
                    + "_"
                    + MyMath.bigDeziToString(Settings.getWorldYOffset())
                    + "_"
                    + MyMath.bigDeziToString(Settings.getZoomX())
                    + "_"
                    + Settings.isCVariable()
                    + "_"
                    + Settings.getNonVariable()
                    + ".png");
            try {
                if (!outputfile.exists()) {
                    outputfile.createNewFile();
                }
                ImageIO.write(bufferedImage, "png", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
