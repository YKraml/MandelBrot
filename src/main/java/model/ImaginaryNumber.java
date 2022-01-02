package main.java.model;

import main.java.main.Settings;

import java.math.BigDecimal;
import java.util.Objects;

public class ImaginaryNumber {

    private final BigDecimal a;
    private final BigDecimal b;

    public ImaginaryNumber(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    public ImaginaryNumber(double a, double b) {
        this.a = BigDecimal.valueOf(a);
        this.b = BigDecimal.valueOf(b);
    }

    public BigDecimal getA() {
        return a;
    }

    public BigDecimal getB() {
        return b;
    }

    public MyPoint toMyPoint() {
        return new MyPoint(a, b);
    }

    public BigDecimal getLength() {

        return a.multiply(a, Settings.getMathContext()).add(b.multiply(b, Settings.getMathContext()), Settings.getMathContext());


    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImaginaryNumber that = (ImaginaryNumber) o;
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
