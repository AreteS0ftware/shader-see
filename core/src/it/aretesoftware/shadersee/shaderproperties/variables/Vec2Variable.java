package it.aretesoftware.shadersee.shaderproperties.variables;


import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class Vec2Variable extends Variable {

    Vec2Variable(ShaderVariableQualifier qualifier, int type, String uniformName) {
        super(qualifier, type, uniformName);

        defaults().space(10);
        add(new VisLabel(uniformName + ": ")).width(100);
        defaults().expandX().width(50).maxWidth(1000).fill();
        add(new VisTextField());
        add(new VisTextField());
    }




}
