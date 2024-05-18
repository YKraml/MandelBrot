package main.java.model;

import main.java.main.Settings;

import java.math.BigDecimal;

public class MyPointBigDecimal implements MyPoint<BigDecimal> {

    private final BigDecimal x;
    private final BigDecimal y;
    private final Settings<BigDecimal> settings;

    public MyPointBigDecimal(BigDecimal x, BigDecimal y, Settings<BigDecimal> settings) {
        this.x = x;
        this.y = y;
        this.settings = settings;
    }

    public MyPointBigDecimal(double x, double y, Settings<BigDecimal> settings) {
        this.x = BigDecimal.valueOf(x);
        this.y = BigDecimal.valueOf(y);
        this.settings = settings;
    }

    @Override
    public BigDecimal getX() {
        return x;
    }

    @Override
    public BigDecimal getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "|" + y + ")";
    }

    @Override
    public ImaginaryNumber<BigDecimal> toImaginaryNumber() {
        return new ImaginaryNumberBigDecimal(x, y, settings);
    }

}
