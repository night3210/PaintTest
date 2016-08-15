package com.paint.paint.panel.left;

import android.content.Context;
import android.util.AttributeSet;

import com.paintlib.canvas.view.color.PaintColorSelectView;
import com.paint.R;
import com.paint.paint.panel.PaintPanelView;


public class PaintColorPanelView extends PaintPanelView<PaintColorSelectView> {
    public PaintColorPanelView(Context context) {
        super(context);
    }

    public PaintColorPanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintColorPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected int getLayoutId() {
        return R.layout.panel_left_paint_colors;
    }


}
