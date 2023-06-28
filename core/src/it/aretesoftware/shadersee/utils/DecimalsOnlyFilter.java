package it.aretesoftware.shadersee.utils;

import com.kotcrab.vis.ui.widget.VisTextField;

public class DecimalsOnlyFilter implements VisTextField.TextFieldFilter {

    @Override
    public boolean acceptChar(VisTextField textField, char c) {
        String text = textField.getText();

        final char PLUS = '+';
        final char MINUS = '-';
        if ((c == PLUS || c == MINUS) && !text.isEmpty()) {
            return false;
        }

        final char DOT = '.';
        if (c == DOT && text.contains(String.valueOf(DOT))) {
            return false;
        }

        return Character.isDigit(c) || c == PLUS || c == MINUS || c == DOT || c == '\n'; // 10 = Enter key
    }

}
