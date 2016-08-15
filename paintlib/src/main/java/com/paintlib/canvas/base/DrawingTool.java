package com.paintlib.canvas.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.paintlib.canvas.DrawAction;
import com.paintlib.canvas.PaintCanvasView;

import java.util.ArrayList;


public abstract class DrawingTool<T extends PaintSettings> implements IDrawingTool {
    protected final T settings;

    public DrawingTool() {
        settings = createSettings();
    }

    @Override
    public void onActionMove(DrawAction action, MotionEvent motionEvent) {
        action.getPath().lineTo(motionEvent.getX(), motionEvent.getY());
    }

    @Override
    public void onActionDown(MotionEvent motionEvent) {
    }

    @Override
    public void onActionUp(MotionEvent motionEvent) {
    }

    @Override
    public Paint createPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(settings.getPaintStyle());
        paint.setStrokeWidth(settings.getStrokeWidth());
        paint.setStrokeCap(settings.getLineCap());
        paint.setStrokeJoin(settings.getStrokeJoin());
        return paint;
    }

    public T getSettings() {
        return settings;
    }

    protected abstract T createSettings();

    public void draw(Canvas canvas, ArrayList<PointF> points) {
    }

    public void setup(PaintCanvasView paintCanvasView) {
    }
}
