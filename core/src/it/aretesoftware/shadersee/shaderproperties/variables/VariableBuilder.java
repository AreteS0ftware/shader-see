package it.aretesoftware.shadersee.shaderproperties.variables;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class VariableBuilder {

    public final Main main;
    public final ShaderVariableQualifier qualifier;
    public final int type;
    public final String name;

    VariableBuilder(Main main, ShaderVariableQualifier qualifier, int type, String name) {
        this.main = main;
        this.qualifier = qualifier;
        this.type = type;
        this.name = name;
    }

}
