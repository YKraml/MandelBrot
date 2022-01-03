package main.java.main;

public class Wrapper<K extends Number> {

    String pairedNamed;
    private K value;

    public Wrapper(K value, String pairedName) {
        this.value = value;
        this.pairedNamed = pairedName;
    }

    public Wrapper(K value) {
        this(value, "NO NAME");
    }

    public void setValue(K value) {
        this.value = value;
    }

    public K getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public String getPairedNamed() {
        return pairedNamed;
    }

    public static <T extends Number> Wrapper<T> valueOf(T value, String pairedNamed) {
        return new Wrapper<>(value, pairedNamed);
    }

    public static <T extends Number> Wrapper<T> valueOf(T value) {
        return new Wrapper<>(value);
    }
}
