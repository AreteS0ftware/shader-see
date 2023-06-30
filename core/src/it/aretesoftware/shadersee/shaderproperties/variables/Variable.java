package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public abstract class Variable extends Table {

    private final ShaderVariableQualifier qualifier;
    private final int type;
    private final String name;

    protected Variable(ShaderVariableQualifier qualifier, int type, String name) {
        this.qualifier = qualifier;
        this.type = type;
        this.name = name;
    }

    protected abstract void createFunctional(Main main);

    protected abstract void createNonFunctional(Main main);

    public static Variable createVertexVariable(Main main, String qualifier, String type, String name) {
        Variable variable = create(qualifier, type, name);
        variable.createNonFunctional(main);
        return variable;
    }

    public static Variable createFragmentVariable(Main main, String qualifier, String type, String name) {
        Variable variable = create(qualifier, type, name);
        if (variable.getVariableQualifier() == ShaderVariableQualifier.uniform) {
            variable.createFunctional(main);
        }
        else {
            variable.createNonFunctional(main);
        }
        return variable;
    }

    public static Variable create(String qualifier, String type, String name) {
        ShaderVariableQualifier v_qualifier = ShaderVariableQualifier.valueOf(qualifier);
        int v_type = ShaderVariableType.valueOf(type);

        Variable variable;
        switch (v_type) {
            case ShaderVariableType.BOOL:
                 variable = new BoolVariable(v_qualifier, v_type, name);
                 break;
            case ShaderVariableType.INT:
                 variable = new IntVariable(v_qualifier, v_type, name);
                 break;
            case ShaderVariableType.FLOAT:
                 variable = new FloatVariable(v_qualifier, v_type, name);
                 break;
            case ShaderVariableType.VEC2:
                variable = new Vec2Variable(v_qualifier, v_type, name);
                break;
            case ShaderVariableType.VEC3:
                variable = new Vec3Variable(v_qualifier, v_type, name);
                break;
            case ShaderVariableType.VEC4:
                variable = new Vec4Variable(v_qualifier, v_type, name);
                break;
            case ShaderVariableType.MAT4:
                variable = new Mat4Variable(v_qualifier, v_type, name);
                break;
            case ShaderVariableType.SAMPLER2D:
                variable = new Sampler2DVariable(v_qualifier, v_type, name);
                break;
            default:
                variable = null;
        }

        return variable;
    }

    public ShaderVariableQualifier getVariableQualifier() {
        return qualifier;
    }

    public int getVariableType() {
        return type;
    }

    public String getVariableName() {
        return name;
    }

}
