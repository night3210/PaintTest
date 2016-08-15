package com.paintlib.canvas.view.color;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class PaintColorCircleView extends View {
    private Paint paint;

    public PaintColorCircleView(Context context) {
        this(context, null);
    }

    public PaintColorCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintColorCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = canvas.getWidth() / 2;
        float y = canvas.getHeight() / 2;
        canvas.drawCircle(x, y, Math.min(x, y), paint);
    }

    public void setColor(int color) {
        paint.setColor(color);
        invalidate();
    }
}
