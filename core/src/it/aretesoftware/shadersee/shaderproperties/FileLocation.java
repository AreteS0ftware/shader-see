package it.aretesoftware.shadersee.shaderproperties;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.utils.Utils;

public abstract class FileLocation extends Table {

    private final VisTextField filePathTextField;

    FileLocation(Main main) {
        filePathTextField = new VisTextField();
        filePathTextField.setDisabled(true);

        defaults().space(10);
        add(new VisLabel(getTitleText())).colspan(2);
        row();
        add(filePathTextField).growX();
        add(createBrowseTextButton(main));

    }

    public void setFilePath(String filePath) {
        filePathTextField.setText(filePath);
    }

    private VisTextButton createBrowseTextButton(Main main) {
        VisTextButton textButton = new VisTextButton("Browse");
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                FileHandle fileHandle = Utils.openFile();
                if (fileHandle == null) {
                    return;
                }
                main.fire(getLoadShaderEvent(fileHandle));
            }
        });
        return textButton;
    }

    protected abstract Event getLoadShaderEvent(FileHandle fileHandle);

    protected abstract String getTitleText();

}
