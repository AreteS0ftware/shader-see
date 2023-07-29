package it.aretesoftware.shadersee.event.shader;

public class SetIntUniformEvent extends SetUniformEvent {

    public final Integer uniformValue;

    public SetIntUniformEvent(String uniformName, Integer uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue == null ? 0 : uniformValue;
    }

}
