package main.java.main;

import main.java.model.ImaginaryNumber;

import java.math.MathContext;

public interface Settings<K extends Number> {
    int getPrecision();

    void setPrecision(int precision);

    int getMaxRecursionDepth();

    void setMaxRecursionDepth(int maxRecursionDepth);

    MathContext getMathContext();

    int getImageWidth();

    void setImageWidth(int imageWidth);

    int getImageHeight();

    void setImageHeight(int imageHeight);

    K getMouseXPos();

    void setMouseXPos(K mouseXPos);

    K getMouseYPos();

    void setMouseYPos(K mouseYPos);

    K getZoomX();

    void setZoomX(K zoomX);

    K getZoomY();

    void setZoomY(K zoomY);

    K getWorldXOffset();

    void setWorldXOffset(K worldXOffset);

    K getWorldYOffset();

    void setWorldYOffset(K worldYOffset);

    boolean isChanged();

    void setChanged(boolean changed);

    int getBigPixelSize();

    boolean isCVariable();

    ImaginaryNumber<K> getNonVariable();

    void setNonVariable(ImaginaryNumber<K> nonVariable);

    void setCIsVariable(boolean cIsVariable);

    Wrapper<Integer> getPrecisionWrapper();

    Wrapper<Integer> getMaxRecursionDepthWrapper();

    Wrapper<Integer> getImageWidthWrapper();

    Wrapper<Integer> getImageHeightWrapper();

    Wrapper<Integer> getBigPixelSizeWrapper();

    Wrapper<K> getMouseXPosWrapper();

    Wrapper<K> getMouseYPosWrapper();

    Wrapper<K> getWorldXOffsetWrapper();

    Wrapper<K> getWorldYOffsetWrapper();

    Wrapper<K> getZoomXWrapper();

    Wrapper<K> getZoomYWrapper();

    void zoomIn();

    void zoomOut();

    void reduceXOffset(K xVector);

    void reduceYOffset(K yVector);

    boolean isUseLargeDoubles();

    void setUseLargeDoubles(boolean useLargeDoubles);
}
