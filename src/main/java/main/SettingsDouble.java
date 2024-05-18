package main.java.main;

import main.java.model.ImaginaryNumber;
import main.java.model.ImaginaryNumberDouble;

public class SettingsDouble extends SettingsGeneral<Double> {

    private final Wrapper<Double> mouseXPos = Wrapper.valueOf((double) 0, "Mouse X Pos");
    private final Wrapper<Double> mouseYPos = Wrapper.valueOf((double) 0, "Mouse Y Pos");
    private final Wrapper<Double> zoomX = Wrapper.valueOf(((double) (imageWidth.getValue() / 4)), "Zoom X");
    private final Wrapper<Double> zoomY = Wrapper.valueOf(zoomX.getValue(), "Zoom Y");
    private final Wrapper<Double> worldYOffset = Wrapper.valueOf((imageHeight.getValue() / zoomY.getValue() / 2), "World X Offset");
    private final Wrapper<Double> worldXOffset = Wrapper.valueOf((imageWidth.getValue() / zoomX.getValue() / 2), "World X Offset");


    private ImaginaryNumber<Double> nonVariable = new ImaginaryNumberDouble(0, 0);


    @Override
    public Double getMouseXPos() {
        return mouseXPos.getValue();
    }

    @Override
    public void setMouseXPos(Double mouseXPos) {
        this.mouseXPos.setValue(mouseXPos);
    }

    @Override
    public Double getMouseYPos() {
        return mouseYPos.getValue();
    }

    @Override
    public void setMouseYPos(Double mouseYPos) {
        this.mouseYPos.setValue(mouseYPos);
    }

    @Override
    public Double getZoomX() {
        return zoomX.getValue();
    }

    @Override
    public void setZoomX(Double zoomX) {
        this.zoomX.setValue(zoomX);
    }

    @Override
    public Double getZoomY() {
        return zoomY.getValue();
    }

    @Override
    public void setZoomY(Double zoomY) {
        this.zoomY.setValue(zoomY);
    }

    @Override
    public Double getWorldXOffset() {
        return worldXOffset.getValue();
    }

    @Override
    public void setWorldXOffset(Double worldXOffset) {
        this.worldXOffset.setValue(worldXOffset);
    }

    @Override
    public Double getWorldYOffset() {
        return worldYOffset.getValue();
    }

    @Override
    public void setWorldYOffset(Double worldYOffset) {
        this.worldYOffset.setValue(worldYOffset);
    }

    @Override
    public ImaginaryNumber<Double> getNonVariable() {
        return nonVariable;
    }

    @Override
    public void setNonVariable(ImaginaryNumber<Double> nonVariable) {
        this.nonVariable = nonVariable;
    }

    @Override
    public Wrapper<Double> getMouseXPosWrapper() {
        return mouseXPos;
    }

    @Override
    public Wrapper<Double> getMouseYPosWrapper() {
        return mouseYPos;
    }

    @Override
    public Wrapper<Double> getWorldXOffsetWrapper() {
        return worldXOffset;
    }

    @Override
    public Wrapper<Double> getWorldYOffsetWrapper() {
        return worldYOffset;
    }

    @Override
    public Wrapper<Double> getZoomXWrapper() {
        return zoomX;
    }

    @Override
    public Wrapper<Double> getZoomYWrapper() {
        return zoomY;
    }

    @Override
    public void zoomIn() {
        setZoomX(getZoomX() * ((1.2)));
        setZoomY(getZoomY() * ((1.2)));
    }

    @Override
    public void zoomOut() {
        setZoomX(getZoomX() * (0.8));
        setZoomY(getZoomY() * (0.8));
    }

    @Override
    public void reduceXOffset(Double xVector) {
        setWorldXOffset(getWorldXOffset() - (xVector));
    }

    @Override
    public void reduceYOffset(Double yVector) {
        setWorldYOffset(getWorldYOffset() - (yVector));
    }

}
