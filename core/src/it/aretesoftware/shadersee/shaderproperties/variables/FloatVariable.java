package it.aretesoftware.shadersee.shaderproperties.variables;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.couscous.Strings;
import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.SetFloatUniform;
import it.aretesoftware.shadersee.utils.DecimalsOnlyFilter;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class FloatVariable extends Variable {

    protected FloatVariable(ShaderVariableQualifier qualifier, int type, String name) {
        super(qualifier, type, name);
    }

    @Override
    protected void createFunctional(Main main) {
        VisTextField textField = new VisTextField("0.0");
        textField.setTextFieldFilter(new DecimalsOnlyFilter());
        textField.setTextFieldListener(new VisTextField.TextFieldListener() {
            @Override
            public void keyTyped(VisTextField textField, char c) {
                if (c != '\n' || Strings.isNullOrEmpty(textField.getText())) return;
                float value = Float.parseFloat(textField.getText());
                main.fire(new SetFloatUniform(getVariableName(), value));
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName() + ": ")).width(100);
        add(textField);
    }

    @Override
    protected void createNonFunctional(Main main) {
        defaults().space(10);
        add(new VisLabel("float " + getVariableName()));
    }
}
