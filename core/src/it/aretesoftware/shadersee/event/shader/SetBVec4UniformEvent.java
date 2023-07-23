package it.aretesoftware.shadersee.event.shader;

public class SetBVec4UniformEvent extends SetUniformEvent {
    public final boolean[] uniformValue;

    public SetBVec4UniformEvent(String uniformName, boolean value1, boolean value2, boolean value3, boolean value4) {
        this(uniformName, new boolean[] {value1, value2, value3, value4});
    }

    public SetBVec4UniformEvent(String uniformName, boolean[] uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue;
    }

}
