package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.SetBoolUniform;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class BoolVariable extends Variable {

    BoolVariable(Main main, ShaderVariableQualifier qualifier, int type, String uniformName) {
        super(main, qualifier, type, uniformName);

        VisCheckBox checkBox = new VisCheckBox("");
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.fire(new SetBoolUniform(uniformName, checkBox.isChecked()));
            }
        });

        defaults().space(10);
        add(new VisLabel(uniformName + ": ")).width(100);
        add(checkBox);
    }




}
