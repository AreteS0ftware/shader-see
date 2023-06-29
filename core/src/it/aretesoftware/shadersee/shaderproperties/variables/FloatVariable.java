package it.aretesoftware.shadersee.shaderproperties.variables;

import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.SetFloatUniform;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;
import it.aretesoftware.shadersee.utils.DecimalsOnlyFilter;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class FloatVariable extends Variable {

    FloatVariable(Main main, ShaderVariableQualifier qualifier, int type, String name) {
        super(main, qualifier, type, name);

        VisTextField textField = new VisTextField();
        textField.setTextFieldFilter(new DecimalsOnlyFilter());
        textField.setTextFieldListener(new VisTextField.TextFieldListener() {
            @Override
            public void keyTyped(VisTextField textField, char c) {
                if (c != '\n') return;
                float value = Float.parseFloat(textField.getText());
                main.fire(new SetFloatUniform(getVariableName(), value));
            }
        });

        defaults().space(10);
        add(new VisLabel(name + ": ")).width(100);
        add(textField);
    }
}
