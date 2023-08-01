package it.aretesoftware.shadersee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ObjectMap;

import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetBVec2UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetBVec3UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetBVec4UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetBoolUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetDoubleUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetFloatUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetIVec2UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetIVec3UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetIVec4UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetIntUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetMat4UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetSampler2DUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetVec2UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetVec3UniformEvent;
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
        main.addPreListener(new EventListener<SetVec3UniformEvent>(SetVec3UniformEvent.class, this) {
            @Override
            protected void fire(SetVec3UniformEvent event) {
                Vector3 vec3 = (Vector3) uniforms.get(event.uniformName);
                if (vec3 == null) {
                    vec3 = new Vector3();
                    uniforms.put(event.uniformName, vec3);
                }
                vec3.set(event.uniformVec3X, event.uniformVec3Y, event.uniformVec3Z);
            }
        });
        main.addPreListener(new EventListener<SetVec4UniformEvent>(SetVec4UniformEvent.class, this) {
            @Override
            protected void fire(SetVec4UniformEvent event) {
                uniforms.put(event.uniformName, event.uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetBVec2UniformEvent>(SetBVec2UniformEvent.class, this) {
            @Override
            protected void fire(SetBVec2UniformEvent event) {
                Boolean[] uniformValue = new Boolean[] {event.value1, event.value2};
                uniforms.put(event.uniformName, uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetBVec3UniformEvent>(SetBVec3UniformEvent.class, this) {
            @Override
            protected void fire(SetBVec3UniformEvent event) {
                Boolean[] uniformValue = new Boolean[] {event.value1, event.value2, event.value3};
                uniforms.put(event.uniformName, uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetBVec4UniformEvent>(SetBVec4UniformEvent.class, this) {
            @Override
            protected void fire(SetBVec4UniformEvent event) {
                Boolean[] uniformValue = new Boolean[] {event.value1, event.value2, event.value3, event.value4};
                uniforms.put(event.uniformName, uniformValue);
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
        main.addPreListener(new EventListener<SetIVec2UniformEvent>(SetIVec2UniformEvent.class, this) {
            @Override
            protected void fire(SetIVec2UniformEvent event) {
                Integer[] uniformValue = new Integer[] {event.value1, event.value2};
                uniforms.put(event.uniformName, uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetIVec3UniformEvent>(SetIVec3UniformEvent.class, this) {
            @Override
            protected void fire(SetIVec3UniformEvent event) {
                Integer[] uniformValue = new Integer[] {event.value1, event.value2, event.value3};
                uniforms.put(event.uniformName, uniformValue);
            }
        });
        main.addPreListener(new EventListener<SetIVec4UniformEvent>(SetIVec4UniformEvent.class, this) {
            @Override
            protected void fire(SetIVec4UniformEvent event) {
                Integer[] uniformValue = new Integer[] {event.value1, event.value2, event.value3, event.value4};
                uniforms.put(event.uniformName, uniformValue);
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
            if (valueType == Boolean.class) {   //bool
                shader.setUniformi(uniformName, (Boolean)uniformValue ? 1 : 0);
            }
            else if (valueType == Integer.class) {  //int
                shader.setUniformi(uniformName, (Integer) uniformValue);
            }
            else if (valueType == Double.class) {   //double
                shader.setUniformf(uniformName, Float.parseFloat(uniformValue.toString()));
            }
            else if (valueType == Vector2.class) {  //vec2
                shader.setUniformf(uniformName, (Vector2) uniformValue);
            }
            else if (valueType == Vector3.class) {  //vec3
                shader.setUniformf(uniformName, (Vector3) uniformValue);
            }
            else if (valueType == Color.class) {    //vec4
                shader.setUniformf(uniformName, (Color) uniformValue);
            }
            else if (valueType == Boolean[].class) {    //bvecn
                Boolean[] values = (Boolean[]) uniformValue;
                switch (values.length) {
                    case 2: //bvec2
                        shader.setUniformi(uniformName, values[0] ? 1 : 0, values[1] ? 1 : 0);
                        break;
                    case 3: //bvec3
                        shader.setUniformi(uniformName, values[0] ? 1 : 0, values[1] ? 1 : 0, values[2] ? 1 : 0);
                        break;
                    case 4: //bvec4
                        shader.setUniformi(uniformName, values[0] ? 1 : 0, values[1] ? 1 : 0, values[2] ? 1 : 0, values[3] ? 1 : 0);
                        break;
                }
            }
            else if (valueType == Integer[].class) {    //ivecn
                Integer[] values = (Integer[]) uniformValue;
                switch (values.length) {
                    case 2: //ivec2
                        shader.setUniformi(uniformName, values[0], values[1]);
                        break;
                    case 3: //ivec3
                        shader.setUniformi(uniformName, values[0], values[1], values[2]);
                        break;
                    case 4: //ivec4
                        shader.setUniformi(uniformName, values[0], values[1], values[2], values[3]);
                        break;
                }
            }
            else if (valueType == Texture.class) {  //sampler2D
                Texture texture = (Texture) uniformValue;
                texture.bind(texture.getTextureObjectHandle());
                shader.setUniformi(uniformName, texture.getTextureObjectHandle());
                Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
            }
            else if (valueType == Matrix4.class) {  //mat4
                shader.setUniformMatrix(uniformName, (Matrix4) uniformValue);
            }
        }
    }

}
