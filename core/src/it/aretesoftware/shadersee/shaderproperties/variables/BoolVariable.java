package it.aretesoftware.shadersee.shaderproperties.variables;


import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class BoolVariable extends Variable {

    BoolVariable(ShaderVariableQualifier qualifier, int type, String uniformName) {
        super(qualifier, type, uniformName);

        defaults().space(10);
        add(new VisLabel(uniformName + ": ")).width(100);
        add(new VisCheckBox(""));
    }




}
