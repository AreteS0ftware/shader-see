package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public abstract class Variable extends Table {

    private final ShaderVariableQualifier qualifier;
    private final int type;
    private final String name;

    protected Variable(Main main, ShaderVariableQualifier qualifier, int type, String name) {
        this.qualifier = qualifier;
        this.type = type;
        this.name = name;
    }

    public static Variable create(Main main, String qualifier, String type, String name) {
        ShaderVariableQualifier v_qualifier = ShaderVariableQualifier.valueOf(qualifier);
        int v_type = ShaderVariableType.valueOf(type);
        switch (v_type) {
            case ShaderVariableType.BOOL:
                return new BoolVariable(main, v_qualifier, v_type, name);
            case ShaderVariableType.INT:
                return new FloatVariable(main, v_qualifier, v_type, name);
            case ShaderVariableType.FLOAT:
                return new FloatVariable(main, v_qualifier, v_type, name);
            case ShaderVariableType.VEC2:
                return new Vec2Variable(main, v_qualifier, v_type, name);
        }
        return new BoolVariable(main, v_qualifier, v_type, name);
    }

    public ShaderVariableQualifier getQualifier() {
        return qualifier;
    }

    public int getType() {
        return type;
    }

    public String getVariableName() {
        return name;
    }

}
