package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.math.Matrix4;

public class SetMat4UniformEvent extends SetUniformEvent {

    public final Matrix4 uniformValue;

    public SetMat4UniformEvent(String uniformName, Matrix4 uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue == null ? new Matrix4() : uniformValue;
    }

}
