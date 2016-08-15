package com.paintlib.canvas.view.color;


import com.paintlib.base.PaintChooser;
import com.paintlib.base.ToolSelectListener;

import java.util.List;


public class PaintColorChooser extends PaintChooser<PaintColorSelectView> {
    public PaintColorChooser(ToolSelectListener<PaintColorSelectView> listener, List<PaintColorSelectView> toolsList) {
        super(listener, toolsList);
    }
}
