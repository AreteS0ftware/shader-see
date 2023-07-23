package it.aretesoftware.shadersee.event.shader;

import it.aretesoftware.shadersee.event.Event;

public abstract class SetUniformEvent extends Event {

    public final String uniformName;

    protected SetUniformEvent(String uniformName) {
        this.uniformName = uniformName;
    }

}
