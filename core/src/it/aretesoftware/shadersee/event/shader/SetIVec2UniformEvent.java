package it.aretesoftware.shadersee.event.shader;

public class SetIVec2UniformEvent extends SetUniformEvent {

    public final Integer value1, value2;

    public SetIVec2UniformEvent(String uniformName, Integer value1, Integer value2) {
        super(uniformName);
        this.value1 = value1;
        this.value2 = value2;
    }

    public SetIVec2UniformEvent(String uniformName, Integer[] uniformValue) {
        super(uniformName);
        if (uniformValue != null) {
            value1 = uniformValue[0];
            value2 = uniformValue[1];
        }
        else {
            value1 = value2 = 0;
        }
    }

}
