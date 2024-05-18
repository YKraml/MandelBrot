package main.java.model;

import java.util.Objects;

public class ImaginaryNumberDouble implements ImaginaryNumber<Double> {
    private final double a;
    private final double b;

    public ImaginaryNumberDouble(double a, double b) {
        this.a = a;
        this.b = b;
    }


    @Override
    public Double getA() {
        return a;
    }

    @Override
    public Double getB() {
        return b;
    }

    @Override
    public MyPoint<Double> toMyPoint() {
        return new MyPointDouble(a, b);
    }

    @Override
    public Double getLength() {
        return a * a + b * b;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImaginaryNumberDouble that = (ImaginaryNumberDouble) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return a + "+" + b + "i";
    }
}
