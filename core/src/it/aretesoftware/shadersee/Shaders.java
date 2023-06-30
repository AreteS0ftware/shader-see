package it.aretesoftware.shadersee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ObjectMap;

import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.LoadFragmentShaderEvent;
import it.aretesoftware.shadersee.event.shader.LoadVertexShaderEvent;
import it.aretesoftware.shadersee.event.shader.SetBoolUniform;
import it.aretesoftware.shadersee.event.shader.SetFloatUniform;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;

public class Shaders {

    private final Main main;
    private final ObjectMap<String, Float> floatUniforms;
    private final ObjectMap<String, Boolean> boolUniforms;
    private ShaderProgram shader;
    private FileHandle vert, frag;

    Shaders(Main main) {
        this.main = main;
        floatUniforms = new ObjectMap<>();
        boolUniforms = new ObjectMap<>();
        addListeners();
    }

    private void addListeners() {
        // Load Shader
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

        // Set Uniforms
        main.addPreListener(new EventListener<SetFloatUniform>(SetFloatUniform.class, this) {
            @Override
            protected void fire(SetFloatUniform event) {
                floatUniforms.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetBoolUniform>(SetBoolUniform.class, this) {
            @Override
            protected void fire(SetBoolUniform event) {
                boolUniforms.put(event.uniformName, event.uniformValue);
            }
        });
    }

    //

    void loadDefaultShader() {
        FileHandle vert = Gdx.files.internal("shaders/default.vert");
        FileHandle frag = Gdx.files.internal("shaders/grayscale.frag");
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

    public void applyShader(Batch batch) {
        batch.setShader(shader);
        if (floatUniforms.isEmpty()) {
            return;
        }
        for (ObjectMap.Entry<String, Float> entry : floatUniforms.entries()) {
            shader.setUniformf(entry.key, entry.value);
        }

        if (boolUniforms.isEmpty()) {
            return;
        }
        for (ObjectMap.Entry<String, Boolean> entry : boolUniforms.entries()) {
            shader.setUniformi(entry.key, entry.value ? 1 : 0);
        }
    }

    public void unapplyShader(Batch batch) {
        batch.setShader(null);
    }

}
