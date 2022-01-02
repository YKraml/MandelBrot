package main.java.main;

import main.java.model.ImaginaryNumber;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Settings {

    private static int PRECISION = 25;
    private static int MAX_DEPTH = 100;
    private static MathContext MATH_CONTEXT = new MathContext(PRECISION);
    private static int POINTS_TO_CHECK = 9;

    private static int WIDTH = 700;
    private static int HEIGHT = 500;

    private static BigDecimal mouseXPos = BigDecimal.ZERO;
    private static BigDecimal mouseYPos = BigDecimal.ZERO;

    private static BigDecimal zoomX = new BigDecimal(WIDTH / 4);
    private static BigDecimal zoomY = zoomX;

    private static BigDecimal worldXOffset = new BigDecimal(WIDTH / zoomX.doubleValue() / 2);
    private static BigDecimal worldYOffset = new BigDecimal(HEIGHT / zoomY.doubleValue() / 2);

    private static boolean changed = true;

    private static final int RATIO = (WIDTH + HEIGHT) / 200;

    private static boolean cIsVariable = true;
    private static ImaginaryNumber nonVariable = new ImaginaryNumber(0,0);


    public static int getPRECISION() {
        return PRECISION;
    }

    public static int getMaxDepth() {
        return MAX_DEPTH;
    }

    public static MathContext getMathContext() {
        return MATH_CONTEXT;
    }

    public static int getPointsToCheck() {
        return POINTS_TO_CHECK;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static BigDecimal getMouseXPos() {
        return mouseXPos;
    }

    public static BigDecimal getMouseYPos() {
        return mouseYPos;
    }

    public static BigDecimal getZoomX() {
        return zoomX;
    }

    public static BigDecimal getZoomY() {
        return zoomY;
    }

    public static BigDecimal getWorldXOffset() {
        return worldXOffset;
    }

    public static BigDecimal getWorldYOffset() {
        return worldYOffset;
    }

    public static boolean isChanged() {
        return changed;
    }

    public static int getRATIO() {
        return RATIO;
    }

    public static boolean isCVariable() {
        return cIsVariable;
    }

    public static ImaginaryNumber getNonVariable() {
        return nonVariable;
    }

    public static void setPRECISION(int PRECISION) {
        Settings.PRECISION = PRECISION;
    }

    public static void setMaxDepth(int maxDepth) {
        MAX_DEPTH = maxDepth;
    }

    public static void setMathContext(MathContext mathContext) {
        MATH_CONTEXT = mathContext;
    }

    public static void setPointsToCheck(int pointsToCheck) {
        POINTS_TO_CHECK = pointsToCheck;
    }

    public static void setWIDTH(int WIDTH) {
        Settings.WIDTH = WIDTH;
    }

    public static void setHEIGHT(int HEIGHT) {
        Settings.HEIGHT = HEIGHT;
    }

    public static void setMouseXPos(BigDecimal mouseXPos) {
        Settings.mouseXPos = mouseXPos;
    }

    public static void setMouseYPos(BigDecimal mouseYPos) {
        Settings.mouseYPos = mouseYPos;
    }

    public static void setZoomX(BigDecimal zoomX) {
        Settings.zoomX = zoomX;
    }

    public static void setZoomY(BigDecimal zoomY) {
        Settings.zoomY = zoomY;
    }

    public static void setWorldXOffset(BigDecimal worldXOffset) {
        Settings.worldXOffset = worldXOffset;
    }

    public static void setWorldYOffset(BigDecimal worldYOffset) {
        Settings.worldYOffset = worldYOffset;
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
}
