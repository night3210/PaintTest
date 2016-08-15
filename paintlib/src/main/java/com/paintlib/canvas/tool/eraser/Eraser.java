package com.paintlib.canvas.tool.eraser;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.paintlib.canvas.base.DrawingTool;


public class Eraser extends DrawingTool<EraserSettings> {
    @Override
    public Paint createPaint() {
        Paint paint = super.createPaint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setColor(settings.getBaseColor());
        return paint;
    }

    @Override
    protected EraserSettings createSettings() {
        return new EraserSettings();
    }
}
