package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.math.Vector2;

public class SetVec2UniformEvent extends SetUniformEvent {

    public final Float uniformVec2X, uniformVec2Y;

    public SetVec2UniformEvent(String uniformName, Vector2 uniformValue) {
        super(uniformName);
        if (uniformValue == null) {
            uniformVec2X = 0f;
            uniformVec2Y = 0f;
        }
        else {
            uniformVec2X = uniformValue.x;
            uniformVec2Y = uniformValue.y;
        }
    }

    public SetVec2UniformEvent(String uniformName, float uniformVec2X, float uniformVec2Y) {
        super(uniformName);
        this.uniformVec2X = uniformVec2X;
        this.uniformVec2Y = uniformVec2Y;
    }

}
