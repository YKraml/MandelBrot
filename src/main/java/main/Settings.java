package main.java.main;

import main.java.model.ImaginaryNumber;

import java.math.BigDecimal;
import java.math.MathContext;

public class Settings {

    private static final Wrapper<Integer> precision = Wrapper.valueOf(25, "Precision");
    private static final Wrapper<Integer> maxRecursionDepth = Wrapper.valueOf(100, "Max Recursion Depth");

    private static final Wrapper<Integer> imageWidth = Wrapper.valueOf(700, "Image Width");
    private static final Wrapper<Integer> imageHeight = Wrapper.valueOf(500, "Image Height");

    private static final Wrapper<Integer> bigPixelSize = Wrapper.valueOf((imageWidth.getValue() + imageHeight.getValue()) / 200, "Pre Render Size");

    private static final Wrapper<BigDecimal> mouseXPos = Wrapper.valueOf(BigDecimal.ZERO, "Mouse X Pos");
    private static final Wrapper<BigDecimal> mouseYPos = Wrapper.valueOf(BigDecimal.ZERO, "Mouse Y Pos");

    private static final Wrapper<BigDecimal> zoomX = Wrapper.valueOf(new BigDecimal(imageWidth.getValue() / 4), "Zoom X");
    private static final Wrapper<BigDecimal> zoomY = Wrapper.valueOf(zoomX.getValue(), "Zoom Y");

    private static final Wrapper<BigDecimal> worldXOffset = Wrapper.valueOf(BigDecimal.valueOf(imageWidth.getValue() / zoomX.getValue().doubleValue() / 2), "World X Offset");
    private static final Wrapper<BigDecimal> worldYOffset = Wrapper.valueOf(BigDecimal.valueOf(imageHeight.getValue() / zoomY.getValue().doubleValue() / 2), "World X Offset");

    private static boolean changed = true;


    private static boolean cIsVariable = true;
    private static ImaginaryNumber nonVariable = new ImaginaryNumber(0, 0);


    public static int getPrecision() {
        return precision.getValue();
    }

    public static int getMaxRecursionDepth() {
        return maxRecursionDepth.getValue();
    }

    public static MathContext getMathContext() {
        return new MathContext(precision.getValue());
    }

    public static int getImageWidth() {
        return imageWidth.getValue();
    }

    public static int getImageHeight() {
        return imageHeight.getValue();
    }

    public static BigDecimal getMouseXPos() {
        return mouseXPos.getValue();
    }

    public static BigDecimal getMouseYPos() {
        return mouseYPos.getValue();
    }

    public static BigDecimal getZoomX() {
        return zoomX.getValue();
    }

    public static BigDecimal getZoomY() {
        return zoomY.getValue();
    }

    public static BigDecimal getWorldXOffset() {
        return worldXOffset.getValue();
    }

    public static BigDecimal getWorldYOffset() {
        return worldYOffset.getValue();
    }

    public static boolean isChanged() {
        return changed;
    }

    public static int getBigPixelSize() {
        return bigPixelSize.getValue();
    }

    public static boolean isCVariable() {
        return cIsVariable;
    }

    public static ImaginaryNumber getNonVariable() {
        return nonVariable;
    }

    public static void setPrecision(int precision) {
        Settings.precision.setValue(precision);
    }

    public static void setMaxRecursionDepth(int maxRecursionDepth) {
        Settings.maxRecursionDepth.setValue(maxRecursionDepth);
    }

    public static void setImageWidth(int imageWidth) {
        Settings.imageWidth.setValue(imageWidth);
    }

    public static void setImageHeight(int imageHeight) {
        Settings.imageHeight.setValue(imageHeight);
    }

    public static void setMouseXPos(BigDecimal mouseXPos) {
        Settings.mouseXPos.setValue(mouseXPos);
    }

    public static void setMouseYPos(BigDecimal mouseYPos) {
        Settings.mouseYPos.setValue(mouseYPos);
    }

    public static void setZoomX(BigDecimal zoomX) {
        Settings.zoomX.setValue(zoomX);
    }

    public static void setZoomY(BigDecimal zoomY) {
        Settings.zoomY.setValue(zoomY);
    }

    public static void setWorldXOffset(BigDecimal worldXOffset) {
        Settings.worldXOffset.setValue(worldXOffset);
    }

    public static void setWorldYOffset(BigDecimal worldYOffset) {
        Settings.worldYOffset.setValue(worldYOffset);
    }

    public static void setChanged(boolean changed) {
        Settings.changed = changed;
    }

    public static void setCIsVariable(boolean cIsVariable) {
        Settings.cIsVariable = cIsVariable;
    }

    public static void setNonVariable(ImaginaryNumber nonVariable) {
        Settings.nonVariable = nonVariable;
    }


    public static Wrapper<Integer> getPrecisionWrapper() {
        return precision;
    }

    public static Wrapper<Integer> getMaxRecursionDepthWrapper() {
        return maxRecursionDepth;
    }

    public static Wrapper<Integer> getImageWidthWrapper() {
        return imageWidth;
    }

    public static Wrapper<Integer> getImageHeightWrapper() {
        return imageHeight;
    }

    public static Wrapper<Integer> getBigPixelSizeWrapper() {
        return bigPixelSize;
    }

    public static Wrapper<BigDecimal> getMouseXPosWrapper() {
        return mouseXPos;
    }

    public static Wrapper<BigDecimal> getMouseYPosWrapper() {
        return mouseYPos;
    }

    public static Wrapper<BigDecimal> getWorldXOffsetWrapper() {
        return worldXOffset;
    }

    public static Wrapper<BigDecimal> getWorldYOffsetWrapper() {
        return worldYOffset;
    }

    public static Wrapper<BigDecimal> getZoomXWrapper() {
        return zoomX;
    }

    public static Wrapper<BigDecimal> getZoomYWrapper() {
        return zoomY;
    }

}
