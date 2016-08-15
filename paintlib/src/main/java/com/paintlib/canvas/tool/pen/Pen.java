package com.paintlib.canvas.tool.pen;

import android.graphics.Paint;

import com.paintlib.canvas.base.DrawingTool;


public class Pen extends DrawingTool<PenSettings> {
    @Override
    public Paint createPaint() {
        Paint paint = super.createPaint();
        paint.setColor(settings.getStrokeColor());
        paint.setShadowLayer(settings.getBlurRadius(), 0, 0, settings.getStrokeColor());
        paint.setAlpha(settings.getOpacity());
        return paint;
    }

    @Override
    protected PenSettings createSettings() {
        return new PenSettings();
    }
}
