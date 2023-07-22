package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;

import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetBoolUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetVec2UniformEvent;

public class BoolVariable extends Variable {

    protected BoolVariable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        VisCheckBox checkBox = new VisCheckBox("");
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMain().fire(new SetBoolUniformEvent(getVariableName(), checkBox.isChecked()));
            }
        });

        getMain().addListener(new EventListener<SetBoolUniformEvent>(SetBoolUniformEvent.class, this) {
            @Override
            protected void fire(SetBoolUniformEvent event) {
                checkBox.setChecked(event.uniformValue);
            }
            @Override
            protected boolean shouldFire(SetBoolUniformEvent event) {
                return event.uniformName.equals(getVariableName());
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName()));
        add(checkBox);
    }

}
