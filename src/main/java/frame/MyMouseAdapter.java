package main.java.frame;

import main.java.main.Settings;
import main.java.model.MyMath;
import main.java.model.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.math.BigDecimal;

public class MyMouseAdapter extends MouseAdapter {


    private BigDecimal originalMousePositionX;
    private BigDecimal originalMousePositionY;


    @Override
    public void mouseDragged(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e)) {

            Settings.setChanged(true);

            BigDecimal newOffsetX = Settings.getWorldXOffset().add((BigDecimal.valueOf(e.getX()).subtract(originalMousePositionX)).divide(Settings.getZoomX(), Settings.getMathContext()));
            BigDecimal newOffsetY = Settings.getWorldYOffset().add((BigDecimal.valueOf(e.getY()).subtract(originalMousePositionY)).divide(Settings.getZoomY(), Settings.getMathContext()));

            originalMousePositionX = BigDecimal.valueOf(e.getX());
            originalMousePositionY = BigDecimal.valueOf(e.getY());

            Settings.setWorldXOffset(newOffsetX);
            Settings.setWorldYOffset(newOffsetY);
            return;
        }

        MyPoint point = calcScreenToWorld(e.getPoint());
        Settings.setMouseXPos(point.getX());
        Settings.setMouseYPos(point.getY());

    }


    @Override
    public void mouseMoved(MouseEvent e) {
        MyPoint point = calcScreenToWorld(e.getPoint());
        Settings.setMouseXPos(point.getX());
        Settings.setMouseYPos(point.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        MyPoint point = calcScreenToWorld(e.getPoint());
        Settings.setMouseXPos(point.getX());
        Settings.setMouseYPos(point.getY());

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (SwingUtilities.isRightMouseButton(e)) {

            this.originalMousePositionX = new BigDecimal(e.getX());
            this.originalMousePositionY = new BigDecimal(e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        Settings.setChanged(true);

        super.mouseWheelMoved(e);

        MyPoint mousePosBefore = this.calcScreenToWorld(e.getPoint());

        int rotation = e.getWheelRotation();
        if (rotation < 0) {
            Settings.setZoomX(Settings.getZoomX().multiply(new BigDecimal("1.2")));
            Settings.setZoomY(Settings.getZoomY().multiply(new BigDecimal("1.2")));
        } else if (rotation > 0) {
            Settings.setZoomX(Settings.getZoomX().multiply(new BigDecimal("0.8")));
            Settings.setZoomY(Settings.getZoomY().multiply(new BigDecimal("0.8")));
        }
        MyPoint mousePosAfter = this.calcScreenToWorld(e.getPoint());

        BigDecimal xVector = mousePosBefore.getX().subtract(mousePosAfter.getX());
        BigDecimal yVector = mousePosBefore.getY().subtract(mousePosAfter.getY());

        Settings.setWorldXOffset(Settings.getWorldXOffset().subtract(xVector));
        Settings.setWorldYOffset(Settings.getWorldYOffset().subtract(yVector));

        MyPoint point = calcScreenToWorld(e.getPoint());
        Settings.setMouseXPos(point.getX());
        Settings.setMouseYPos(point.getY());
    }


    private MyPoint calcScreenToWorld(Point screenPoint) {
        return MyMath.calcScreenToWorld(new MyPoint(BigDecimal.valueOf(screenPoint.getX()), BigDecimal.valueOf(screenPoint.getY())));
    }

}
