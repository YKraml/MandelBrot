package main.java.model;

import java.awt.*;

public interface Calculator<K extends Number> {

    ImaginaryNumber<K> add(ImaginaryNumber<K> x, ImaginaryNumber<K> y);

    ImaginaryNumber<K> multiply(ImaginaryNumber<K> x, ImaginaryNumber<K> y);

    default String formatNumber(K number) {

        boolean numberIsLarge = number.doubleValue() > Math.pow(10, 6);
        boolean numberIsInt = number.doubleValue() % 1 == 0;

        String formattedNumber = String.valueOf(number);

        if (numberIsLarge && numberIsInt) {
            formattedNumber = String.format("%e", number.doubleValue());
        } else if (!numberIsLarge && numberIsInt) {
            formattedNumber = String.format("%.0f", number.doubleValue());
        } else if (numberIsLarge) {
            formattedNumber = String.format("%.3e", number.doubleValue());
        } else {
            formattedNumber = String.format("%.3f", number.doubleValue());
        }

        return formattedNumber.replace(",", ".");
    }

    MyPoint<K> calcWorldToScreen(MyPoint<K> worldPoint, K worldXOffset, K worldYOffset, K zoomX, K zoomY);

    MyPoint<K> calcScreenToWorld(MyPoint<K> screenPoint);

    MyPoint<K> valueOf(Point point);

    MyPoint<K> createMousePoint();

    K calcOffset(K worldXOffset, int x, K originalMousePosition, K zoom);

    MyPoint<K> createPoint(int x, int y);

    K createNumber(double number);

    K subtract(K a, K b);

    MyPoint<K> createPoint(K x, K y);
}
