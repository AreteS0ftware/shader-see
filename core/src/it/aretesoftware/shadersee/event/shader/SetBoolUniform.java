package it.aretesoftware.shadersee.event.shader;

import it.aretesoftware.shadersee.event.Event;

public class SetBoolUniform extends Event {

    public final String uniformName;
    public final boolean uniformValue;

    public SetBoolUniform(String uniformName, boolean uniformValue) {
        this.uniformName = uniformName;
        this.uniformValue = uniformValue;
    }

}
