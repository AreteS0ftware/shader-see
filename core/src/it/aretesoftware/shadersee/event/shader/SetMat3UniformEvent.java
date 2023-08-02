package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.math.Matrix3;

public class SetMat3UniformEvent extends SetUniformEvent {

    public final Matrix3 uniformValue;

    public SetMat3UniformEvent(String uniformName, Matrix3 uniformValue) {
        super(uniformName);
        this.uniformValue = uniformValue == null ? new Matrix3() : uniformValue;
    }
}
