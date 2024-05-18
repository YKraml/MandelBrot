package main.java.main;

import java.math.MathContext;

public abstract class SettingsGeneral<K extends Number> implements Settings<K> {
    protected final Wrapper<Integer> imageWidth = Wrapper.valueOf(500, "Image Width");
    protected final Wrapper<Integer> imageHeight = Wrapper.valueOf(500, "Image Height");
    private final Wrapper<Integer> precision = Wrapper.valueOf(25, "Precision");
    private final Wrapper<Integer> maxRecursionDepth = Wrapper.valueOf(100, "Max Recursion Depth");
    private final Wrapper<Integer> bigPixelSize = Wrapper.valueOf((imageWidth.getValue() + imageHeight.getValue()) / 200, "Pre Render Size");
    private boolean changed = true;
    private boolean cIsVariable = true;

    private boolean useLargeDoubles = true;

    @Override
    public int getPrecision() {
        return precision.getValue();
    }

    @Override
    public void setPrecision(int precision) {
        this.precision.setValue(precision);
    }

    @Override
    public int getMaxRecursionDepth() {
        return maxRecursionDepth.getValue();
    }

    @Override
    public void setMaxRecursionDepth(int maxRecursionDepth) {
        this.maxRecursionDepth.setValue(maxRecursionDepth);
    }

    @Override
    public MathContext getMathContext() {
        return new MathContext(precision.getValue());
    }

    @Override
    public int getImageWidth() {
        return imageWidth.getValue();
    }

    @Override
    public void setImageWidth(int imageWidth) {
        this.imageWidth.setValue(imageWidth);
    }

    @Override
    public int getImageHeight() {
        return imageHeight.getValue();
    }

    @Override
    public void setImageHeight(int imageHeight) {
        this.imageHeight.setValue(imageHeight);
    }

    @Override
    public boolean isChanged() {
        return changed;
    }

    @Override
    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    @Override
    public int getBigPixelSize() {
        return bigPixelSize.getValue();
    }

    @Override
    public boolean isCVariable() {
        return cIsVariable;
    }

    @Override
    public void setCIsVariable(boolean cIsVariable) {
        this.cIsVariable = cIsVariable;
    }

    @Override
    public Wrapper<Integer> getPrecisionWrapper() {
        return precision;
    }

    @Override
    public Wrapper<Integer> getMaxRecursionDepthWrapper() {
        return maxRecursionDepth;
    }

    @Override
    public Wrapper<Integer> getImageWidthWrapper() {
        return imageWidth;
    }

    @Override
    public Wrapper<Integer> getImageHeightWrapper() {
        return imageHeight;
    }

    @Override
    public Wrapper<Integer> getBigPixelSizeWrapper() {
        return bigPixelSize;
    }

    @Override
    public boolean isUseLargeDoubles() {
        return useLargeDoubles;
    }

    @Override
    public void setUseLargeDoubles(boolean useLargeDoubles) {
        this.useLargeDoubles = useLargeDoubles;
    }
}
