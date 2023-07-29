package it.aretesoftware.shadersee.shaderproperties.variables;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.couscous.Strings;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetIntUniformEvent;
import it.aretesoftware.shadersee.utils.UnsignedDigitsOnlyFilter;

public class IntVariable extends Variable<Integer> {

    IntVariable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        VisTextField uniformTextField = new VisTextField("0");
        uniformTextField.setTextFieldFilter(new UnsignedDigitsOnlyFilter());
        uniformTextField.setTextFieldListener(new VisTextField.TextFieldListener() {
            @Override
            public void keyTyped(VisTextField textField, char c) {
                if (c != '\n' || Strings.isNullOrEmpty(textField.getText())) return;
                int value = Integer.parseInt(textField.getText());
                setUniform(value);
            }
        });

        getMain().addListener(new VariableEventListener<SetIntUniformEvent>(SetIntUniformEvent.class, this) {
            @Override
            protected void fire(SetIntUniformEvent event) {
                uniformTextField.setText(String.valueOf(event.uniformValue));
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName()));
        add(uniformTextField).width(100).maxWidth(1000).growX();
    }

    @Override
    protected void setUniform(Integer value) {
        getMain().fire(new SetIntUniformEvent(getVariableName(), value));
    }

}
