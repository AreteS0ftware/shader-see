package it.aretesoftware.shadersee.event.shader;

public class SetFloatUniformEvent extends SetUniformEvent {

    public final float uniformValue;

    public SetFloatUniformEvent(String uniformName, float uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue;
    }

}
