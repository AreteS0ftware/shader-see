package it.aretesoftware.shadersee.shaderproperties.variables;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;

import it.aretesoftware.shadersee.event.shader.SetBVec2UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetBVec3UniformEvent;

public class BVec2Variable extends Variable<Boolean[]> {

    private VisCheckBox xCheckBox, yCheckBox;

    BVec2Variable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        xCheckBox = createBVec2CheckBox();
        yCheckBox = createBVec2CheckBox();

        defaults().space(5);
        add(new VisLabel(getVariableName()));
        defaults().expandX();
        add(xCheckBox);
        add(yCheckBox);
    }

    @Override
    protected void setUniform(Boolean[] value) {
        getMain().fire(new SetBVec2UniformEvent(getVariableName(), value));
    }

    private VisCheckBox createBVec2CheckBox() {
        VisCheckBox checkBox = new VisCheckBox("");
        checkBox.addListener(new BVec2CheckBoxListener());
        return checkBox;
    }

    private class BVec2CheckBoxListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            boolean x = xCheckBox.isChecked();
            boolean y = yCheckBox.isChecked();
            setUniform(new Boolean[] {x, y});
        }
    }

}
