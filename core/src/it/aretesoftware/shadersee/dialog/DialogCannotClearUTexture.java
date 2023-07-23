package it.aretesoftware.shadersee.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;

public class DialogCannotClearUTexture extends VisDialog {

    public DialogCannotClearUTexture() {
        super("Cannot clear u_texture!");

        setCenterOnAdd(true);
        setResizable(false);
        setSize(400, 225);

        Table contentTable = getContentTable();
        contentTable.add(new VisLabel("'uniform sampler2D u_texture' cannot be removed."));

        addCloseButton();
    }

}
