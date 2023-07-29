package it.aretesoftware.shadersee.shaderproperties.variables;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisRadioButton;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.couscous.Strings;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.SetVec2UniformEvent;
import it.aretesoftware.shadersee.event.shader.SetVec2UniformOptionEvent;
import it.aretesoftware.shadersee.preview.Preview;
import it.aretesoftware.shadersee.utils.DecimalsOnlyFilter;

public class Vec2Variable extends Variable<Vector2> {

    private final Vector2 vec2 = new Vector2();
    private VisTextField xTextField, yTextField;
    private VisRadioButton customRadioButton, resolutionRadioButton, pointerRadioButton;
    private ButtonGroup<VisRadioButton> radioButtonGroup;

    Vec2Variable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        xTextField = createVec2TextField();
        yTextField = createVec2TextField();

        getMain().addListener(new VariableEventListener<SetVec2UniformEvent>(SetVec2UniformEvent.class, this) {
            @Override
            protected void fire(SetVec2UniformEvent event) {
                xTextField.setText(String.valueOf(event.uniformVec2X));
                yTextField.setText(String.valueOf(event.uniformVec2Y));
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName()));
        defaults().expandX().width(50).maxWidth(1000).fill();
        add(xTextField);
        add(yTextField);
        row();
        defaults().reset();

        radioButtonGroup = new ButtonGroup<>();
        customRadioButton = createVec2RadioButton("Custom", true);
        resolutionRadioButton = createVec2RadioButton("Resolution", false);
        pointerRadioButton = createVec2RadioButton("Pointer", false);
        radioButtonGroup.add(customRadioButton);
        radioButtonGroup.add(resolutionRadioButton);
        radioButtonGroup.add(pointerRadioButton);

        getMain().addListener(new VariableEventListener<SetVec2UniformOptionEvent>(SetVec2UniformOptionEvent.class, this) {
            @Override
            protected void fire(SetVec2UniformOptionEvent event) {
                VisRadioButton radioButton = null;
                switch (event.option) {
                    case Custom:
                        radioButton = customRadioButton;
                        break;
                    case Resolution:
                        radioButton = resolutionRadioButton;
                        break;
                    case Pointer:
                        radioButton = pointerRadioButton;
                        break;
                }
                radioButton.setChecked(true);
            }
        });

        add(customRadioButton);
        add(resolutionRadioButton);
        add(pointerRadioButton);
    }

    @Override
    protected void setUniform(Vector2 value) {
        getMain().fire(new SetVec2UniformEvent(getVariableName(), value));
    }

    //

    @Override
    public void act(float delta) {
        super.act(delta);
        Preview preview = getMain().getPreview();
        if (pointerRadioButton.isChecked()) {
            preview.screenToLocalCoordinates(vec2.set(Gdx.input.getX(), Gdx.input.getY()));
            setUniform(vec2);
        }
        if (resolutionRadioButton.isChecked()) {
            vec2.set(preview.getWidth(), preview.getHeight());
            setUniform(vec2);
        }
    }

    //

    private VisTextField createVec2TextField() {
        VisTextField textField = new VisTextField("0.0");
        textField.setTextFieldFilter(new DecimalsOnlyFilter());
        textField.setTextFieldListener(new Vec2TextFieldListener());
        return textField;
    }

    private VisRadioButton createVec2RadioButton(String name, boolean checked) {
        VisRadioButton radioButton = new VisRadioButton(name);
        radioButton.setName(name);
        radioButton.setChecked(checked);
        radioButton.addListener(new Vec2RadioButtonListener());
        return radioButton;
    }

    private class Vec2TextFieldListener implements VisTextField.TextFieldListener {
        @Override
        public void keyTyped(VisTextField textField, char c) {
            if (c != '\n' || Strings.isNullOrEmpty(textField.getText())) return;
            float x = Float.parseFloat(xTextField.getText());
            float y = Float.parseFloat(yTextField.getText());
            setUniform(vec2.set(x, y));
        }
    }

    private class Vec2RadioButtonListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            VisRadioButton checked = radioButtonGroup.getChecked();
            if (checked == null) {
                return;
            }

            Vec2Options option = checked == customRadioButton ? Vec2Options.Custom
                    : checked == resolutionRadioButton ? Vec2Options.Resolution
                    : checked == pointerRadioButton ? Vec2Options.Pointer : null;
            if (option == Vec2Options.Custom) {
                xTextField.setDisabled(false);
                yTextField.setDisabled(false);
            }
            else {
                xTextField.setDisabled(true);
                yTextField.setDisabled(true);
            }
            setUniform(vec2.setZero());
            getMain().fire(new SetVec2UniformOptionEvent(getVariableName(), option));
        }
    }

    public enum Vec2Options {
        Custom,
        Resolution,
        Pointer
    }
}
