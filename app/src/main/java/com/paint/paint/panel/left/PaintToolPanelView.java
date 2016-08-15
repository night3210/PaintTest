package com.paint.paint.panel.left;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.paintlib.canvas.view.tool.PaintToolSelectView;
import com.paint.R;
import com.paint.paint.panel.PaintPanelView;

import java.util.ArrayList;
import java.util.List;


public class PaintToolPanelView extends PaintPanelView<PaintToolSelectView> {

    public PaintToolPanelView(Context context) {
        super(context);
    }

    public PaintToolPanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintToolPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected int getLayoutId() {
        return R.layout.panel_left_paint_tools;
    }

    public void setChooseColorListener(View.OnClickListener onClickListener) {
        findViewById(R.id.artist_paint_color_picker_button).setOnClickListener(onClickListener);
    }

    public List<PaintToolSelectView> getToolViewList() {
        int toolsCount = getChildCount() - 1;
        List<PaintToolSelectView> tools = new ArrayList<>(toolsCount);
        for (int i = 1; i < toolsCount; i++) {
            tools.add((PaintToolSelectView) getChildAt(i));
        }
        return tools;
    }
}
