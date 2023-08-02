package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.event.shader.SetMat3UniformEvent;
import it.aretesoftware.shadersee.utils.DecimalsOnlyFilter;

public class Mat3Variable extends Variable<Matrix3> {

    private VisTextField m00TextField, m01TextField, m02TextField;
    private VisTextField m10TextField, m11TextField, m12TextField;
    private VisTextField m20TextField, m21TextField, m22TextField;

    Mat3Variable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        Matrix3 mat3 = new Matrix3();
        float[] val = mat3.getValues();
        m00TextField = createMat3TextField(val[Matrix3.M00]);
        m01TextField = createMat3TextField(val[Matrix3.M01]);
        m02TextField = createMat3TextField(val[Matrix3.M02]);
        m10TextField = createMat3TextField(val[Matrix3.M10]);
        m11TextField = createMat3TextField(val[Matrix3.M11]);
        m12TextField = createMat3TextField(val[Matrix3.M12]);
        m20TextField = createMat3TextField(val[Matrix3.M20]);
        m21TextField = createMat3TextField(val[Matrix3.M21]);
        m22TextField = createMat3TextField(val[Matrix3.M22]);

        Table table = new Table();
        table.defaults().expandX().space(3).width(50).maxWidth(1000).fill();
        table.add(m00TextField);
        table.add(m01TextField);
        table.add(m02TextField);
        table.row();
        table.add(m10TextField);
        table.add(m11TextField);
        table.add(m12TextField);
        table.row();
        table.add(m20TextField);
        table.add(m21TextField);
        table.add(m22TextField);

        defaults().space(7);
        add(new VisLabel(getVariableName()));
        add(table).grow();
    }

    @Override
    protected void setUniform(Matrix3 value) {
        getMain().fire(new SetMat3UniformEvent(getVariableName(), value));
    }

    private VisTextField createMat3TextField(float value) {
        VisTextField textField = new VisTextField(String.valueOf(value));
        textField.setTextFieldFilter(new DecimalsOnlyFilter());
        textField.setTextFieldListener(new Mat3TextFieldListener());
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

    private class Mat3TextFieldListener implements VisTextField.TextFieldListener {
        @Override
        public void keyTyped(VisTextField textField, char c) {
            if (c != '\n') return;
            updateUniform();
        }
    }

    private void updateUniform() {
        Matrix3 mat3 = new Matrix3();
        float[] val = mat3.getValues();
        val[Matrix3.M00] = getDigit(m00TextField);
        val[Matrix3.M01] = getDigit(m01TextField);
        val[Matrix3.M02] = getDigit(m02TextField);
        val[Matrix3.M10] = getDigit(m10TextField);
        val[Matrix3.M11] = getDigit(m11TextField);
        val[Matrix3.M12] = getDigit(m12TextField);
        val[Matrix3.M20] = getDigit(m20TextField);
        val[Matrix3.M21] = getDigit(m21TextField);
        val[Matrix3.M22] = getDigit(m22TextField);
        setUniform(mat3);
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
