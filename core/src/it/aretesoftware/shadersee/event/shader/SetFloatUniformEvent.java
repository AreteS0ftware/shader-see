package it.aretesoftware.shadersee.event.shader;

public class SetFloatUniformEvent extends SetUniformEvent {

    public final Float uniformValue;

    public SetFloatUniformEvent(String uniformName, Float uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue == null ? 0 : uniformValue;
    }

}
