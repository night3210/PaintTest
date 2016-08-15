package com.paint.paint.panel.left;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.paint.R;
import com.paint.paint.panel.PanelView;


public class MainPanelView extends PanelView {
    public MainPanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onInit() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.panel_left_main;
    }

    public void setPaintButtonClickListener(OnClickListener listener) {
        View view = findViewById(R.id.paint_button);
        view.setVisibility(VISIBLE);
        view.setOnClickListener(listener);
    }

}
