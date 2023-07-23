package it.aretesoftware.shadersee.event.shader;

public class SetDoubleUniformEvent extends SetUniformEvent {

    public final double uniformValue;

    public SetDoubleUniformEvent(String uniformName, double uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue;
    }

}
