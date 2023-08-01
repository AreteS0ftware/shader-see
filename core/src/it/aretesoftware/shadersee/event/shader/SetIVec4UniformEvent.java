package it.aretesoftware.shadersee.event.shader;

public class SetIVec4UniformEvent extends SetUniformEvent {

    public final Integer value1, value2, value3, value4;

    public SetIVec4UniformEvent(String uniformName, Integer value1, Integer value2, Integer value3, Integer value4) {
        super(uniformName);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
    }

    public SetIVec4UniformEvent(String uniformName, Integer[] uniformValue) {
        super(uniformName);
        if (uniformValue != null) {
            value1 = uniformValue[0];
            value2 = uniformValue[1];
            value3 = uniformValue[2];
            value4 = uniformValue[3];
        }
        else {
            value1 = value2 = value3 = value4 = 0;
        }
    }

}
