package main.java.frame;

import main.java.main.Settings;
import main.java.model.Calculator;
import main.java.model.Graph;
import main.java.model.MyPoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends MyPanel {

    protected static final Color MOUSE_COLOR = Color.GREEN;
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    private final Graph graph;
    private final Calculator calculator;
    private final Settings settings;
    private BufferedImage image;
    private Thread drawThread;

    public DrawPanel(Graph graph, Calculator calculator, Settings settings) {
        this.graph = graph;
        this.calculator = calculator;
        this.settings = settings;
        image = new BufferedImage(this.settings.getImageWidth(), this.settings.getImageHeight(), BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected void init() {
        Dimension dimension = new Dimension();
        dimension.setSize(settings.getImageWidth(), settings.getImageHeight());
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setBackground(BACKGROUND_COLOR);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (settings.isChanged()) {
            settings.setChanged(false);

            if (drawThread != null) {
                drawThread.interrupt();
            }

            boolean sizeChanged = settings.getImageWidth() != image.getWidth() || settings.getImageHeight() != image.getHeight();
            if (sizeChanged) {
                image = new BufferedImage(settings.getImageWidth(), settings.getImageHeight(), BufferedImage.TYPE_INT_RGB);
            }
            drawThread = new Thread(() -> graph.calcImage(image));
            drawThread.start();
        }

        g.drawImage(image,
                0, 0, image.getWidth(), image.getHeight(),
                (img, infoFlags, x, y, width, height) -> false);

        g.setColor(MOUSE_COLOR);
        MyPoint mousePos = calculator.createMousePoint();
        drawMouse(g, mousePos);
    }

    private void drawMouse(Graphics g, MyPoint worldPoint) {
        MyPoint screenPoint = calculator.calcWorldToScreen(worldPoint, settings.getWorldXOffset(), settings.getWorldYOffset(), settings.getZoomX(), settings.getZoomY());
        int xPixel = screenPoint.getX().intValue();
        int yPixel = screenPoint.getY().intValue();
        int width = 5;
        int height = 5;
        g.drawOval(xPixel - width / 2, yPixel - height / 2, width, height);
    }

}
