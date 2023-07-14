package it.aretesoftware.shadersee.shaderproperties.variables;


import com.kotcrab.vis.ui.widget.VisLabel;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public class Sampler2DVariable extends Variable {

    Sampler2DVariable(VariableBuilder builder) {
        super(builder);
    }


    @Override
    protected void populate() {
        defaults().space(10);
        add(new VisLabel(ShaderVariableType.toString(getVariableType()) + " " + getVariableName() + ";"));
    }

}
