package com.paint.paint;

import android.view.View;
import android.widget.Toast;

import com.paint.paint.panel.ArtistBackListener;
import com.paintlib.base.PaintChooser;
import com.paintlib.base.ToolSelectListener;
import com.paintlib.canvas.PaintCanvasView;
import com.paintlib.canvas.base.DrawingTool;
import com.paintlib.canvas.tool.ToolKey;
import com.paintlib.canvas.view.color.PaintColorChooser;
import com.paintlib.canvas.view.color.PaintColorSelectView;
import com.paintlib.canvas.view.tool.PaintToolChooser;
import com.paintlib.canvas.view.tool.PaintToolSelectView;
import com.paint.paint.panel.PanelView;
import com.paint.paint.panel.left.MainPanelView;
import com.paint.paint.panel.left.PaintColorPanelView;
import com.paint.paint.panel.left.PaintToolPanelView;
import com.paint.paint.panel.right.PaintSettingPanel;
import com.paint.paint.panel.SettingActionsListener;

import java.util.HashMap;
import java.util.Map;


public class PaintDrawController {
    private final Map<ToolKey, DrawingTool> drawingTools = new HashMap<>();

    private PaintCanvasView canvasView;
    //left panels
    private PaintToolPanelView toolPanelPaintView;
    private PaintColorPanelView colorPanelPaintView;
    private MainPanelView toolPanelMain; //TODO make parent of PaintPanelView
    //right panels
    private PanelView settingPanelMain;
    private PaintSettingPanel settingPanelPaint;

    private PaintColorChooser paintColorChooser;
    private PaintChooser paintToolChooser;

    public PaintDrawController(PaintCanvasView canvasView,
                               MainPanelView toolPanelMain,
                               PaintToolPanelView toolPanelPaintView,
                               PaintColorPanelView colorPanelPaintView,
                               PaintSettingPanel settingPanelPaint,
                               PanelView settingPanelMain) {
        this.canvasView = canvasView;
        this.toolPanelPaintView = toolPanelPaintView;
        this.colorPanelPaintView = colorPanelPaintView;
        this.toolPanelMain = toolPanelMain;
        this.settingPanelMain = settingPanelMain;
        this.settingPanelPaint = settingPanelPaint;

        initDrawingTools();
        initToolPanelView();
        initColorPanelView();
        initSettingsPanel();

        toolPanelMain.setPaintButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePaintClick();
            }
        });
    }

    public void setPaintActive(boolean active){
        canvasView.setActive(active);
    }

    private void initDrawingTools() {
        for (ToolKey tool : ToolKey.values()) {
            drawingTools.put(tool, tool.createTool());
        }
        canvasView.setDrawingTool(drawingTools.get(ToolKey.getDefault()));
    }

    private void performBackFromPaintColorChooser() {
        toolPanelPaintView.show();
        colorPanelPaintView.hide();
    }

    private void performBackFromPaintTool() {
        settingPanelMain.show();
        settingPanelPaint.hide();

        toolPanelMain.show();
        toolPanelPaintView.hide();
        setPaintActive(false);
    }

    private void initToolPanelView() {
        toolPanelPaintView.setBackListener(new ArtistBackListener() {
            @Override
            public void onBackPressed() {
                performBackFromPaintTool();
            }
        });
        toolPanelPaintView.setChooseColorListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleColorChooseClick();
            }
        });
        paintToolChooser = new PaintToolChooser(new ToolSelectListener<PaintToolSelectView>() {
            @Override
            public void onToolSelected(PaintToolSelectView tool) {
                Toast.makeText(canvasView.getContext(), "ToolKey color selected: " + tool.getToolValue(), Toast.LENGTH_SHORT).show();
                DrawingTool selectedTool = drawingTools.get(tool.getToolValue());
                canvasView.setDrawingTool(selectedTool);
                settingPanelPaint.update(selectedTool);
            }
        }, toolPanelPaintView.getToolViewList());
    }

    private void initColorPanelView() {
        colorPanelPaintView.setBackListener(new ArtistBackListener() {
            @Override
            public void onBackPressed() {
                performBackFromPaintColorChooser();
            }
        });
        paintColorChooser = new PaintColorChooser(new ToolSelectListener<PaintColorSelectView>() {
            @Override
            public void onToolSelected(PaintColorSelectView tool) {
                Toast.makeText(canvasView.getContext(), "ToolKey color selected: " + tool.getToolValue(), Toast.LENGTH_SHORT).show();
                canvasView.getDrawingTool().getSettings().setStrokeColor(tool.getToolValue());
            }
        }, colorPanelPaintView.getToolViewList());
    }

    private void initSettingsPanel() {
        settingPanelPaint.setListener(new SettingActionsListener() {
            @Override
            public void undo() {
                canvasView.undo();
            }

            @Override
            public void redo() {
                canvasView.redo();
            }

            @Override
            public void clear() {
                canvasView.clear();
            }

            @Override
            public void updateStrokeWith(float value) {
                canvasView.getDrawingTool().getSettings().setStrokeWidth(value);
            }
        });
    }

    public boolean handleBackPressed() {
        if (colorPanelPaintView.isActive()) {
            performBackFromPaintColorChooser();
            return true;
        } else if (toolPanelPaintView.isActive()) {
            performBackFromPaintTool();
            return true;
        }
        return false;
    }

    protected void handleColorChooseClick() {
        toolPanelPaintView.hide();
        colorPanelPaintView.show();
    }

    public void handlePaintClick() {
        settingPanelMain.hide();
        settingPanelPaint.show();

        toolPanelMain.hide();
        toolPanelPaintView.show();
        setPaintActive(true);
    }
}
