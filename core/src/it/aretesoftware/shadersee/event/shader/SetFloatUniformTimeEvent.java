package it.aretesoftware.shadersee.event.shader;

import it.aretesoftware.shadersee.event.Event;

public class SetFloatUniformTimeEvent extends Event {

    public final String uniformName;
    public final boolean timeEnabled;

    public SetFloatUniformTimeEvent(String uniformName, boolean timeEnabled) {
        this.uniformName = uniformName;
        this.timeEnabled = timeEnabled;
    }

}
