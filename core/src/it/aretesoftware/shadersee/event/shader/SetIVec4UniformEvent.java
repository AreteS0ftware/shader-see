package it.aretesoftware.shadersee.event.shader;

public class SetIVec4UniformEvent extends SetUniformEvent {

    public final Integer[] uniformValue;

    public SetIVec4UniformEvent(String uniformName, Integer value1, Integer value2, Integer value3, Integer value4) {
        this(uniformName, new Integer[] {value1, value2, value3, value4});
    }

    public SetIVec4UniformEvent(String uniformName, Integer[] uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue == null ? new Integer[] {0, 0, 0, 0} : uniformValue;
    }

}
