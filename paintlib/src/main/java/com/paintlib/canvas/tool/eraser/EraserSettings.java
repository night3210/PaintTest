package com.paintlib.canvas.tool.eraser;


import android.graphics.Paint;

import com.paintlib.canvas.base.PaintSettings;


public class EraserSettings extends PaintSettings {
    public EraserSettings() {
        strokeWidth = 100;
        lineCap = Paint.Cap.SQUARE;
        strokeJoin = Paint.Join.BEVEL;
    }

    public void setStrokeWidth(float width) {
        if (width >= 0) {
            strokeWidth = width * 2;
        } else {
            strokeWidth = DEFAULT_PAINT_STROKE_WIDTH;
        }
    }
}
