package com.paintlib.canvas.tool;

import com.paintlib.canvas.base.DrawingTool;
import com.paintlib.canvas.tool.brush.Brush;
import com.paintlib.canvas.tool.eraser.Eraser;
import com.paintlib.canvas.tool.pen.Pen;


public enum ToolKey {
    PEN {
        @Override
        public DrawingTool createTool() {
            return new Pen();
        }
    },
    BRUSH {
        @Override
        public DrawingTool createTool() {
            return new Brush();
        }
    },
    ERASER {
        @Override
        public DrawingTool createTool() {
            return new Eraser();
        }
    };

    public abstract DrawingTool createTool();

    public static ToolKey getToolKey(Integer index) {
        if (index == null || index < 0 || index > values().length - 1) {
            return values()[0];
        } else return values()[index];
    }

    public static ToolKey getDefault() {
        return PEN;
    }
}
