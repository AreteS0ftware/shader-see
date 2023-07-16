package it.aretesoftware.shadersee.event.shader;

import it.aretesoftware.shadersee.event.Event;

public class SetIntUniformEvent extends Event {

    public final String uniformName;
    public final int uniformValue;

    public SetIntUniformEvent(String uniformName, int uniformValue) {
        this.uniformName = uniformName;
        this.uniformValue = uniformValue;
    }

}
