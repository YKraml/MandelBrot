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

    public static <K extends Number> String formatNumber(K number) {

        boolean numberIsLarge = number.doubleValue() > Math.pow(10, 6);
        boolean numberIsInt = number.doubleValue() % 1 == 0;

        String formattedNumber = String.valueOf(number);

        if (numberIsLarge && numberIsInt) {
            formattedNumber = String.format("%e", number.doubleValue());
        } else if (!numberIsLarge && numberIsInt) {
            formattedNumber = String.format("%.0f", number.doubleValue());
        } else if (numberIsLarge && !numberIsInt) {
            formattedNumber = String.format("%.3e", number.doubleValue());
        } else if (!numberIsLarge && !numberIsInt) {
            formattedNumber = String.format("%.3f", number.doubleValue());
        }

        return formattedNumber.replace(",", ".");
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
