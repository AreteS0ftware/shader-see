package it.aretesoftware.shadersee.event.shader;

public class SetBoolUniformEvent extends SetUniformEvent {

    public final boolean uniformValue;

    public SetBoolUniformEvent(String uniformName, boolean uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue;
    }

}
