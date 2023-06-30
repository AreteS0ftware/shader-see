package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.SetBoolUniform;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public class BoolVariable extends Variable {

    protected BoolVariable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void createFunctional() {
        VisCheckBox checkBox = new VisCheckBox("");
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMain().fire(new SetBoolUniform(getVariableName(), checkBox.isChecked()));
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName())).growX();
        add(checkBox);
    }

    @Override
    protected void createNonFunctional() {
        defaults().space(10);
        add(new VisLabel("bool " + getVariableName()) + ";");
    }

}
