package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.graphics.Color;

public class SetVec4UniformEvent extends SetUniformEvent {

    public final Color uniformValue;

    public SetVec4UniformEvent(String uniformName, Color uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue;
    }
}
