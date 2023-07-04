package it.aretesoftware.shadersee.previewproperties;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;

public class UTexture extends Table {

    UTexture(Main main) {
        add(new VisLabel("u_texture")).expandX().center().colspan(2);
        row();
        defaults().space(10);
        add(new VisTextField("")).growX();
        add(new VisTextButton("Browse"));
    }


}
