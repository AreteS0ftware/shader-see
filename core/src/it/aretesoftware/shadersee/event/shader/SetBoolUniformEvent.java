package it.aretesoftware.shadersee.event.shader;

import it.aretesoftware.shadersee.event.Event;

public class SetBoolUniformEvent extends Event {

    public final String uniformName;
    public final boolean uniformValue;

    public SetBoolUniformEvent(String uniformName, boolean uniformValue) {
        this.uniformName = uniformName;
        this.uniformValue = uniformValue;
    }

}
