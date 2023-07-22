package it.aretesoftware.shadersee.shaderproperties.variables;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.couscous.Strings;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetIntUniformEvent;
import it.aretesoftware.shadersee.utils.UnsignedDigitsOnlyFilter;

public class IntVariable extends Variable {

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
                getMain().fire(new SetIntUniformEvent(getVariableName(), value));
            }
        });

        getMain().addListener(new EventListener<SetIntUniformEvent>(SetIntUniformEvent.class, this) {
            @Override
            protected void fire(SetIntUniformEvent event) {
                uniformTextField.setText(String.valueOf(event.uniformValue));
            }
            @Override
            protected boolean shouldFire(SetIntUniformEvent event) {
                return event.uniformName.equals(getVariableName());
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName()));
        add(uniformTextField).width(100).maxWidth(1000).growX();
    }

}
