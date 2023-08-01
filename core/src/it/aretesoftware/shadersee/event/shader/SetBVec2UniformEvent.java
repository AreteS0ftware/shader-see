package it.aretesoftware.shadersee.event.shader;

public class SetBVec2UniformEvent extends SetUniformEvent {

    public final Boolean value1, value2;

    public SetBVec2UniformEvent(String uniformName, boolean value1, boolean value2) {
        super(uniformName);
        this.value1 = value1;
        this.value2 = value2;
    }

    public SetBVec2UniformEvent(String uniformName, Boolean[] uniformValue) {
        super(uniformName);
        if (uniformValue != null) {
            value1 = uniformValue[0];
            value2 = uniformValue[1];
        }
        else {
            value1 = value2 = false;
        }
    }

}
