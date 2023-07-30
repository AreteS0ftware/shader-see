package it.aretesoftware.shadersee.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.aretesoftware.shadersee.utils.ShaderVariablePrecision;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public class DialogSource extends VisDialog {

    public DialogSource(String title, String shaderSource) {
        super(title);
        setCenterOnAdd(true);
        setResizable(true);
        addCloseButton();

        VisLabel label = new VisLabel(shaderSource);
        label.getStyle().font.getData().markupEnabled = true;

        setSize(Math.min(600, label.getWidth() + 60), Math.min(600, label.getHeight() + 60));

        Table contentTable = getContentTable();
        VisScrollPane scrollPane = new VisScrollPane(label);
        contentTable.add(scrollPane);

    }
    
}
