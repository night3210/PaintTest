package com.paint.paint.panel;


public interface SettingActionsListener {
    void undo();

    void redo();

    void clear();

    void updateStrokeWith(float value);
}
