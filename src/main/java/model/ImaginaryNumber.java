package main.java.model;

public interface ImaginaryNumber<K extends Number> {
    K getA();

    K getB();

    MyPoint<K> toMyPoint();

    K getLength();
}
