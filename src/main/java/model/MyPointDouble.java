package main.java.model;

public class MyPointDouble implements MyPoint<Double> {
    private final double x;
    private final double y;

    public MyPointDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }


    @Override
    public String toString() {
        return "(" + x + "|" + y + ")";
    }

    @Override
    public ImaginaryNumber<Double> toImaginaryNumber() {
        return new ImaginaryNumberDouble(x, y);
    }

}
