package main.java.main;

import main.java.model.ImaginaryNumber;
import main.java.model.ImaginaryNumberBigDecimal;

import java.math.BigDecimal;

public class SettingsBigDecimal extends SettingsGeneral<BigDecimal> {

    private final Wrapper<BigDecimal> mouseXPos = Wrapper.valueOf(BigDecimal.ZERO, "Mouse X Pos");
    private final Wrapper<BigDecimal> mouseYPos = Wrapper.valueOf(BigDecimal.ZERO, "Mouse Y Pos");
    private final Wrapper<BigDecimal> zoomX = Wrapper.valueOf(new BigDecimal(imageWidth.getValue() / 4), "Zoom X");
    private final Wrapper<BigDecimal> zoomY = Wrapper.valueOf(zoomX.getValue(), "Zoom Y");
    private final Wrapper<BigDecimal> worldYOffset = Wrapper.valueOf(BigDecimal.valueOf(imageHeight.getValue() / zoomY.getValue().doubleValue() / 2), "World X Offset");
    private final Wrapper<BigDecimal> worldXOffset = Wrapper.valueOf(BigDecimal.valueOf(imageWidth.getValue() / zoomX.getValue().doubleValue() / 2), "World X Offset");


    private ImaginaryNumber<BigDecimal> nonVariable = new ImaginaryNumberBigDecimal(0, 0, this);


    @Override
    public BigDecimal getMouseXPos() {
        return mouseXPos.getValue();
    }

    @Override
    public void setMouseXPos(BigDecimal mouseXPos) {
        this.mouseXPos.setValue(mouseXPos);
    }

    @Override
    public BigDecimal getMouseYPos() {
        return mouseYPos.getValue();
    }

    @Override
    public void setMouseYPos(BigDecimal mouseYPos) {
        this.mouseYPos.setValue(mouseYPos);
    }

    @Override
    public BigDecimal getZoomX() {
        return zoomX.getValue();
    }

    @Override
    public void setZoomX(BigDecimal zoomX) {
        this.zoomX.setValue(zoomX);
    }

    @Override
    public BigDecimal getZoomY() {
        return zoomY.getValue();
    }

    @Override
    public void setZoomY(BigDecimal zoomY) {
        this.zoomY.setValue(zoomY);
    }

    @Override
    public BigDecimal getWorldXOffset() {
        return worldXOffset.getValue();
    }

    @Override
    public void setWorldXOffset(BigDecimal worldXOffset) {
        this.worldXOffset.setValue(worldXOffset);
    }

    @Override
    public BigDecimal getWorldYOffset() {
        return worldYOffset.getValue();
    }

    @Override
    public void setWorldYOffset(BigDecimal worldYOffset) {
        this.worldYOffset.setValue(worldYOffset);
    }

    @Override
    public ImaginaryNumber<BigDecimal> getNonVariable() {
        return nonVariable;
    }

    @Override
    public void setNonVariable(ImaginaryNumber<BigDecimal> nonVariable) {
        this.nonVariable = nonVariable;
    }

    @Override
    public Wrapper<BigDecimal> getMouseXPosWrapper() {
        return mouseXPos;
    }

    @Override
    public Wrapper<BigDecimal> getMouseYPosWrapper() {
        return mouseYPos;
    }

    @Override
    public Wrapper<BigDecimal> getWorldXOffsetWrapper() {
        return worldXOffset;
    }

    @Override
    public Wrapper<BigDecimal> getWorldYOffsetWrapper() {
        return worldYOffset;
    }

    @Override
    public Wrapper<BigDecimal> getZoomXWrapper() {
        return zoomX;
    }

    @Override
    public Wrapper<BigDecimal> getZoomYWrapper() {
        return zoomY;
    }

    @Override
    public void zoomIn() {
        setZoomX(getZoomX().multiply(new BigDecimal("1.2")));
        setZoomY(getZoomY().multiply(new BigDecimal("1.2")));
    }

    @Override
    public void zoomOut() {
        setZoomX(getZoomX().multiply(new BigDecimal("0.8")));
        setZoomY(getZoomY().multiply(new BigDecimal("0.8")));
    }

    @Override
    public void reduceXOffset(BigDecimal xVector) {
        setWorldXOffset(getWorldXOffset().subtract(xVector));
    }

    @Override
    public void reduceYOffset(BigDecimal yVector) {
        setWorldYOffset(getWorldYOffset().subtract(yVector));
    }

}
