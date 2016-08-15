package com.paintlib.canvas.view.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.paintlib.R;
import com.paintlib.base.PaintSelectableTool;
import com.paintlib.canvas.tool.ToolKey;



public class PaintToolSelectView extends ImageButton implements PaintSelectableTool<ToolKey> {
    private ToolKey toolKey;

    public PaintToolSelectView(Context context) {
        this(context, null);
    }

    public PaintToolSelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintToolSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PaintToolSelectView, 0, 0);
        toolKey = ToolKey.getToolKey(typedArray.getColor(R.styleable.PaintToolSelectView_paintTool, 0));
        typedArray.recycle();
    }

    @Override
    public ToolKey getToolValue() {
        return toolKey;
    }
}
