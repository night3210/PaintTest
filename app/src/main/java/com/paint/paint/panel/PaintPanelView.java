package com.paint.paint.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.paintlib.base.PaintSelectableTool;
import com.paint.R;

import java.util.ArrayList;
import java.util.List;


public abstract class PaintPanelView<T extends View & PaintSelectableTool> extends PanelView {
    protected View backButton;
    protected ArtistBackListener backListener;

    public PaintPanelView(Context context) {
        super(context);
    }

    public PaintPanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onInit() {
        backButton = findViewById(getBackButtonId());
    }

    protected int getBackButtonId() {
        return R.id.artist_paint_back_button;
    }

    public void setBackListener(ArtistBackListener listener) {
        backListener = listener;
        if (backListener == null) {
            backButton.setOnClickListener(null);
        } else {
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backListener.onBackPressed();
                }
            });
        }
    }

    public List<T> getToolViewList() {
        int toolsCount = getChildCount() - 1;
        List<T> tools = new ArrayList<>(toolsCount);
        for (int i = 0; i < toolsCount; i++) {
            tools.add((T) getChildAt(i));
        }
        return tools;
    }
}
