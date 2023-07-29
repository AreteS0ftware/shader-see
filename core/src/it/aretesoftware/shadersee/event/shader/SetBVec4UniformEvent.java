package it.aretesoftware.shadersee.event.shader;

public class SetBVec4UniformEvent extends SetUniformEvent {

    public final Boolean[] uniformValue;

    public SetBVec4UniformEvent(String uniformName, boolean value1, boolean value2, boolean value3, boolean value4) {
        this(uniformName, new Boolean[] {value1, value2, value3, value4});
    }

    public SetBVec4UniformEvent(String uniformName, Boolean[] uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue == null ? new Boolean[] {false, false, false, false} : uniformValue;
    }

}
