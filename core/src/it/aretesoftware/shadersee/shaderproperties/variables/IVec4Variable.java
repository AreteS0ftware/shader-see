package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.event.shader.SetIVec4UniformEvent;
import it.aretesoftware.shadersee.utils.SignedDigitsOnlyFilter;

public class IVec4Variable extends Variable<Integer[]> {

    private VisTextField xTextField, yTextField, zTextField, wTextField;

    protected IVec4Variable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        xTextField = createIVec4TextField();
        yTextField = createIVec4TextField();
        zTextField = createIVec4TextField();
        wTextField = createIVec4TextField();
        getMain().addListener(new VariableEventListener<SetIVec4UniformEvent>(SetIVec4UniformEvent.class, this) {
            @Override
            protected void fire(SetIVec4UniformEvent event) {
                Integer[] uniformValue = event.uniformValue;
                xTextField.setText(String.valueOf(uniformValue[0]));
                yTextField.setText(String.valueOf(uniformValue[1]));
                zTextField.setText(String.valueOf(uniformValue[2]));
                wTextField.setText(String.valueOf(uniformValue[3]));
            }
        });

        defaults().space(5);
        add(new VisLabel(getVariableName()));
        defaults().expandX().width(30).maxWidth(1000).fill();
        add(xTextField);
        add(yTextField);
        add(zTextField);
        add(wTextField);
    }

    @Override
    protected void setUniform(Integer[] value) {
        getMain().fire(new SetIVec4UniformEvent(getVariableName(), value));
    }

    private VisTextField createIVec4TextField() {
        VisTextField textField = new VisTextField("0");
        textField.setTextFieldFilter(new SignedDigitsOnlyFilter());
        textField.setTextFieldListener(new IVec4TextFieldListener());
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

    private class IVec4TextFieldListener implements VisTextField.TextFieldListener {
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
        int w = getDigit(wTextField);
        setUniform(new Integer[] {x, y, z, w});
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
