package main.java.model;

import java.awt.*;
import java.math.BigDecimal;

public class MyPoint {

    private final BigDecimal x;
    private final BigDecimal y;

    public MyPoint(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public MyPoint(double x, double y) {
        this.x = BigDecimal.valueOf(x);
        this.y = BigDecimal.valueOf(y);
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public int getXAsInt() {
        return x.intValue();
    }

    public int getYAsInt() {
        return y.intValue();
    }

    public static MyPoint valueOf(Point point){
        return new MyPoint(BigDecimal.valueOf(point.x), BigDecimal.valueOf(point.y));
    }

    @Override
    public String toString() {
        return "(" + x + "|" + y + ")";
    }

    public ImaginaryNumber toImaginaryNumber(){
        return new ImaginaryNumber(x,y);
    }
}
