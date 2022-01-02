package main.java.frame;

import main.java.main.Settings;
import main.java.model.Graph;
import main.java.model.MyMath;
import main.java.model.MyPoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends MyPanel {

    protected static final Color MOUSE_COLOR = Color.GREEN;
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    private final Graph _graph;
    private final BufferedImage _image;
    private Thread _drawThread;

    public DrawPanel(Graph graph) {
        _graph = graph;
        _image = new BufferedImage(Settings.getWIDTH(), Settings.getHEIGHT(), BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected void init() {
        Dimension dimension = new Dimension();
        dimension.setSize(Settings.getWIDTH(), Settings.getHEIGHT());
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setBackground(BACKGROUND_COLOR);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (Settings.isChanged()) {
            Settings.setChanged(false);

            if (_drawThread != null) {
                _drawThread.interrupt();
            }

            _drawThread = new Thread(() -> _graph.calcImage(_image, Settings.getZoomX(), Settings.getZoomY(), Settings.getWorldXOffset(), Settings.getWorldYOffset()));
            _drawThread.start();
        }

        g.drawImage(_image,
                0, 0, _image.getWidth(), _image.getHeight(),
                (img, infoFlags, x, y, width, height) -> false);

        g.setColor(MOUSE_COLOR);
        MyPoint mousePos = new MyPoint(Settings.getMouseXPos(), Settings.getMouseYPos());
        drawMouse(g, mousePos);
    }

    private void drawMouse(Graphics g, MyPoint worldPoint) {
        MyPoint screenPoint = MyMath.calcWorldToScreen(worldPoint, Settings.getWorldXOffset(), Settings.getWorldYOffset(), Settings.getZoomX(), Settings.getZoomY());
        int xPixel = screenPoint.getX().intValue();
        int yPixel = screenPoint.getY().intValue();
        int width = 5;
        int height = 5;
        g.drawOval(xPixel - width / 2, yPixel - height / 2, width, height);
    }

}
