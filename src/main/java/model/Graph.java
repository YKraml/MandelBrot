package main.java.model;

import main.java.main.History;
import main.java.main.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Graph<K extends Number> {

    private static final Color[] gradient;
    private final Calculator<K> calculator;
    private final Settings<K> settings;


    static {
        gradient = new Color[10000];
        for (int i = 0; i < gradient.length; i++) {
            gradient[i] = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
        }
    }

    public Graph(Calculator<K> calculator, Settings<K> settings) {
        this.calculator = calculator;
        this.settings = settings;
    }

    private int getDepthFrom(ImaginaryNumber<K> startNumber, ImaginaryNumber<K> c) {

        ImaginaryNumber<K> current = startNumber;
        for (int k = 0; k < settings.getMaxRecursionDepth(); k++) {
            current = calculator.add(calculator.multiply(current, current), c);

            if (current.getLength().doubleValue() < Math.pow(10, -10)) {
                return k;
            }

            if (current.getLength().intValue() > 2) {
                return k;
            }
        }

        return -1;
    }

    public Color getColorFrom(ImaginaryNumber<K> startNumber, ImaginaryNumber<K> c) {
        int depth = getDepthFrom(c, startNumber);
        return depth < 0 ? Color.BLACK : gradient[(depth + 2) % gradient.length];
    }

    public synchronized void calcImage(BufferedImage image) {

        AtomicBoolean finishedDrawing = new AtomicBoolean(true);

        Set<Point> bigPixels = new HashSet<>();
        for (int i = 0; i < image.getWidth() / settings.getBigPixelSize() + 1; i++) {
            if (settings.isChanged()) {
                return;
            }
            for (int j = 0; j < image.getHeight() / settings.getBigPixelSize() + 1; j++) {
                bigPixels.add(new Point(i * settings.getBigPixelSize(), j * settings.getBigPixelSize()));
            }
        }

        //Male BigPixels
        bigPixels.parallelStream().forEach(screenPoint -> {
            if (settings.isChanged()) {
                finishedDrawing.set(false);
                return;
            }

            MyPoint<K> myPoint = calculator.createPoint(screenPoint.x, screenPoint.y);
            MyPoint<K> worldPoint = calculator.calcScreenToWorld(myPoint);
            Color color = settings.isCVariable() ? getColorFrom(worldPoint.toImaginaryNumber(), settings.getNonVariable()) : getColorFrom(settings.getNonVariable(), worldPoint.toImaginaryNumber());

            for (int i = 0; i < settings.getBigPixelSize(); i++) {
                for (int j = 0; j < settings.getBigPixelSize(); j++) {
                    int pixelX = screenPoint.x + i;
                    int pixelY = screenPoint.y + j;
                    if (pixelX < image.getWidth() && pixelY < image.getHeight() && pixelX >= 0 && pixelY >= 0) {
                        image.setRGB(pixelX, pixelY, color.getRGB());
                    }
                }
            }
        });

        //Finde BigPixels, die neu gemalt werden müssen.
        Set<Point> bigPixelsToBeRedrawn = bigPixels.parallelStream().filter(screenPoint -> {
            Point[] bigPixelNeighbors = new Point[]{
                    new Point(screenPoint.x - settings.getBigPixelSize(), screenPoint.y - settings.getBigPixelSize()), new Point(screenPoint.x, screenPoint.y - settings.getBigPixelSize()), new Point(screenPoint.x + settings.getBigPixelSize(), screenPoint.y - settings.getBigPixelSize()),
                    new Point(screenPoint.x - settings.getBigPixelSize(), screenPoint.y), new Point(screenPoint.x, screenPoint.y), new Point(screenPoint.x + settings.getBigPixelSize(), screenPoint.y),
                    new Point(screenPoint.x - settings.getBigPixelSize(), screenPoint.y + settings.getBigPixelSize()), new Point(screenPoint.x, screenPoint.y + settings.getBigPixelSize()), new Point(screenPoint.x + settings.getBigPixelSize(), screenPoint.y + settings.getBigPixelSize())
            };

            Set<Integer> neighboringColors = new HashSet<>();
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
            Runnable runnable = () -> draw(image, bigPixel, finishedDrawing);
            wait(runnable);
        });

        if (finishedDrawing.get()) {
            System.out.println("Finished");
            //CompletableFuture.runAsync(() -> addImageToHistoryAndSaveToDrive(image));
        }

    }


    private void draw(BufferedImage image, Point bigPixel, AtomicBoolean finishedDrawing) {
        if (settings.isChanged()) {
            finishedDrawing.set(false);
            return;
        }

        for (int k = 0; k < settings.getBigPixelSize() * settings.getBigPixelSize(); k++) {
            Point screenPoint = new Point(bigPixel.x + k / settings.getBigPixelSize(), bigPixel.y + k % settings.getBigPixelSize());
            MyPoint<K> worldPoint = calculator.calcScreenToWorld(calculator.valueOf(screenPoint));
            Color color = settings.isCVariable() ? getColorFrom(worldPoint.toImaginaryNumber(), settings.getNonVariable()) : getColorFrom(settings.getNonVariable(), worldPoint.toImaginaryNumber());

            if (screenPoint.x < image.getWidth() && screenPoint.y < image.getHeight() && screenPoint.x >= 0 && screenPoint.y >= 0) {
                image.setRGB(screenPoint.x, screenPoint.y, color.getRGB());
            }
        }
    }

    private static void wait(Runnable runnable) {
        try {
            CompletableFuture.runAsync(runnable)
                    .orTimeout(60, TimeUnit.SECONDS)
                    .exceptionally(throwable -> {
                        System.out.println("An error occurred");
                        return null;
                    }).get();
        } catch (InterruptedException | ExecutionException ignored) {
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
                    + settings.getImageWidth()
                    + "x"
                    + settings.getImageHeight()
                    + "_"
                    + calculator.formatNumber(settings.getWorldXOffset())
                    + "_"
                    + calculator.formatNumber(settings.getWorldYOffset())
                    + "_"
                    + calculator.formatNumber(settings.getZoomX())
                    + "_"
                    + settings.isCVariable()
                    + "_"
                    + settings.getNonVariable()
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
