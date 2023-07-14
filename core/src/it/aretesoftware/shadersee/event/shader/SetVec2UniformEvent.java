package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.math.Vector2;

import it.aretesoftware.shadersee.event.Event;

public class SetVec2UniformEvent extends Event {

    public final String uniformName;
    public final float uniformVec2X, uniformVec2Y;

    public SetVec2UniformEvent(String uniformName, Vector2 uniformValue) {
        this(uniformName, uniformValue.x, uniformValue.y);
    }

    public SetVec2UniformEvent(String uniformName, float uniformVec2X, float uniformVec2Y) {
        this.uniformName = uniformName;
        this.uniformVec2X = uniformVec2X;
        this.uniformVec2Y = uniformVec2Y;
    }

}
