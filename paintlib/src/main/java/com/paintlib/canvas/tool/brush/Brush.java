package com.paintlib.canvas.tool.brush;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;

import com.paintlib.R;
import com.paintlib.canvas.DrawAction;
import com.paintlib.canvas.PaintCanvasView;
import com.paintlib.canvas.base.DrawingTool;

import java.util.ArrayList;


//TODO
public class Brush extends DrawingTool<BrushSettings> {
    private float prevX;
    private float prevY;
    private float TOUCH_TOLERANCE = 5;

    @Override
    public Paint createPaint() {
        Paint paint = super.createPaint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(getSettings().getStrokeColor());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(getSettings().getStrokeWidth());
        paint.setAlpha(0x80);
        return paint;
    }

    @Override
    protected BrushSettings createSettings() {
        return new BrushSettings();
    }

    @Override
    public void onActionUp(MotionEvent motionEvent) {
    }

    @Override
    public void onActionDown(MotionEvent motionEvent) {
        prevY = motionEvent.getY();
        prevX = motionEvent.getX();
    }
    public void log(String log) {
        Log.d("TAGz)",log);
    }
    // this function check if current point more than size(x/y) far from previous point, it add additional points
    @Override
    public void onActionMove(DrawAction path, MotionEvent motionEvent) {
        if(bmp==null || path.getPoints().size()==0) {
            path.getPoints().add(new PointF(motionEvent.getX(), motionEvent.getY()));
        }else{
            int sizex = bmp.getWidth()*3;
            int sizey = bmp.getHeight()*3;
            PointF lastpoint = path.getPoints().get(path.getPoints().size()-1);
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            float absdistx = Math.abs(lastpoint.x-x);
            float absdisty = Math.abs(lastpoint.y-y);
            if(absdistx<(float)sizex/3 && absdisty<(float)sizey/3)
                return;
            if(absdistx>sizex ||
                    absdisty>sizey) {

                float distx = x-lastpoint.x;
                float disty = y-lastpoint.y;
                float beginx = lastpoint.x;
                float beginy = lastpoint.y;

                /*if(absdistx>absdisty)
                    log("distanceX bigger");
                else
                    log("distanceY bigger");*/

                int directionX = (distx>0?1:-1);
                int directionY = (disty>0?1:-1);
                // X increment is by default is width. if movement is vertical then x increment is movement
                float addx = sizex*directionX;
                if(absdisty>absdistx) {
                    addx = absdistx/(absdisty/sizey)*directionX;;
                }
                float addy = sizey*directionY;

                if(absdistx>absdisty) {
                    addy = absdisty/(absdistx/sizex)*directionY;
                }


                int c=0;
                while(true) {
                    if(absdistx > absdisty) {
                        if (distx > 0 && beginx > x ||
                                distx < 0 && beginx < x)
                            break;
                    }else {
                        if (disty > 0 && beginy > y ||
                                disty < 0 && beginy < y)
                            break;
                    }
                    path.getPoints().add(new PointF(beginx, beginy));
//                    log(" add point bx/by "+beginx+"/"+beginy+ ": "+addx+"/"+addy);
                    beginx += addx;
                    beginy += addy;
                }
            }else {
                path.getPoints().add(new PointF(motionEvent.getX(), motionEvent.getY()));
            }
        }
    }

    @Override
    public void draw(Canvas canvas, ArrayList<PointF> points) {
        PointF previousPoint=null;
        float angle = 0;
        float deltaY = 0;
        float deltaX = 0;
        for(PointF point:points) {
            Matrix m = new Matrix();
            if(previousPoint!=null) {
                deltaY = point.y-previousPoint.y;
                deltaX = point.x-previousPoint.x;
                if(deltaX!=0 && deltaY!=0) {
                    angle = (float) (Math.abs(Math.atan2(deltaY, deltaX) * 180 / Math.PI));
                    if (deltaY == 0)
                        angle = deltaX < 0 ? 0 : 180;
                    if (deltaX == 0)
                        angle = 90;
                }
            } else {
                previousPoint = point;
                if(points.size()>1)
                    continue;
            }
            if(deltaX==0 && deltaY==0)
                continue;
            log("angle2 p1="+(int)previousPoint.x+"/"+(int)previousPoint.y+
                    " p2="+(int)point.x+"/"+(int)point.y+
                    " delta x/y="+(int)deltaX+"/"+(int)deltaY+
                    " angle="+angle);

            m.setScale(3,3);
            m.postRotate(angle,10,10);
            m.postTranslate(point.x, point.y);
            canvas.drawBitmap(bmp, m, null);
            previousPoint=point;
        }
    }
Bitmap bmp;
    @Override
    public void setup(PaintCanvasView paintCanvasView) {
        bmp = BitmapFactory.decodeResource(paintCanvasView.getResources(), R.drawable.brush_chain);
    }
}
