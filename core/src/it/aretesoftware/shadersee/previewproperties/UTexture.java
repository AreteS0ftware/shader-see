package it.aretesoftware.shadersee.previewproperties;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.SetUTextureEvent;
import it.aretesoftware.shadersee.utils.Utils;

public class UTexture extends Table {

    private final VisTextField uTextureFilePathTextField;

    UTexture(Main main) {
        uTextureFilePathTextField = createUTextureFilePathTextField(main);

        add(new VisLabel("u_texture")).expandX().center().colspan(2);
        row();
        defaults().space(10);
        add(uTextureFilePathTextField).growX();
        add(createBrowseTextButton(main));
    }

    private VisTextField createUTextureFilePathTextField(Main main) {
        VisTextField textField = new VisTextField(main.getShaders().getUTextureFilePath());
        textField.setDisabled(true);
        return textField;
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
                Texture texture = new Texture(fileHandle);
                main.fire(new SetUTextureEvent(texture, fileHandle));
                uTextureFilePathTextField.setText(fileHandle.file().getAbsolutePath());
            }
        });
        return textButton;
    }

}
