package it.aretesoftware.shadersee.dialog;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTextButton;

import it.aretesoftware.shadersee.utils.Utils;

public class DialogShaderLoadError extends VisDialog {

    public DialogShaderLoadError(String log, FileHandle vert, FileHandle frag) {
        super("Cannot read Shader!");
        populateContentTable(log, vert, frag);
        populateButtonsTable(log);
        settings();
    }

    private void populateContentTable(String log, FileHandle vert, FileHandle frag) {
        VisLabel vertFilePathLabel = new VisLabel(vert.file().getAbsolutePath());
        VisLabel fragFilePathLabel = new VisLabel(frag.file().getAbsolutePath());
        VisScrollPane logLabelScrollPane = new VisScrollPane(new VisLabel(log));

        Table contentTable = getContentTable();
        contentTable.defaults().padTop(5);
        contentTable.add("The following files could not be loaded").padTop(15);
        contentTable.row();
        contentTable.add(vertFilePathLabel);
        contentTable.row();
        contentTable.add(fragFilePathLabel);
        contentTable.row();
        contentTable.add("Log").padTop(20);
        contentTable.row();
        contentTable.add(logLabelScrollPane).padBottom(10);
    }

    private void populateButtonsTable(String log) {
        VisTextButton printToFile = new VisTextButton("Print to file");
        printToFile.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                FileHandle fileHandle = Utils.saveFile(log, "txt");
                if (fileHandle == null) return;
                Utils.desktopOpenFile(fileHandle);
            }
        });

        VisTextButton okButton = new VisTextButton("OK");
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                fadeOut();
            }
        });

        Table buttonsTable = getButtonsTable();
        buttonsTable.add(printToFile);
        buttonsTable.add(okButton);
    }

    private void settings() {
        Table contentTable = getContentTable();
        setCenterOnAdd(true);
        setResizable(true);
        setSize(Math.min(600, contentTable.getPrefWidth() + 60), Math.min(600, contentTable.getPrefHeight() + 100));
        addCloseButton();
    }


}
