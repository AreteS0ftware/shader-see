package it.aretesoftware.shadersee.shaderproperties.variables;

import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class VariableBuilder {

    public final ShaderVariableQualifier qualifier;
    public final int type;
    public final String name;

    VariableBuilder(ShaderVariableQualifier qualifier, int type, String name) {
        this.qualifier = qualifier;
        this.type = type;
        this.name = name;
    }

}
