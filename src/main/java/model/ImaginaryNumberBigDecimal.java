package main.java.model;

import main.java.main.Settings;

import java.math.BigDecimal;
import java.util.Objects;

public class ImaginaryNumberBigDecimal implements ImaginaryNumber<BigDecimal> {

    private final BigDecimal a;
    private final BigDecimal b;
    private final Settings<BigDecimal> settings;

    public ImaginaryNumberBigDecimal(BigDecimal a, BigDecimal b, Settings<BigDecimal> settings) {
        this.a = a;
        this.b = b;
        this.settings = settings;
    }

    public ImaginaryNumberBigDecimal(double a, double b, Settings<BigDecimal> settings) {
        this.a = BigDecimal.valueOf(a);
        this.b = BigDecimal.valueOf(b);
        this.settings = settings;
    }

    @Override
    public BigDecimal getA() {
        return a;
    }

    @Override
    public BigDecimal getB() {
        return b;
    }

    @Override
    public MyPoint<BigDecimal> toMyPoint() {
        return new MyPointBigDecimal(a, b, settings);
    }

    @Override
    public BigDecimal getLength() {
        return a.multiply(a, settings.getMathContext()).add(b.multiply(b, settings.getMathContext()), settings.getMathContext());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImaginaryNumberBigDecimal that = (ImaginaryNumberBigDecimal) o;
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
