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

    protected BoolVariable(ShaderVariableQualifier qualifier, int type, String name) {
        super(qualifier, type, name);
    }

    @Override
    protected void createFunctional(Main main) {
        VisCheckBox checkBox = new VisCheckBox("");
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.fire(new SetBoolUniform(getVariableName(), checkBox.isChecked()));
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName() + ": ")).width(100);
        add(checkBox);
    }

    @Override
    protected void createNonFunctional(Main main) {
        defaults().space(10);
        add(new VisLabel("bool " + getVariableName()));
    }

}
