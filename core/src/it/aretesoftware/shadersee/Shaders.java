package it.aretesoftware.shadersee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import it.aretesoftware.shadersee.dialog.DialogShaderLoadError;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.LoadFragmentShaderEvent;
import it.aretesoftware.shadersee.event.shader.LoadShaderEvent;
import it.aretesoftware.shadersee.event.shader.LoadVertexShaderEvent;
import it.aretesoftware.shadersee.event.shader.SetUTextureEvent;
import it.aretesoftware.shadersee.event.shader.ShaderProgramUpdateEvent;

public class Shaders {

    private final Main main;
    private final ShaderUniforms shaderUniforms;
    private ShaderProgram shader;
    private FileHandle vert, frag;

    private Texture u_texture;
    private FileHandle uTextureFileHandle;

    Shaders(Main main) {
        this.main = main;
        uTextureFileHandle = Gdx.files.internal("textures/badlogic.jpg");
        u_texture = new Texture(uTextureFileHandle);
        shaderUniforms = new ShaderUniforms(main);
        addListeners();
    }

    private void addListeners() {
        // Load Shader
        main.addPreListener(new EventListener<LoadShaderEvent>(LoadShaderEvent.class, this) {
            @Override
            protected void fire(LoadShaderEvent event) {
                loadShader(event.vert, event.frag);
            }
        });
        main.addPreListener(new EventListener<LoadVertexShaderEvent>(LoadVertexShaderEvent.class, this) {
            @Override
            protected void fire(LoadVertexShaderEvent event) {
                loadShader(event.vert, frag);
            }
        });
        main.addPreListener(new EventListener<LoadFragmentShaderEvent>(LoadFragmentShaderEvent.class, this) {
            @Override
            protected void fire(LoadFragmentShaderEvent event) {
                loadShader(vert, event.frag);
            }
        });
        // SetUTextureEvent
        main.addPreListener(new EventListener<SetUTextureEvent>(SetUTextureEvent.class, this) {
            @Override
            protected void fire(SetUTextureEvent event) {
                u_texture.dispose();
                u_texture = event.u_texture;
                uTextureFileHandle = event.fileHandle;
            }
        });
    }

    //

    void loadDefaultShader() {
        FileHandle vert = Gdx.files.internal("shaders/default.vert");
        FileHandle frag = Gdx.files.internal("shaders/default.frag");
        loadShader(vert, frag);
    }

    void loadShader(FileHandle vert, FileHandle frag) {
        ShaderProgram newShader = new ShaderProgram(vert, frag);
        if (!newShader.isCompiled()) {
            DialogShaderLoadError dialog = new DialogShaderLoadError(newShader.getLog(), vert, frag);
            main.getStage().addActor(dialog.fadeIn());
            newShader.dispose();
            return;
        }
        if (shader != null) {
            shader.dispose();
        }
        shader = newShader;
        this.vert = vert;
        this.frag = frag;
        main.fire(new ShaderProgramUpdateEvent(shader, vert, frag));
    }

    public void bindShader(Batch batch) {
        batch.setShader(shader);
        shaderUniforms.setUniforms(shader);
    }

    public void unbindShader(Batch batch) {
        batch.setShader(null);
    }

    public Texture getUTexture() {
        return u_texture;
    }

    public String getUTextureFilePath() {
        return uTextureFileHandle.file().getAbsolutePath();
    }

    public ShaderProgram getShader() {
        return shader;
    }

    public FileHandle getVertexShaderFileHandle() {
        return vert;
    }

    public String getVertexShaderAbsoluteFilePath() {
        return vert.file().getAbsolutePath();
    }

    public FileHandle getFragmentShaderFileHandle() {
        return frag;
    }

    public String getFragmentShaderAbsoluteFilePath()  {
        return frag.file().getAbsolutePath();
    }

}
