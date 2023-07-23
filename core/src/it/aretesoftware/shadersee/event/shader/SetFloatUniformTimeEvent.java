package it.aretesoftware.shadersee.event.shader;

public class SetFloatUniformTimeEvent extends SetUniformEvent {

    public final boolean timeEnabled;

    public SetFloatUniformTimeEvent(String uniformName, boolean timeEnabled) {
        super(uniformName);
        this.timeEnabled = timeEnabled;
    }

}
