package it.aretesoftware.shadersee.menu;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.Shaders;
import it.aretesoftware.shadersee.dialog.DialogWelcome;
import it.aretesoftware.shadersee.event.shader.LoadShaderEvent;

public class FileMenu extends Menu {

    public FileMenu(Main main) {
        super("File");

        MenuItem importMenuItem = new MenuItem("Import...");
        importMenuItem.setSubMenu(new ImportSubMenu(main));

        MenuItem refreshMenuItem = new MenuItem("Refresh");
        refreshMenuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Shaders shaders = main.getShaders();
                FileHandle vert = getFile(shaders.getVertexShaderFileHandle());
                FileHandle frag = getFile(shaders.getFragmentShaderFileHandle());
                main.fire(new LoadShaderEvent(vert, frag));
            }

            private FileHandle getFile(FileHandle fileHandle) {
                return fileHandle.type() == Files.FileType.Internal ? fileHandle : Gdx.files.absolute(fileHandle.file().getAbsolutePath());
            }
        });

        MenuItem welcomeScreenMenuItem = new MenuItem("Welcome Screen...");
        welcomeScreenMenuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DialogWelcome dialog = new DialogWelcome(main);
                main.getStage().addActor(dialog.fadeIn());
            }
        });

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        addItem(importMenuItem);
        addItem(refreshMenuItem);
        addItem(welcomeScreenMenuItem);
        addItem(exitMenuItem);
    }
}
