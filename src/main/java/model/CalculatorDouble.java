package main.java.model;

import main.java.main.Settings;

import java.awt.*;

public class CalculatorDouble implements Calculator<Double> {
    private final Settings<Double> settings;

    public CalculatorDouble(Settings<Double> settings) {
        this.settings = settings;
    }

    @Override
    public ImaginaryNumber<Double> add(ImaginaryNumber<Double> x, ImaginaryNumber<Double> y) {
        return new ImaginaryNumberDouble(x.getA() + y.getA(), x.getB() + y.getB());
    }

    @Override
    public ImaginaryNumber<Double> multiply(ImaginaryNumber<Double> x, ImaginaryNumber<Double> y) {
        return new ImaginaryNumberDouble(x.getA() * (y.getA()) - (x.getB() * (y.getB())), x.getA() * (y.getB()) + (y.getA() * (x.getB())));
    }

    @Override
    public MyPoint<Double> calcWorldToScreen(MyPoint<Double> worldPoint, Double worldXOffset, Double worldYOffset, Double zoomX, Double zoomY) {
        double screenXPos = worldXOffset + (worldPoint.getX()) * (zoomX);
        double screenYPos = worldYOffset + (worldPoint.getY()) * (zoomY);
        return new MyPointDouble(screenXPos, screenYPos);
    }

    @Override
    public MyPoint<Double> calcScreenToWorld(MyPoint<Double> screenPoint) {
        double worldXPos = screenPoint.getX() / (settings.getZoomX()) - (settings.getWorldXOffset());
        double worldYPos = screenPoint.getY() / (settings.getZoomX()) - (settings.getWorldYOffset());
        return new MyPointDouble(worldXPos, worldYPos);
    }

    @Override
    public MyPoint<Double> valueOf(Point point) {
        return new MyPointDouble(point.x, point.y);
    }

    @Override
    public MyPoint<Double> createMousePoint() {
        return new MyPointDouble(settings.getMouseXPos(), settings.getMouseYPos());
    }

    @Override
    public Double calcOffset(Double worldXOffset, int value, Double originalMousePosition, Double zoom) {
        return worldXOffset + ((value - (originalMousePosition)) / (zoom));
    }

    @Override
    public MyPoint<Double> createPoint(int x, int y) {
        return new MyPointDouble(x + settings.getBigPixelSize() * 0.5, y + settings.getBigPixelSize() * 0.5);
    }

    @Override
    public Double createNumber(double number) {
        return number;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public MyPoint<Double> createPoint(Double x, Double y) {
        return new MyPointDouble(x, y);
    }
}
