package main.java.frame;

import main.java.main.Settings;
import main.java.model.Calculator;
import main.java.model.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MyMouseAdapter<K extends Number> extends MouseAdapter {


    private final Calculator<K> calculator;
    private final Settings<K> settings;
    private K originalMousePositionX;
    private K originalMousePositionY;

    public MyMouseAdapter(Calculator<K> calculator, Settings<K> settings) {
        this.calculator = calculator;
        this.settings = settings;
    }


    @Override
    public void mouseDragged(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e)) {

            settings.setChanged(true);
            K newOffsetX = calculator.calcOffset(settings.getWorldXOffset(), e.getX(), originalMousePositionX, settings.getZoomX());
            K newOffsetY = calculator.calcOffset(settings.getWorldYOffset(), e.getY(), originalMousePositionY, settings.getZoomY());

            originalMousePositionX = calculator.createNumber(e.getX());
            originalMousePositionY = calculator.createNumber(e.getY());

            settings.setWorldXOffset(newOffsetX);
            settings.setWorldYOffset(newOffsetY);
            return;
        }

        MyPoint<K> point = calcScreenToWorld(e.getPoint());
        settings.setMouseXPos(point.getX());
        settings.setMouseYPos(point.getY());

    }


    @Override
    public void mouseMoved(MouseEvent e) {
        MyPoint<K> point = calcScreenToWorld(e.getPoint());
        settings.setMouseXPos(point.getX());
        settings.setMouseYPos(point.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        MyPoint<K> point = calcScreenToWorld(e.getPoint());
        settings.setMouseXPos(point.getX());
        settings.setMouseYPos(point.getY());

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (SwingUtilities.isRightMouseButton(e)) {
            this.originalMousePositionX = calculator.createNumber(e.getX());
            this.originalMousePositionY = calculator.createNumber(e.getY());
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        settings.setChanged(true);

        super.mouseWheelMoved(e);

        MyPoint<K> mousePosBefore = this.calcScreenToWorld(e.getPoint());

        int rotation = e.getWheelRotation();
        if (rotation < 0) {
            settings.zoomIn();
        } else if (rotation > 0) {
            settings.zoomOut();
        }
        MyPoint<K> mousePosAfter = this.calcScreenToWorld(e.getPoint());

        K xVector = calculator.subtract(mousePosBefore.getX(), mousePosAfter.getX());
        K yVector = calculator.subtract(mousePosBefore.getY(), mousePosAfter.getY());

        settings.reduceXOffset(xVector);
        settings.reduceYOffset(yVector);

        MyPoint<K> point = calcScreenToWorld(e.getPoint());
        settings.setMouseXPos(point.getX());
        settings.setMouseYPos(point.getY());
    }


    private MyPoint<K> calcScreenToWorld(Point screenPoint) {
        return calculator.calcScreenToWorld(calculator.createPoint(calculator.createNumber(screenPoint.getX()), calculator.createNumber(screenPoint.getY())));
    }

}
