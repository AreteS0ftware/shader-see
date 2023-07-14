package it.aretesoftware.shadersee.shaderproperties.variables;

import com.kotcrab.vis.ui.widget.VisLabel;

import it.aretesoftware.shadersee.utils.ShaderVariableType;

public class DummyVariable extends Variable {

    protected DummyVariable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        defaults().space(10);
        add(new VisLabel(ShaderVariableType.toString(getVariableType()) + " " + getVariableName() + ";"));
    }

}
