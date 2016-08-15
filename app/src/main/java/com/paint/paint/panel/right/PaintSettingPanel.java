package com.paint.paint.panel.right;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.paintlib.canvas.base.DrawingTool;
import com.paint.R;
import com.paint.paint.panel.PanelView;
import com.paint.paint.panel.SettingActionsListener;


public class PaintSettingPanel extends PanelView {
    private View undoButton;
    private View redoButton;
    private View clearButton;
    private SeekBar strokeWidthSeekBar;
    private TextView strokeWithLabelView;

    private SettingActionsListener listener;

    public PaintSettingPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintSettingPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onInit() {
        undoButton = findViewById(R.id.artist_setting_paint_button_undo);
        redoButton = findViewById(R.id.artist_setting_paint_button_redo);
        clearButton = findViewById(R.id.artist_setting_paint_button_clear);
        strokeWidthSeekBar = (SeekBar) findViewById(R.id.artist_setting_paint_stroke_width_seekbar);
        strokeWithLabelView = (TextView) findViewById(R.id.artist_setting_paint_stroke_width_label);

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.undo();
                }
            }
        });
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.redo();
                }
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clear();
                }
            }
        });
        strokeWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                strokeWithLabelView.setText("stroke width:  " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (listener != null) {
                    listener.updateStrokeWith(seekBar.getProgress());
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.panel_right_paint_settings;
    }

    public void setListener(SettingActionsListener listener) {
        this.listener = listener;
    }

    //TODO
    public void update(DrawingTool selectedTool) {
        int strokeWith = (int) selectedTool.getSettings().getStrokeWidth();
        strokeWidthSeekBar.setProgress(strokeWith);
        strokeWithLabelView.setText("stroke width:  " + strokeWith);
    }
}
