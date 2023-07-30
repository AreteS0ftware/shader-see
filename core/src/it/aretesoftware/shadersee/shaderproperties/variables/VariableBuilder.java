package it.aretesoftware.shadersee.shaderproperties.variables;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariablePrecision;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public class VariableBuilder {

    public final Main main;
    public final ShaderVariableQualifier qualifier;
    public final ShaderVariablePrecision precision;
    public final int type;
    public final String name;

    public VariableBuilder(Main main, String qualifier, String precision, String type, String name) {
        this(main, ShaderVariableQualifier.valueOf(qualifier), precision != null ? ShaderVariablePrecision.valueOf(precision.toLowerCase()) : null, ShaderVariableType.toInt(type), name);
    }

    public VariableBuilder(Main main, ShaderVariableQualifier qualifier, ShaderVariablePrecision precision, int type, String name) {
        this.main = main;
        this.qualifier = qualifier;
        this.precision = precision;
        this.type = type;
        this.name = name;
    }

}
