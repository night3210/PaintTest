package com.paintlib.canvas.base;

import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

import com.paintlib.canvas.DrawAction;


public interface IDrawingTool {
    Paint createPaint();

    void onActionMove(DrawAction action, MotionEvent motionEvent);

    void onActionDown(MotionEvent motionEvent);

    void onActionUp(MotionEvent motionEvent);
}
