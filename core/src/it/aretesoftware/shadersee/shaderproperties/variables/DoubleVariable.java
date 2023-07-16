package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.couscous.Strings;
import it.aretesoftware.shadersee.event.shader.SetDoubleUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetFloatUniformEvent;
import it.aretesoftware.shadersee.utils.DecimalsOnlyFilter;

public class DoubleVariable extends Variable {

    private VisTextField uniformTextField;
    private VisCheckBox elapsedTimeCheckbox;
    private float elapsedTime;

    protected DoubleVariable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        uniformTextField = new VisTextField("0.0");
        uniformTextField.setTextFieldFilter(new DecimalsOnlyFilter());
        uniformTextField.setTextFieldListener(new VisTextField.TextFieldListener() {
            @Override
            public void keyTyped(VisTextField textField, char c) {
                if (c != '\n' || Strings.isNullOrEmpty(textField.getText())) return;
                double value = Double.parseDouble(textField.getText());
                getMain().fire(new SetDoubleUniformEvent(getVariableName(), value));
            }
        });

        elapsedTimeCheckbox = new VisCheckBox("Time");
        elapsedTimeCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (elapsedTimeCheckbox.isChecked()) {
                    uniformTextField.setDisabled(true);
                }
                else {
                    elapsedTime = 0;
                    uniformTextField.setDisabled(false);
                    uniformTextField.setText("0.0");
                }
                getMain().fire(new SetFloatUniformEvent(getVariableName(), 0));
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName()));
        add(uniformTextField).width(100).maxWidth(1000).growX();
        add(elapsedTimeCheckbox);
    }

    //

    @Override
    public void act(float delta) {
        super.act(delta);

        if (elapsedTimeCheckbox.isChecked()) {
            elapsedTime += delta;
            uniformTextField.setText(String.valueOf(elapsedTime));
            getMain().fire(new SetFloatUniformEvent(getVariableName(), elapsedTime));
        }
    }
}
