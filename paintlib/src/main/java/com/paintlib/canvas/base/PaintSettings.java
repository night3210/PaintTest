package com.paintlib.canvas.base;

import android.graphics.Color;
import android.graphics.Paint;


public abstract class PaintSettings {
    public static final float DEFAULT_PAINT_STROKE_WIDTH = 3f;

    protected Paint.Style paintStyle = Paint.Style.STROKE;
    protected int baseColor = Color.TRANSPARENT;
    protected float strokeWidth = DEFAULT_PAINT_STROKE_WIDTH;
    protected int opacity = 255;
    protected float blurRadius;
    protected int strokeColor = Color.BLACK;

    protected Paint.Cap lineCap = Paint.Cap.ROUND;
    protected Paint.Join strokeJoin = Paint.Join.MITER;

    public Paint.Style getPaintStyle() {
        return paintStyle;
    }

    public void setStrokeColor(int color) {
        strokeColor = color;
    }

    public void setPaintStyle(Paint.Style paintStyle) {
        this.paintStyle = paintStyle;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float width) {
        if (width >= 0) {
            strokeWidth = width;
        } else {
            strokeWidth = DEFAULT_PAINT_STROKE_WIDTH;
        }
    }

    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        if ((opacity >= 0) && (opacity <= 255)) {
            this.opacity = opacity;
        } else {
            this.opacity = 255;
        }
    }

    public float getBlurRadius() {
        return blurRadius;
    }

    public void setBlurRadius(float blurRadius) {
        if (blurRadius >= 0) {
            this.blurRadius = blurRadius;
        } else {
            this.blurRadius = 0f;
        }
    }

    public Paint.Cap getLineCap() {
        return lineCap;
    }

    public void setLineCap(Paint.Cap lineCap) {
        this.lineCap = lineCap;
    }

    public int getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(int baseColor) {
        this.baseColor = baseColor;
    }

    public Paint.Join getStrokeJoin() {
        return strokeJoin;
    }

    public void setStrokeJoin(Paint.Join strokeJoin) {
        this.strokeJoin = strokeJoin;
    }
}
