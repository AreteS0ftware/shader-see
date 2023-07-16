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
import it.aretesoftware.shadersee.event.shader.SetVec2UniformEvent;
import it.aretesoftware.shadersee.preview.Preview;
import it.aretesoftware.shadersee.utils.DecimalsOnlyFilter;

public class BVec2Variable extends Variable {

    private final Vector2 vec2 = new Vector2();
    private VisTextField xTextField, yTextField;
    private ButtonGroup<VisRadioButton> radioButtonGroup;
    private VisRadioButton customRadioButton, resolutionRadioButton, pointerRadioButton;

    BVec2Variable(VariableBuilder builder) {
        super(builder);
    }


    @Override
    protected void populate() {
        xTextField = createVec2TextField();
        yTextField = createVec2TextField();

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

        add(customRadioButton);
        add(resolutionRadioButton);
        add(pointerRadioButton);
    }

    //

    @Override
    public void act(float delta) {
        super.act(delta);
        Preview preview = getMain().getPreview();
        if (pointerRadioButton.isChecked()) {
            preview.screenToLocalCoordinates(vec2.set(Gdx.input.getX(), Gdx.input.getY()));
            updateVec2TextFields();
            getMain().fire(new SetVec2UniformEvent(getVariableName(), vec2));
        }
        if (resolutionRadioButton.isChecked()) {
            vec2.set(preview.getWidth(), preview.getHeight());
            updateVec2TextFields();
            getMain().fire(new SetVec2UniformEvent(getVariableName(), vec2));
        }
    }

    //

    private void updateVec2TextFields() {
        xTextField.setText(String.valueOf(vec2.x));
        yTextField.setText(String.valueOf(vec2.y));
    }

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
            getMain().fire(new SetVec2UniformEvent(getVariableName(), vec2.set(x, y)));
        }
    }

    private class Vec2RadioButtonListener extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (customRadioButton.isChecked()) {
                xTextField.setDisabled(false);
                yTextField.setDisabled(false);
            }
            else {
                xTextField.setDisabled(true);
                yTextField.setDisabled(true);
            }
            vec2.setZero();
            updateVec2TextFields();
            getMain().fire(new SetVec2UniformEvent(getVariableName(), vec2));
        }
    }
}
