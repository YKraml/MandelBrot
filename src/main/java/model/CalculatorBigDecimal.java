package main.java.model;


import main.java.main.Settings;

import java.awt.*;
import java.math.BigDecimal;

public class CalculatorBigDecimal implements Calculator<BigDecimal> {

    private final Settings<BigDecimal> settings;

    public CalculatorBigDecimal(Settings<BigDecimal> settings) {
        this.settings = settings;
    }

    @Override
    public ImaginaryNumber<BigDecimal> add(ImaginaryNumber<BigDecimal> x, ImaginaryNumber<BigDecimal> y) {
        return new ImaginaryNumberBigDecimal(x.getA().add(y.getA()), x.getB().add(y.getB()), settings);
    }

    @Override
    public ImaginaryNumber<BigDecimal> multiply(ImaginaryNumber<BigDecimal> x, ImaginaryNumber<BigDecimal> y) {
        return new ImaginaryNumberBigDecimal(x.getA().multiply(y.getA()).subtract(x.getB().multiply(y.getB())).round(settings.getMathContext()), x.getA().multiply(y.getB()).add(y.getA().multiply(x.getB())).round(settings.getMathContext()), settings);
    }

    @Override
    public MyPoint<BigDecimal> calcWorldToScreen(MyPoint<BigDecimal> worldPoint, BigDecimal worldXOffset, BigDecimal worldYOffset, BigDecimal zoomX, BigDecimal zoomY) {
        BigDecimal screenXPos = worldXOffset.add(worldPoint.getX(), settings.getMathContext()).multiply(zoomX, settings.getMathContext());
        BigDecimal screenYPos = worldYOffset.add(worldPoint.getY(), settings.getMathContext()).multiply(zoomY, settings.getMathContext());
        return new MyPointBigDecimal(screenXPos, screenYPos, settings);
    }

    @Override
    public MyPoint<BigDecimal> calcScreenToWorld(MyPoint<BigDecimal> screenPoint) {
        BigDecimal worldXPos = screenPoint.getX().divide(settings.getZoomX(), settings.getMathContext()).subtract(settings.getWorldXOffset());
        BigDecimal worldYPos = screenPoint.getY().divide(settings.getZoomX(), settings.getMathContext()).subtract(settings.getWorldYOffset());
        return new MyPointBigDecimal(worldXPos, worldYPos, settings);
    }

    @Override
    public MyPointBigDecimal valueOf(Point point) {
        return new MyPointBigDecimal(BigDecimal.valueOf(point.x), BigDecimal.valueOf(point.y), settings);
    }

    @Override
    public MyPointBigDecimal createMousePoint() {
        return new MyPointBigDecimal(settings.getMouseXPos(), settings.getMouseYPos(), settings);
    }

    @Override
    public BigDecimal calcOffset(BigDecimal worldXOffset, int value, BigDecimal originalMousePosition, BigDecimal zoom) {
        return worldXOffset.add((BigDecimal.valueOf(value).subtract(originalMousePosition)).divide(zoom, settings.getMathContext()));
    }

    @Override
    public MyPoint<BigDecimal> createPoint(int x, int y) {
        return new MyPointBigDecimal(x + settings.getBigPixelSize() * 0.5, y + settings.getBigPixelSize() * 0.5, settings);
    }

    @Override
    public BigDecimal createNumber(double number) {
        return new BigDecimal(number);
    }

    @Override
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    @Override
    public MyPoint<BigDecimal> createPoint(BigDecimal x, BigDecimal y) {
        return new MyPointBigDecimal(x, y, settings);
    }
}
