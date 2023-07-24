package it.aretesoftware.shadersee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;

import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetBVec4UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetBoolUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetDoubleUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetFloatUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetIntUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetSampler2DUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetVec2UniformEvent;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;

public class ShaderUniforms {

    private final ObjectMap<String, Boolean> boolUniformsMap;
    private final ObjectMap<String, Integer> intUniformsMap;
    private final ObjectMap<String, Float> floatUniformsMap;
    private final ObjectMap<String, Double> doubleUniformsMap;
    private final ObjectMap<String, Vector2> vec2UniformsMap;
    private final ObjectMap<String, boolean[]> bvec4UniformsMap;
    private final ObjectMap<String, Texture> sampler2DUniformsMap;

    ShaderUniforms(Main main) {
        boolUniformsMap = new ObjectMap<>();
        intUniformsMap = new ObjectMap<>();
        floatUniformsMap = new ObjectMap<>();
        doubleUniformsMap = new ObjectMap<>();
        vec2UniformsMap = new ObjectMap<>();
        bvec4UniformsMap = new ObjectMap<>();
        sampler2DUniformsMap = new ObjectMap<>();
        addListeners(main);
    }

    private void addListeners(Main main) {
        // Shader Load
        main.addPreListener(new EventListener<ShaderLoadEvent>(ShaderLoadEvent.class, this) {
            @Override
            protected void fire(ShaderLoadEvent event) {
                floatUniformsMap.clear();
                boolUniformsMap.clear();
                vec2UniformsMap.clear();
            }
        });
        // Set Uniforms
        main.addPreListener(new EventListener<SetBoolUniformEvent>(SetBoolUniformEvent.class, this) {
            @Override
            protected void fire(SetBoolUniformEvent event) {
                boolUniformsMap.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetIntUniformEvent>(SetIntUniformEvent.class, this) {
            @Override
            protected void fire(SetIntUniformEvent event) {
                intUniformsMap.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetFloatUniformEvent>(SetFloatUniformEvent.class, this) {
            @Override
            protected void fire(SetFloatUniformEvent event) {
                floatUniformsMap.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetDoubleUniformEvent>(SetDoubleUniformEvent.class, this) {
            @Override
            protected void fire(SetDoubleUniformEvent event) {
                doubleUniformsMap.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetVec2UniformEvent>(SetVec2UniformEvent.class, this) {
            @Override
            protected void fire(SetVec2UniformEvent event) {
                Vector2 vec2 = vec2UniformsMap.get(event.uniformName);
                if (vec2 == null) {
                    vec2 = new Vector2();
                    vec2UniformsMap.put(event.uniformName, vec2);
                }
                vec2.set(event.uniformVec2X, event.uniformVec2Y);
            }
        });
        main.addPreListener(new EventListener<SetBVec4UniformEvent>(SetBVec4UniformEvent.class, this) {
            @Override
            protected void fire(SetBVec4UniformEvent event) {
                bvec4UniformsMap.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetSampler2DUniformEvent>(SetSampler2DUniformEvent.class, this) {
            @Override
            protected void fire(SetSampler2DUniformEvent event) {
                String uniformName = event.uniformName;
                Texture uniformValue = event.uniformValue;
                sampler2DUniformsMap.put(uniformName, uniformValue);
                //TODO: disposed textures stay in the map until replaced
            }
        });
    }

    //

    void setUniforms(ShaderProgram shader) {
        if (!boolUniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, Boolean> entry : boolUniformsMap.entries()) {
                shader.setUniformi(entry.key, entry.value ? 1 : 0);
            }
        }
        if (!intUniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, Integer> entry : intUniformsMap.entries()) {
                shader.setUniformi(entry.key, entry.value);
            }
        }
        if (!floatUniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, Float> entry : floatUniformsMap.entries()) {
                shader.setUniformf(entry.key, entry.value);
            }
        }
        if (!doubleUniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, Double> entry : doubleUniformsMap.entries()) {
                shader.setUniformf(entry.key, Float.parseFloat(entry.value.toString()));
            }
        }
        if (!vec2UniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, Vector2> entry : vec2UniformsMap.entries()) {
                shader.setUniformf(entry.key, entry.value);
            }
        }
        if (!bvec4UniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, boolean[]> entry : bvec4UniformsMap.entries()) {
                boolean[] values = entry.value;
                shader.setUniformi(entry.key, values[0] ? 1 : 0, values[1] ? 1 : 0, values[2] ? 1 : 0, values[3] ? 1 : 0);
            }
        }
        if (!sampler2DUniformsMap.isEmpty()) {
            for (ObjectMap.Entry<String, Texture> entry : sampler2DUniformsMap.entries()) {
                Texture texture = entry.value;
                texture.bind(texture.getTextureObjectHandle());
                shader.setUniformi(entry.key, texture.getTextureObjectHandle());
                Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
            }
        }
    }

}
