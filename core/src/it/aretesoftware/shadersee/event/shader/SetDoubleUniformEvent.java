package it.aretesoftware.shadersee.event.shader;

public class SetDoubleUniformEvent extends SetUniformEvent {

    public final Double uniformValue;

    public SetDoubleUniformEvent(String uniformName, Double uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue == null ? 0 : uniformValue;
    }

}
