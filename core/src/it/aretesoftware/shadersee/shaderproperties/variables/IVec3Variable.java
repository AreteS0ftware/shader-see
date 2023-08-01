package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.event.shader.SetIVec3UniformEvent;
import it.aretesoftware.shadersee.utils.SignedDigitsOnlyFilter;

public class IVec3Variable extends Variable<Integer[]> {

    private VisTextField xTextField, yTextField, zTextField;

    protected IVec3Variable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        xTextField = createIVec3TextField();
        yTextField = createIVec3TextField();
        zTextField = createIVec3TextField();
        getMain().addListener(new VariableEventListener<SetIVec3UniformEvent>(SetIVec3UniformEvent.class, this) {
            @Override
            protected void fire(SetIVec3UniformEvent event) {
                xTextField.setText(String.valueOf(event.value1));
                yTextField.setText(String.valueOf(event.value2));
                zTextField.setText(String.valueOf(event.value3));
            }
        });

        defaults().space(10);
        add(new VisLabel(getVariableName()));
        defaults().expandX().width(30).maxWidth(1000).fill();
        add(xTextField);
        add(yTextField);
        add(zTextField);
    }

    @Override
    protected void setUniform(Integer[] value) {
        getMain().fire(new SetIVec3UniformEvent(getVariableName(), value));
    }

    private VisTextField createIVec3TextField() {
        VisTextField textField = new VisTextField("0");
        textField.setTextFieldFilter(new SignedDigitsOnlyFilter());
        textField.setTextFieldListener(new IVec3TextFieldListener());
        textField.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                super.keyboardFocusChanged(event, actor, focused);
                String text = textField.getText();
                if (!focused && (text.isEmpty() || text.equals("+") || text.equals("-"))) {
                    updateUniform();
                }
            }
        });
        return textField;
    }

    private class IVec3TextFieldListener implements VisTextField.TextFieldListener {
        @Override
        public void keyTyped(VisTextField textField, char c) {
            if (c != '\n') return;
            updateUniform();
        }
    }

    private void updateUniform() {
        int x = getDigit(xTextField);
        int y = getDigit(yTextField);
        int z = getDigit(zTextField);
        setUniform(new Integer[] {x, y, z});
    }

    private int getDigit(VisTextField textField) {
        String text = textField.getText();
        if (text.isEmpty() || text.equals("+") || text.equals("-")) {
            return 0;
        }
        else {
            return Integer.parseInt(text);
        }
    }

}
