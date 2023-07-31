package it.aretesoftware.shadersee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;

import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetBVec4UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetBoolUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetDoubleUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetFloatUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetIVec4UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetIntUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetMat4UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetSampler2DUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetVec2UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetVec4UniformEvent;
import it.aretesoftware.shadersee.event.shader.ShaderProgramUpdateEvent;

public class ShaderUniforms {

    private final ObjectMap<String, Object> uniforms;

    ShaderUniforms(Main main) {
        uniforms = new ObjectMap<>();
        addListeners(main);
    }

    private void addListeners(Main main) {
        // Shader Load
        main.addPreListener(new EventListener<ShaderProgramUpdateEvent>(ShaderProgramUpdateEvent.class, this) {
            @Override
            protected void fire(ShaderProgramUpdateEvent event) {
                uniforms.clear();
            }
        });
        // Set Uniforms
        main.addPreListener(new EventListener<SetBoolUniformEvent>(SetBoolUniformEvent.class, this) {
            @Override
            protected void fire(SetBoolUniformEvent event) {
                uniforms.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetIntUniformEvent>(SetIntUniformEvent.class, this) {
            @Override
            protected void fire(SetIntUniformEvent event) {
                uniforms.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetFloatUniformEvent>(SetFloatUniformEvent.class, this) {
            @Override
            protected void fire(SetFloatUniformEvent event) {
                uniforms.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetDoubleUniformEvent>(SetDoubleUniformEvent.class, this) {
            @Override
            protected void fire(SetDoubleUniformEvent event) {
                uniforms.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetVec2UniformEvent>(SetVec2UniformEvent.class, this) {
            @Override
            protected void fire(SetVec2UniformEvent event) {
                Vector2 vec2 = (Vector2) uniforms.get(event.uniformName);
                if (vec2 == null) {
                    vec2 = new Vector2();
                    uniforms.put(event.uniformName, vec2);
                }
                vec2.set(event.uniformVec2X, event.uniformVec2Y);
            }
        });
        main.addPreListener(new EventListener<SetBVec4UniformEvent>(SetBVec4UniformEvent.class, this) {
            @Override
            protected void fire(SetBVec4UniformEvent event) {
                uniforms.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetSampler2DUniformEvent>(SetSampler2DUniformEvent.class, this) {
            @Override
            protected void fire(SetSampler2DUniformEvent event) {
                String uniformName = event.uniformName;
                Texture uniformValue = event.uniformValue;
                uniforms.put(uniformName, uniformValue);
                //TODO: disposed textures stay in the map until replaced
            }
        });
        main.addPreListener(new EventListener<SetMat4UniformEvent>(SetMat4UniformEvent.class, this) {
            @Override
            protected void fire(SetMat4UniformEvent event) {
                uniforms.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetVec4UniformEvent>(SetVec4UniformEvent.class, this) {
            @Override
            protected void fire(SetVec4UniformEvent event) {
                uniforms.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetIVec4UniformEvent>(SetIVec4UniformEvent.class, this) {
            @Override
            protected void fire(SetIVec4UniformEvent event) {
                uniforms.put(event.uniformName, event.uniformValue);
            }
        });

    }

    //

    void setUniforms(ShaderProgram shader) {
        if (uniforms.isEmpty()) {
            return;
        }
        for (ObjectMap.Entry<String, Object> entry : uniforms.entries()) {
            String uniformName = entry.key;
            Object uniformValue = entry.value;
            Class<?> valueType = uniformValue.getClass();
            if (valueType == Boolean.class) {
                shader.setUniformi(uniformName, (Boolean)uniformValue ? 1 : 0);
            }
            else if (valueType == Integer.class) {
                shader.setUniformi(uniformName, (Integer) uniformValue);
            }
            else if (valueType == Double.class) {
                shader.setUniformf(uniformName, Float.parseFloat(uniformValue.toString()));
            }
            else if (valueType == Vector2.class) {
                shader.setUniformf(uniformName, (Vector2) uniformValue);
            }
            else if (valueType == Boolean[].class) {
                Boolean[] values = (Boolean[]) uniformValue;
                shader.setUniformi(uniformName, values[0] ? 1 : 0, values[1] ? 1 : 0, values[2] ? 1 : 0, values[3] ? 1 : 0);
            }
            else if (valueType == Integer[].class) {
                Integer[] values = (Integer[]) uniformValue;
                shader.setUniformi(uniformName, values[0], values[1], values[2], values[3]);
            }
            else if (valueType == Texture.class) {
                Texture texture = (Texture) uniformValue;
                texture.bind(texture.getTextureObjectHandle());
                shader.setUniformi(uniformName, texture.getTextureObjectHandle());
                Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
            }
            else if (valueType == Matrix4.class) {
                shader.setUniformMatrix(uniformName, (Matrix4) uniformValue);
            }
            else if (valueType == Color.class) {
                shader.setUniformf(uniformName, (Color) uniformValue);
            }
        }
    }

}
