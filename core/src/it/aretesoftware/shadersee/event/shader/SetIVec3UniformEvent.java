package it.aretesoftware.shadersee.event.shader;

public class SetIVec3UniformEvent extends SetUniformEvent {

    public final Integer value1, value2, value3;

    public SetIVec3UniformEvent(String uniformName, Integer value1, Integer value2, Integer value3) {
        super(uniformName);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public SetIVec3UniformEvent(String uniformName, Integer[] uniformValue) {
        super(uniformName);
        if (uniformValue != null) {
            value1 = uniformValue[0];
            value2 = uniformValue[1];
            value3 = uniformValue[2];
        }
        else {
            value1 = value2 = value3 = 0;
        }
    }

}
