package it.aretesoftware.shadersee.event.shader;

import it.aretesoftware.shadersee.event.Event;

public class SetFloatUniformEvent extends Event {

    public final String uniformName;
    public final float uniformValue;

    public SetFloatUniformEvent(String uniformName, float uniformValue) {
        this.uniformName = uniformName;
        this.uniformValue = uniformValue;
    }

}
