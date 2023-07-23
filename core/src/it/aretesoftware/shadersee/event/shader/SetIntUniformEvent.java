package it.aretesoftware.shadersee.event.shader;

public class SetIntUniformEvent extends SetUniformEvent {

    public final int uniformValue;

    public SetIntUniformEvent(String uniformName, int uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue;
    }

}
