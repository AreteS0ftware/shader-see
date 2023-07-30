package it.aretesoftware.shadersee.shaderproperties.variables;

import com.kotcrab.vis.ui.widget.VisLabel;

import it.aretesoftware.shadersee.utils.ShaderVariableType;

public class DummyVariable extends Variable<Object> {

    protected DummyVariable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        defaults().space(10);

        StringBuilder builder = new StringBuilder();
        builder.append(ShaderVariableType.toString(getVariableType()));
        builder.append(" ");
        builder.append(getVariablePrecision() == null ? "" : getVariablePrecision().toString());
        builder.append(" ");
        builder.append(getVariableName());
        builder.append(";");

        add(new VisLabel(builder.toString()));
    }

    @Override
    protected void setUniform(Object value) {

    }

}
