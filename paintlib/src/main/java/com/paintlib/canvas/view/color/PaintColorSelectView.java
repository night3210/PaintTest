package com.paintlib.canvas.view.color;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.paintlib.base.PaintSelectableTool;



public class PaintColorSelectView extends FrameLayout implements PaintSelectableTool<Integer> {
    private int color;

    public PaintColorSelectView(Context context) {
        this(context, null);
    }

    public PaintColorSelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintColorSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        com.paintlib.canvas.view.color.PaintColorCircleView colorCircleView = new PaintColorCircleView(getContext());
        colorCircleView.setColor(color);
        addView(colorCircleView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        View checkBoxView = new View(getContext());
        //checkBoxView.setBackgroundResource();
        addView(checkBoxView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public Integer getToolValue() {
        return color;
    }
}
