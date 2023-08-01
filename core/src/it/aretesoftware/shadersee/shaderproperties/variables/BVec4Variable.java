package it.aretesoftware.shadersee.shaderproperties.variables;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;

import it.aretesoftware.shadersee.event.shader.SetBVec4UniformEvent;

public class BVec4Variable extends Variable<Boolean[]> {

    private VisCheckBox xCheckBox, yCheckBox, zCheckBox, wCheckBox;

    BVec4Variable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        xCheckBox = createBVec4CheckBox();
        yCheckBox = createBVec4CheckBox();
        zCheckBox = createBVec4CheckBox();
        wCheckBox = createBVec4CheckBox();

        defaults().space(5);
        add(new VisLabel(getVariableName()));
        defaults().expandX();
        add(xCheckBox);
        add(yCheckBox);
        add(zCheckBox);
        add(wCheckBox);
    }

    @Override
    protected void setUniform(Boolean[] value) {
        getMain().fire(new SetBVec4UniformEvent(getVariableName(), value));
    }

    private VisCheckBox createBVec4CheckBox() {
        VisCheckBox checkBox = new VisCheckBox("");
        checkBox.addListener(new BVec4CheckBoxListener());
        return checkBox;
    }

    private class BVec4CheckBoxListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            boolean x = xCheckBox.isChecked();
            boolean y = yCheckBox.isChecked();
            boolean z = zCheckBox.isChecked();
            boolean w = wCheckBox.isChecked();
            setUniform(new Boolean[] {x, y, z, w});
        }
    }

}
