package com.paintlib.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.paintlib.canvas.base.DrawingTool;

import java.util.ArrayList;
import java.util.List;


public class PaintCanvasView extends View {

    private List<DrawAction> drawActions = new ArrayList<>();
    private int historyPointer;
    private int undoCounter;

    private DrawingTool drawingTool;

    private float startX;
    private float startY;
    private boolean isDown;

    private boolean isActive;

    //all painting is on this canvas with the bitmap
    private Bitmap bitmap;
    private Canvas bitmapCanvas;

    //handlers of on draw actions
    private final DrawHandler DEFAULT = new DefaultDrawHandler();
    private final DrawHandler UNDO = new DrawHandler() {

        @Override
        protected void onDraw(Canvas canvas) {
            initBitmap();
            canvas.drawColor(getBaseColor());
            for (int i = 0; i < drawActions.size() - undoCounter; i++) {
                drawActions.get(i).draw(bitmapCanvas);
            }
            drawHandler = DEFAULT;
        }
    };

    private final DrawHandler DEFAULT_EXTERNAL = new DefaultDrawHandler() {

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawHandler = DEFAULT;
            historyPointer = drawActions.size() - undoCounter;
        }
    };

    private DrawHandler drawHandler = DEFAULT;

    public PaintCanvasView(Context context) {
        super(context);
    }

    public PaintCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawHandler.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isActive) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onActionDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                onActionMove(event);
                break;
            case MotionEvent.ACTION_UP:
                onActionUp(event);
                break;
            default:
                break;
        }
        return true;
    }

    private void onActionDown(MotionEvent event) {
        drawingTool.setup(this);
        updateHistory(new DrawAction(createPath(event), drawingTool.createPaint(), drawingTool));
        isDown = true;
        drawingTool.onActionDown(event);
        invalidate();
    }

    private void onActionMove(MotionEvent event) {
        if (!isDown) {
            return;
        }
        drawingTool.onActionMove(getCurrentHistory(), event);
        invalidate();
    }

    private void onActionUp(MotionEvent event) {
        if (isDown) {
            historyPointer = drawActions.size();
            startX = 0f;
            startY = 0f;
            isDown = false;
            drawingTool.onActionUp(event);
            invalidate();
        }
    }

    private void updateHistory(DrawAction drawAction) {
        if (historyPointer == drawActions.size()) {
            drawActions.add(drawAction);
        } else {
            // on the way of undo/redo
            undoCounter = 0;
            drawActions.set(historyPointer, drawAction);
            int clearIndex = historyPointer + 1;
            for (int i = clearIndex, size = drawActions.size(); i < size; i++) {
                drawActions.remove(clearIndex);
            }
        }
    }

    public boolean undo() {
        if (historyPointer > 0) {
            undoCounter++;
            historyPointer--;
            drawHandler = UNDO;
            invalidate();
            return true;
        } else {
            return false;
        }
    }

    public boolean redo() {
        if (historyPointer < drawActions.size()) {
            undoCounter--;
            drawHandler = DEFAULT_EXTERNAL;
            invalidate();
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        Path path = new Path();
        path.moveTo(0f, 0f);
        path.addRect(0f, 0f, getWidth(), getHeight(), Path.Direction.CCW);
        path.close();
        Paint clearPaint = new Paint();
        clearPaint.setColor(getBaseColor());
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        clearPaint.setStyle(Paint.Style.FILL);

        DrawAction clearDrawAction = new DrawAction(path, clearPaint, null);

        updateHistory(clearDrawAction);
        drawHandler = DEFAULT_EXTERNAL;
        invalidate();
    }

    private void initBitmap() {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
    }

    private Path createPath(MotionEvent event) {
        startX = event.getX();
        startY = event.getY();
        Path path = new Path();
        path.moveTo(startX, startY);
        return path;
    }

    private Path getCurrentPath() {
        return drawActions.get(historyPointer).getPath();
    }
    private DrawAction getCurrentHistory() {
        return drawActions.get(historyPointer);
    }

    private int getBaseColor() {
        return Color.TRANSPARENT;
    }

    public void setDrawingTool(DrawingTool drawingTool) {
        this.drawingTool = drawingTool;
    }

    public DrawingTool getDrawingTool() {
        return drawingTool;
    }

    /**
     * This method gets current canvas as bitmap.
     *
     * @return This is returned as bitmap.
     */
    public Bitmap getBitmap() {
        setDrawingCacheEnabled(false);
        setDrawingCacheEnabled(true);
        return Bitmap.createBitmap(getDrawingCache());
    }

    /**
     * This method gets current canvas as scaled bitmap.
     *
     * @return This is returned as scaled bitmap.
     */
    public Bitmap getScaleBitmap(int w, int h) {
        setDrawingCacheEnabled(false);
        setDrawingCacheEnabled(true);
        return Bitmap.createScaledBitmap(getDrawingCache(), w, h, true);
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    private abstract class DrawHandler {
        protected abstract void onDraw(Canvas canvas);
    }

    private class DefaultDrawHandler extends DrawHandler {
        @Override
        protected void onDraw(Canvas canvas) {
            if (bitmap == null) {
                initBitmap();
            }
            for (int i = historyPointer; i < drawActions.size() - undoCounter; i++) {
                drawActions.get(i).draw(bitmapCanvas);
            }
        }
    }
}
