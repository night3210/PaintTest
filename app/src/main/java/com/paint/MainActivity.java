package com.paint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.paint.paint.PaintDrawController;
import com.paint.paint.panel.PanelView;
import com.paint.paint.panel.left.MainPanelView;
import com.paint.paint.panel.left.PaintColorPanelView;
import com.paint.paint.panel.left.PaintToolPanelView;
import com.paint.paint.panel.right.PaintSettingPanel;
import com.paintlib.canvas.PaintCanvasView;

public class MainActivity extends AppCompatActivity {

    private PaintDrawController paintController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPaintController();
    }

    private void initPaintController() {
        paintController = new PaintDrawController(
                (PaintCanvasView) findViewById(R.id.paint_canvas_view),
                (MainPanelView) findViewById(R.id.main_panel),
                (PaintToolPanelView) findViewById(R.id.paint_tool_panel),
                (PaintColorPanelView) findViewById(R.id.paint_color_panel),
                (PaintSettingPanel) findViewById(R.id.settings_panel_paint),
                (PanelView) findViewById(R.id.main_right_panel)
        );
        paintController.setPaintActive(true);
    }

    @Override
    public void onBackPressed() {
        if (paintController.handleBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
