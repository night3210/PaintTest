package com.paint.paint.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;


public abstract class PanelView extends LinearLayout {
    public PanelView(Context context) {
        this(context, null);
    }

    public PanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        inflate(getContext(), getLayoutId(), this);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        onInit();
    }

    protected abstract void onInit();

    public void show() {
        setVisibility(VISIBLE);
    }

    public void hide() {
        setVisibility(INVISIBLE);
    }

    public boolean isActive() {
        return getVisibility() == VISIBLE;
    }

    protected abstract int getLayoutId();
}
