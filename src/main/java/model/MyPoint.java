package main.java.model;

public interface MyPoint<K extends Number> {

    K getX();

    K getY();

    @Override
    String toString();

    ImaginaryNumber<K> toImaginaryNumber();
}
