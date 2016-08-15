package com.paintlib.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

import com.paintlib.canvas.base.DrawingTool;

import java.util.ArrayList;


public class DrawAction {
    private Paint paint;
    private Path path;
    private DrawingTool tool;
    private ArrayList<PointF> points;

    public DrawAction(Path path, Paint paint, DrawingTool tool) {
        this.path = path;
        this.paint = paint;
        this.tool = tool;
    }

    public Paint getPaint() {
        return paint;
    }

    public Path getPath() {
        return path;
    }

    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
        if(points!=null && points.size()>0)
            tool.draw(canvas,points);
    }

    public ArrayList<PointF> getPoints() {
        if(points==null)
            points=new ArrayList<>(100);
        return points;
    }
}
