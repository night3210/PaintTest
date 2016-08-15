package com.paintlib.base;

import android.view.View;

import java.util.ArrayList;
import java.util.List;


public abstract class PaintChooser<T extends View & PaintSelectableTool> implements View.OnClickListener {
    protected List<T> tools;
    protected T selectedTool;

    protected ToolSelectListener<T> toolSelectListener;

    public PaintChooser(ToolSelectListener<T> listener, List<T> toolsList) {
        this(toolsList);
        setListener(listener);
    }

    public PaintChooser(List<T> toolsList) {
        tools = toolsList;
        for (T tool : tools) {
            tool.setOnClickListener(this);
        }
    }

    public void addTool(T tool) {
        if (tools == null) {
            tools = new ArrayList<>();
        }
        tools.add(tool);
        tool.setOnClickListener(this);
    }

    public void setListener(ToolSelectListener<T> listener) {
        toolSelectListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (selectedTool != v) {
            if (selectedTool != null) {
                selectedTool.setSelected(false);
            }
            selectedTool = (T) v;
            selectedTool.setSelected(true);
            if (toolSelectListener != null) {
                toolSelectListener.onToolSelected(selectedTool);
            }
        }
    }
}
