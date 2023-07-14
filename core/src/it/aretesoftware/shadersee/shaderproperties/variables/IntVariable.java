package it.aretesoftware.shadersee.shaderproperties.variables;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class IntVariable extends Variable {

    IntVariable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        defaults().space(10);
        add(new VisLabel(getVariableName() + ": ")).width(100);
        add(new VisTextField());
    }

}
