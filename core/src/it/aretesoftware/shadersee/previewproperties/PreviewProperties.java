package it.aretesoftware.shadersee.previewproperties;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;

import it.aretesoftware.shadersee.Main;

public class PreviewProperties extends Table {

    public PreviewProperties(Main main) {
        Table contentTable = new Table();
        contentTable.defaults().padLeft(20).padRight(10);
        contentTable.add(new UTexture(main)).padTop(15).growX();

        VisLabel previewPropertiesLabel = new VisLabel("Preview Properties", "title");
        VisScrollPane scrollPane = new VisScrollPane(contentTable);
        clear();
        add(previewPropertiesLabel).top();
        row();
        add(scrollPane).expand().fillX().top();
    }

}
