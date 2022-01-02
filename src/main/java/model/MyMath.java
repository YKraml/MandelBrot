package main.java.model;

import main.java.main.Settings;

import java.math.BigDecimal;

public class MyMath {

    public static ImaginaryNumber add(ImaginaryNumber x, ImaginaryNumber y) {
        return new ImaginaryNumber(
                x.getA().add(y.getA()),
                x.getB().add(y.getB())
        );
    }

    public static ImaginaryNumber mult(ImaginaryNumber x, ImaginaryNumber y) {
        return new ImaginaryNumber(
                x.getA().multiply(y.getA()).subtract(x.getB().multiply(y.getB())).round(Settings.getMathContext()),
                x.getA().multiply(y.getB()).add(y.getA().multiply(x.getB())).round(Settings.getMathContext())
        );
    }

    public static String bigDeziToString(BigDecimal bigDecimal) {
        return String.format("%6.3e", bigDecimal.doubleValue());
    }

    public static MyPoint calcWorldToScreen(MyPoint worldPoint, BigDecimal worldXOffset, BigDecimal worldYOffset, BigDecimal zoomX, BigDecimal zoomY) {

        BigDecimal screenXPos = worldXOffset.add(worldPoint.getX(), Settings.getMathContext()).multiply(zoomX, Settings.getMathContext());
        BigDecimal screenYPos = worldYOffset.add(worldPoint.getY(), Settings.getMathContext()).multiply(zoomY, Settings.getMathContext());

        return new MyPoint(screenXPos, screenYPos);

    }

    public static MyPoint calcScreenToWorld(MyPoint screenPoint) {

        BigDecimal worldXPos = screenPoint.getX().divide(Settings.getZoomX(), Settings.getMathContext()).subtract(Settings.getWorldXOffset());
        BigDecimal worldYPos = screenPoint.getY().divide(Settings.getZoomX(), Settings.getMathContext()).subtract(Settings.getWorldYOffset());

        return new MyPoint(worldXPos, worldYPos);
    }

}
