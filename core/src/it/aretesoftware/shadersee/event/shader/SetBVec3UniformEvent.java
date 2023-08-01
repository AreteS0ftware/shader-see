package it.aretesoftware.shadersee.event.shader;

public class SetBVec3UniformEvent extends SetUniformEvent {

    public final Boolean value1, value2, value3;

    public SetBVec3UniformEvent(String uniformName, boolean value1, boolean value2, boolean value3) {
        super(uniformName);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public SetBVec3UniformEvent(String uniformName, Boolean[] uniformValue) {
        super(uniformName);
        if (uniformValue != null) {
            value1 = uniformValue[0];
            value2 = uniformValue[1];
            value3 = uniformValue[2];
        }
        else {
            value1 = value2 = value3 = false;
        }
    }

}
