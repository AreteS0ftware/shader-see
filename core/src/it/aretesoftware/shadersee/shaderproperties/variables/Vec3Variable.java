package it.aretesoftware.shadersee.shaderproperties.variables;


import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.event.shader.SetVec3UniformEvent;
import it.aretesoftware.shadersee.utils.DecimalsOnlyFilter;

public class Vec3Variable extends Variable<Vector3> {

    private VisTextField xTextField, yTextField, zTextField;

    Vec3Variable(VariableBuilder builder) {
        super(builder);
    }


    @Override
    protected void populate() {
        xTextField = createVec3TextField();
        yTextField = createVec3TextField();
        zTextField = createVec3TextField();
        getMain().addListener(new VariableEventListener<SetVec3UniformEvent>(SetVec3UniformEvent.class, this) {
            @Override
            protected void fire(SetVec3UniformEvent event) {
                xTextField.setText(String.valueOf(event.uniformVec3X));
                yTextField.setText(String.valueOf(event.uniformVec3Y));
                zTextField.setText(String.valueOf(event.uniformVec3Z));
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName()));
        defaults().expandX().width(50).maxWidth(1000).fill();
        add(xTextField);
        add(yTextField);
        add(zTextField);
    }

    @Override
    protected void setUniform(Vector3 value) {
        getMain().fire(new SetVec3UniformEvent(getVariableName(), value));
    }

    private VisTextField createVec3TextField() {
        VisTextField textField = new VisTextField("0.0");
        textField.setTextFieldFilter(new DecimalsOnlyFilter());
        textField.setTextFieldListener(new Vec3TextFieldListener());
        textField.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                super.keyboardFocusChanged(event, actor, focused);
                String text = textField.getText();
                if (!focused && (text.isEmpty() || text.equals("+") || text.equals("-") || text.equals("."))) {
                    updateUniform();
                }
            }
        });
        return textField;
    }

    private class Vec3TextFieldListener implements VisTextField.TextFieldListener {
        @Override
        public void keyTyped(VisTextField textField, char c) {
            if (c != '\n') return;
            updateUniform();
        }
    }

    private void updateUniform() {
        float x = getDigit(xTextField);
        float y = getDigit(yTextField);
        float z = getDigit(zTextField);
        setUniform(new Vector3(x, y, z));
    }

    private float getDigit(VisTextField textField) {
        String text = textField.getText();
        if (text.isEmpty() || text.equals("+") || text.equals("-") || text.equals(".")) {
            return 0f;
        }
        else {
            return Float.parseFloat(text);
        }
    }

}
