package it.aretesoftware.shadersee;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;

import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetBoolUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetFloatUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetVec2UniformEvent;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;

public class ShaderUniforms {

    private final ObjectMap<String, Boolean> boolUniformsMap;
    private final ObjectMap<String, Float> floatUniformsMap;
    private final ObjectMap<String, Vector2> vec2UniformsMap;

    ShaderUniforms(Main main) {
        boolUniformsMap = new ObjectMap<>();
        floatUniformsMap = new ObjectMap<>();
        vec2UniformsMap = new ObjectMap<>();
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
        main.addPreListener(new EventListener<SetFloatUniformEvent>(SetFloatUniformEvent.class, this) {
            @Override
            protected void fire(SetFloatUniformEvent event) {
                put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetBoolUniformEvent>(SetBoolUniformEvent.class, this) {
            @Override
            protected void fire(SetBoolUniformEvent event) {
                put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetVec2UniformEvent>(SetVec2UniformEvent.class, this) {
            @Override
            protected void fire(SetVec2UniformEvent event) {
                vec2UniformsMap.put(event.uniformName, event.uniformValue);
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
        if (!vec2UniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, Vector2> entry : vec2UniformsMap.entries()) {
                shader.setUniformf(entry.key, entry.value);
            }
        }
    }

}
