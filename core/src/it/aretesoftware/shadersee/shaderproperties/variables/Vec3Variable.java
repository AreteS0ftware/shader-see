package it.aretesoftware.shadersee.shaderproperties.variables;


import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class Vec3Variable extends Variable {

    Vec3Variable(Main main, ShaderVariableQualifier qualifier, int type, String uniformName) {
        super(main, qualifier, type, uniformName);

        defaults().space(5);
        add(new VisLabel(uniformName + ": ")).width(100);
        defaults().expandX().width(50).maxWidth(1000).fill();
        add(new VisTextField());
        add(new VisTextField());
        add(new VisTextField());
    }




}
