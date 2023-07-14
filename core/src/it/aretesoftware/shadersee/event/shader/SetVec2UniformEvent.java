package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.math.Vector2;

import it.aretesoftware.shadersee.event.Event;

public class SetVec2UniformEvent extends Event {

    public final String uniformName;
    public final Vector2 uniformValue;

    public SetVec2UniformEvent(String uniformName, Vector2 uniformValue) {
        this.uniformName = uniformName;
        this.uniformValue = uniformValue;
    }

}
