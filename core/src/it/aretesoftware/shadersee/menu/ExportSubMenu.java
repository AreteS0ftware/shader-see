package it.aretesoftware.shadersee.menu;

import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;

import it.aretesoftware.shadersee.Main;

public class ExportSubMenu extends PopupMenu {

    ExportSubMenu(Main main) {
        addItem(createExportVertexShaderMenuItem());
        addItem(createExportFragmentShaderMenuItem());
        addItem(createExportShaderProgramMenuItem());
    }

    private MenuItem createExportVertexShaderMenuItem() {
        MenuItem item = new MenuItem("Export Vertex Shader");
        return item;
    }

    private MenuItem createExportFragmentShaderMenuItem() {
        MenuItem item = new MenuItem("Export Fragment Shader");
        return item;
    }

    private MenuItem createExportShaderProgramMenuItem() {
        MenuItem item = new MenuItem("Export Shader Program");
        return item;
    }
}
