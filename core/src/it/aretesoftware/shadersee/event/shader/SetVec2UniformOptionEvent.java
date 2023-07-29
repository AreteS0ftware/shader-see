package it.aretesoftware.shadersee.event.shader;

import it.aretesoftware.shadersee.shaderproperties.variables.Vec2Variable;

public class SetVec2UniformOptionEvent extends SetUniformEvent {

    public final Vec2Variable.Vec2Options option;

    public SetVec2UniformOptionEvent(String uniformName, Vec2Variable.Vec2Options option) {
        super(uniformName);
        this.option = option;
    }

}
