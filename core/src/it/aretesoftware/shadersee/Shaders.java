package it.aretesoftware.shadersee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.LoadFragmentShaderEvent;
import it.aretesoftware.shadersee.event.shader.LoadVertexShaderEvent;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;

public class Shaders {

    private final Main main;
    private ShaderProgram shader;
    private FileHandle vert, frag;

    Shaders(Main main) {
        this.main = main;
        addListeners();
    }

    private void addListeners() {
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
            newShader.dispose();
            return;
        }
        if (shader != null) {
            shader.dispose();
        }
        shader = newShader;
        this.vert = vert;
        this.frag = frag;
        main.fire(new ShaderLoadEvent(shader, vert, frag));
    }

    ShaderProgram getShader() {
        return shader;
    }

}
