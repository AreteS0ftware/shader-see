package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.math.Vector3;

public class SetVec3UniformEvent extends SetUniformEvent {

    public final float uniformVec3X, uniformVec3Y, uniformVec3Z;

    public SetVec3UniformEvent(String uniformName, Vector3 uniformValue) {
        super(uniformName);
        if (uniformValue == null) {
            uniformVec3X = 0f;
            uniformVec3Y = 0f;
            uniformVec3Z = 0f;
        }
        else {
            uniformVec3X = uniformValue.x;
            uniformVec3Y = uniformValue.y;
            uniformVec3Z = uniformValue.z;
        }
    }

    public SetVec3UniformEvent(String uniformName, float uniformVec3X, float uniformVec3Y, float uniformVec3Z) {
        super(uniformName);
        this.uniformVec3X = uniformVec3X;
        this.uniformVec3Y = uniformVec3Y;
        this.uniformVec3Z = uniformVec3Z;
    }
}
