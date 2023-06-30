package it.aretesoftware.shadersee;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ObjectMap;

import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetBoolUniform;
import it.aretesoftware.shadersee.event.shader.SetFloatUniform;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;

public class ShaderUniforms {

    private final ObjectMap<String, Boolean> boolUniformsMap;
    private final ObjectMap<String, Float> floatUniformsMap;

    ShaderUniforms(Main main) {
        boolUniformsMap = new ObjectMap<>();
        floatUniformsMap = new ObjectMap<>();
        addListeners(main);
    }

    private void addListeners(Main main) {
        // Shader Load
        main.addPreListener(new EventListener<ShaderLoadEvent>(ShaderLoadEvent.class, this) {
            @Override
            protected void fire(ShaderLoadEvent event) {
                clearUniforms();
            }
        });
        // Set Uniforms
        main.addPreListener(new EventListener<SetFloatUniform>(SetFloatUniform.class, this) {
            @Override
            protected void fire(SetFloatUniform event) {
                put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetBoolUniform>(SetBoolUniform.class, this) {
            @Override
            protected void fire(SetBoolUniform event) {
                put(event.uniformName, event.uniformValue);
            }
        });
    }

    //

    private void put(String uniformName, boolean uniformValue) {
        boolUniformsMap.put(uniformName, uniformValue);
    }

    private void put(String uniformName, float uniformValue) {
        floatUniformsMap.put(uniformName, uniformValue);
    }

    private void clearUniforms() {
        boolUniformsMap.clear();
        floatUniformsMap.clear();
    }

    void setUniforms(ShaderProgram shader) {
        if (!floatUniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, Float> entry : floatUniformsMap.entries()) {
                shader.setUniformf(entry.key, entry.value);
            }
        }
        if (!boolUniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, Boolean> entry : boolUniformsMap.entries()) {
                shader.setUniformi(entry.key, entry.value ? 1 : 0);
            }
        }
    }

}
