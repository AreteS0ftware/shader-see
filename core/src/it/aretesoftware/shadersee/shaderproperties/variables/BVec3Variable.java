package it.aretesoftware.shadersee.shaderproperties.variables;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;

import it.aretesoftware.shadersee.event.shader.SetBVec3UniformEvent;

public class BVec3Variable extends Variable<Boolean[]> {

    private VisCheckBox xCheckBox, yCheckBox, zCheckBox;

    BVec3Variable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        xCheckBox = createBVec3CheckBox();
        yCheckBox = createBVec3CheckBox();
        zCheckBox = createBVec3CheckBox();

        defaults().space(5);
        add(new VisLabel(getVariableName()));
        defaults().expandX();
        add(xCheckBox);
        add(yCheckBox);
        add(zCheckBox);
    }

    @Override
    protected void setUniform(Boolean[] value) {
        getMain().fire(new SetBVec3UniformEvent(getVariableName(), value));
    }

    private VisCheckBox createBVec3CheckBox() {
        VisCheckBox checkBox = new VisCheckBox("");
        checkBox.addListener(new BVec3CheckBoxListener());
        return checkBox;
    }

    private class BVec3CheckBoxListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            boolean x = xCheckBox.isChecked();
            boolean y = yCheckBox.isChecked();
            boolean z = zCheckBox.isChecked();
            setUniform(new Boolean[] {x, y, z});
        }
    }

}
