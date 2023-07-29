package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.couscous.Strings;
import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetFloatUniformEvent;
import it.aretesoftware.shadersee.event.shader.SetFloatUniformTimeEvent;
import it.aretesoftware.shadersee.event.shader.SetUniformEvent;
import it.aretesoftware.shadersee.utils.DecimalsOnlyFilter;

public class FloatVariable extends Variable<Float> {

    private VisTextField uniformTextField;
    private VisCheckBox elapsedTimeCheckbox;
    private float elapsedTime;

    protected FloatVariable(VariableBuilder builder) {
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
                float value = Float.parseFloat(textField.getText());
                setUniform(value);
            }
        });
        elapsedTimeCheckbox = new VisCheckBox("Time");
        elapsedTimeCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                elapsedTime = 0;
                uniformTextField.setDisabled(elapsedTimeCheckbox.isChecked());
                setUniform(0f);
                getMain().fire(new SetFloatUniformTimeEvent(getVariableName(), elapsedTimeCheckbox.isChecked()));
            }
        });

        getMain().addListener(new VariableEventListener<SetFloatUniformEvent>(SetFloatUniformEvent.class, this) {
            @Override
            protected void fire(SetFloatUniformEvent event) {
                uniformTextField.setText(String.valueOf(event.uniformValue));
            }
        });
        getMain().addListener(new VariableEventListener<SetFloatUniformTimeEvent>(SetFloatUniformTimeEvent.class, this) {
            @Override
            protected void fire(SetFloatUniformTimeEvent event) {
                elapsedTimeCheckbox.setChecked(event.timeEnabled);
            }
        });

        defaults().space(7);
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
            setUniform(elapsedTime);
        }
    }

    @Override
    protected void setUniform(Float value) {
        getMain().fire(new SetFloatUniformEvent(getVariableName(), value));
    }
}
