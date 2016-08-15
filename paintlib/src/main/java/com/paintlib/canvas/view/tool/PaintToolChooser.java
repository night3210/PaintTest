package com.paintlib.canvas.view.tool;


import com.paintlib.base.PaintChooser;
import com.paintlib.base.ToolSelectListener;

import java.util.List;


public class PaintToolChooser extends PaintChooser<PaintToolSelectView> {
    public PaintToolChooser(ToolSelectListener<PaintToolSelectView> listener, List<PaintToolSelectView> toolsList) {
        super(listener, toolsList);
    }
}
