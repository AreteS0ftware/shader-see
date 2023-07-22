package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.math.Vector2;

import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.shaderproperties.variables.Vec2Variable;

public class SetVec2UniformOptionEvent extends Event {

    public final String uniformName;
    public final Vec2Variable.Vec2Options option;

    public SetVec2UniformOptionEvent(String uniformName, Vec2Variable.Vec2Options option) {
        this.uniformName = uniformName;
        this.option = option;
    }

}
