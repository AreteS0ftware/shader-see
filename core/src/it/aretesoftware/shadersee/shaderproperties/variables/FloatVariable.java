package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.couscous.Strings;
import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.SetBoolUniform;
import it.aretesoftware.shadersee.event.shader.SetFloatUniform;
import it.aretesoftware.shadersee.utils.DecimalsOnlyFilter;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class FloatVariable extends Variable {

    private VisTextField uniformTextField;
    private VisCheckBox elapsedTimeCheckbox;
    private float elapsedTime;

    protected FloatVariable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void createFunctional() {
        uniformTextField = new VisTextField("0.0");
        uniformTextField.setTextFieldFilter(new DecimalsOnlyFilter());
        uniformTextField.setTextFieldListener(new VisTextField.TextFieldListener() {
            @Override
            public void keyTyped(VisTextField textField, char c) {
                if (c != '\n' || Strings.isNullOrEmpty(textField.getText())) return;
                float value = Float.parseFloat(textField.getText());
                getMain().fire(new SetFloatUniform(getVariableName(), value));
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
                }
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName()));
        add(uniformTextField).width(100).maxWidth(1000).growX();
        add(elapsedTimeCheckbox);
    }

    @Override
    protected void createNonFunctional() {
        defaults().space(10);
        add(new VisLabel("float " + getVariableName()) + ";");
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (elapsedTimeCheckbox.isChecked()) {
            elapsedTime += delta;
            uniformTextField.setText(String.valueOf(elapsedTime));
            getMain().fire(new SetFloatUniform(getVariableName(), elapsedTime));
        }
    }
}
