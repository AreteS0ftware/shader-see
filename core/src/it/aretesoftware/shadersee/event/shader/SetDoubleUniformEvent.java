package it.aretesoftware.shadersee.event.shader;

import it.aretesoftware.shadersee.event.Event;

public class SetDoubleUniformEvent extends Event {

    public final String uniformName;
    public final double uniformValue;

    public SetDoubleUniformEvent(String uniformName, double uniformValue) {
        this.uniformName = uniformName;
        this.uniformValue = uniformValue;
    }

}
