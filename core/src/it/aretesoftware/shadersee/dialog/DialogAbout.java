package it.aretesoftware.shadersee.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.LinkLabel;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;

public class DialogAbout extends VisDialog {

    public DialogAbout() {
        super("About");

        setSize(500, 225);
        setCenterOnAdd(true);
        addCloseButton();

        Table contentTable = getContentTable();
        contentTable.defaults().padTop(5);
        contentTable.add(new VisLabel("Shader See is developed by Arete for the libGDX community."));
        contentTable.row();
        contentTable.add(new VisLabel("Version 0.1.0"));
        contentTable.row();
        contentTable.add(new VisLabel("Copyright © Arete 2023"));
        contentTable.row();
        contentTable.add(new LinkLabel("https://github.com/AreteS0ftware/shader-see"));
    }

}
