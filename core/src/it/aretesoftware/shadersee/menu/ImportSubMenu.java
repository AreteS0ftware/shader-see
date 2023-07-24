package it.aretesoftware.shadersee.menu;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.LoadFragmentShaderEvent;
import it.aretesoftware.shadersee.event.shader.LoadVertexShaderEvent;
import it.aretesoftware.shadersee.utils.Utils;

public class ImportSubMenu extends PopupMenu {

    private final Main main;

    ImportSubMenu(Main main) {
        this.main = main;
        addItem(createImportVertexShaderMenuItem());
        addItem(createImportFragmentShaderMenuItem());
        addItem(createImportShaderProgramMenuItem());
    }

    private MenuItem createImportVertexShaderMenuItem() {
        MenuItem item = new MenuItem("Import Vertex Shader");
        item.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FileHandle fileHandle = Utils.openFile();
                if (fileHandle == null) {
                    return;
                }
                main.fire(new LoadVertexShaderEvent(fileHandle));
            }
        });
        return item;
    }

    private MenuItem createImportFragmentShaderMenuItem() {
        MenuItem item = new MenuItem("Import Fragment Shader");
        item.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FileHandle fileHandle = Utils.openFile();
                if (fileHandle == null) {
                    return;
                }
                main.fire(new LoadFragmentShaderEvent(fileHandle));
            }
        });
        return item;
    }

    private MenuItem createImportShaderProgramMenuItem() {
        MenuItem item = new MenuItem("Import Shader Program");
        return item;
    }
}
