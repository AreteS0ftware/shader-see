package it.aretesoftware.shadersee.event.shader;

public class SetBoolUniformEvent extends SetUniformEvent {

    public final Boolean uniformValue;

    public SetBoolUniformEvent(String uniformName, Boolean uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue == null ? false : uniformValue;
    }

}
