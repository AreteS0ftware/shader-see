package it.aretesoftware.shadersee.utils;

import com.kotcrab.vis.ui.widget.VisTextField;

public class SignedDigitsOnlyFilter implements VisTextField.TextFieldFilter {

    @Override
    public boolean acceptChar(VisTextField textField, char c) {
        String text = textField.getText();

        final char PLUS = '+';
        if ((c == PLUS) && !text.isEmpty()) {
            return false;
        }

        return Character.isDigit(c) || c == PLUS || c == '\n'; // 10 = Enter key
    }

}
