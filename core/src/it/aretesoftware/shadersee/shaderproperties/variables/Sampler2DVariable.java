package it.aretesoftware.shadersee.shaderproperties.variables;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.dialog.DialogCannotClearUTexture;
import it.aretesoftware.shadersee.dialog.DialogShowSampler2D;
import it.aretesoftware.shadersee.event.shader.SetSampler2DUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetUTextureEvent;
import it.aretesoftware.shadersee.utils.Utils;

public class Sampler2DVariable extends Variable<Texture> {

    private VisLabel variableNameLabel;
    private VisTextField textureFilePathTextField;
    private VisTextButton browseTextButton, clearTextureTextButton, viewTextureTextButton;
    private FileHandle textureFileHandle;
    private Texture texture;

    Sampler2DVariable(VariableBuilder builder) {
        super(builder);
    }


    @Override
    protected void populate() {
        variableNameLabel = new VisLabel(getVariableName());
        textureFilePathTextField = createTextureFilePathTextField();
        browseTextButton = createBrowseTextButton();
        clearTextureTextButton = createClearTextureTextButton();
        viewTextureTextButton = createViewTextureButton();
        if (isUTexture()) {
            texture = getMain().getShaders().getUTexture();
            textureFileHandle = Gdx.files.absolute(getMain().getShaders().getUTextureFilePath());
        }
        rebuild();
    }

    @Override
    protected void setUniform(Texture value) {

    }

    private void rebuild() {
        clear();
        defaults().reset();
        defaults().space(7);
        add(variableNameLabel);
        add(textureFilePathTextField).growX();
        add(browseTextButton);
        row();
        if (texture == null) {
           return;
        }
        Table table = new Table();
        table.defaults().growX().space(7);
        if (!isUTexture()) {
            table.add(clearTextureTextButton);
        }
        table.add(viewTextureTextButton);
        add(table).growX().colspan(3);
    }

    private VisTextButton createClearTextureTextButton() {
        VisTextButton textButton = new VisTextButton("Clear Texture");
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                if (isUTexture()) {
                    DialogCannotClearUTexture dialog = new DialogCannotClearUTexture();
                    getMain().getStage().addActor(dialog.fadeIn());
                }
                else {
                    texture.dispose();
                    getMain().fire(new SetSampler2DUniformEvent(getVariableName(), texture, null));
                    texture = null;
                    textureFileHandle = null;
                    rebuild();
                }
            }
        });
        return textButton;
    }

    private VisTextButton createViewTextureButton() {
        VisTextButton viewTextureTextButton = new VisTextButton("View Texture");
        viewTextureTextButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                DialogShowSampler2D dialog = new DialogShowSampler2D(textureFileHandle.file().getAbsolutePath(), texture);
                getMain().getStage().addActor(dialog.fadeIn());
            }
        });
        return viewTextureTextButton;
    }

    private VisTextField createTextureFilePathTextField() {
        VisTextField textureFilePathTextField = new VisTextField("No texture selected!");
        textureFilePathTextField.setDisabled(true);
        if (isUTexture()) {
            textureFilePathTextField.setText(getMain().getShaders().getUTextureFilePath());
            getMain().addListener(new VariableEventListener<SetUTextureEvent>(SetUTextureEvent.class, this) {
                @Override
                protected void fire(SetUTextureEvent event) {
                    FileHandle fileHandle = event.fileHandle;
                    String absoluteFilePath = fileHandle != null ? fileHandle.file().getAbsolutePath() : "No texture selected!";
                    textureFilePathTextField.setText(absoluteFilePath);
                }
            });
        }
        else {
            getMain().addListener(new VariableEventListener<SetSampler2DUniformEvent>(SetSampler2DUniformEvent.class, this) {
                @Override
                protected void fire(SetSampler2DUniformEvent event) {
                    FileHandle fileHandle = event.fileHandle;
                    String absoluteFilePath = fileHandle != null ? fileHandle.file().getAbsolutePath() : "No texture selected!";
                    textureFilePathTextField.setText(absoluteFilePath);
                }
            });
        }
        return textureFilePathTextField;
    }

    private VisTextButton createBrowseTextButton() {
        VisTextButton textButton = new VisTextButton("Browse");
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                FileHandle fileHandle = Utils.openFile();
                if (fileHandle == null) {
                    return;
                }
                textureFileHandle = fileHandle;
                texture = new Texture(fileHandle);
                if (isUTexture()) {
                    getMain().fire(new SetUTextureEvent(texture, fileHandle));
                }
                else {
                    getMain().fire(new SetSampler2DUniformEvent(getVariableName(), texture, fileHandle));
                }
                rebuild();
            }
        });
        return textButton;
    }

    private boolean isUTexture() {
        return getVariableName().equals("u_texture");
    }

}
