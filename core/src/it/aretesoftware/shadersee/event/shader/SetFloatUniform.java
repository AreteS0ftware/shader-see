package it.aretesoftware.shadersee.event.shader;

import it.aretesoftware.shadersee.event.Event;

public class SetFloatUniform extends Event {

    public final String uniformName;
    public final float uniformValue;

    public SetFloatUniform(String uniformName, float uniformValue) {
        this.uniformName = uniformName;
        this.uniformValue = uniformValue;
    }

}
