package it.aretesoftware.shadersee.shaderproperties.variables;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class IntVariable extends Variable {

    IntVariable(Main main, ShaderVariableQualifier qualifier, int type, String name) {
        super(main, qualifier, type, name);

        defaults().space(10);
        add(new VisLabel(name + ": ")).width(100);
        add(new VisTextField());
    }
}
