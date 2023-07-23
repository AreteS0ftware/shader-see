package it.aretesoftware.shadersee.dialog;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisScrollPane;

public class DialogShowSampler2D extends VisDialog {

    public DialogShowSampler2D(String title, Texture texture) {
        super(title);
        setCenterOnAdd(true);
        setResizable(true);
        setSize(Math.min(600, texture.getWidth() + 60), Math.min(600, texture.getHeight() + 60));

        Table contentTable = getContentTable();
        VisScrollPane scrollPane = new VisScrollPane(new VisImage(texture));
        contentTable.add(scrollPane);

        addCloseButton();
    }

}
