package it.aretesoftware.shadersee.shaderproperties;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

public class FileLocation extends Table {

    private final VisTextField filePathTextField;

    public FileLocation(String text) {
        filePathTextField = new VisTextField();
        filePathTextField.setDisabled(true);

        defaults().space(10);
        add(new VisLabel("Shader")).colspan(2);
        row();
        add(new VisLabel(text));
        add(filePathTextField).growX();
    }

    public void setFilePath(String filePath) {
        filePathTextField.setText(filePath);
    }

}
