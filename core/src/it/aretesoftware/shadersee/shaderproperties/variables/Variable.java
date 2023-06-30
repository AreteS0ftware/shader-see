package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public abstract class Variable extends Table {

    private final ShaderVariableQualifier qualifier;
    private final int type;
    private final String name;

    protected Variable(VariableBuilder builder) {
        this.qualifier = builder.qualifier;
        this.type = builder.type;
        this.name = builder.name;
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
        VariableBuilder builder = new VariableBuilder(ShaderVariableQualifier.valueOf(qualifier), ShaderVariableType.valueOf(type), name);
        Variable variable;
        switch (builder.type) {
            case ShaderVariableType.BOOL:
                 variable = new BoolVariable(builder);
                 break;
            case ShaderVariableType.INT:
                 variable = new IntVariable(builder);
                 break;
            case ShaderVariableType.FLOAT:
                 variable = new FloatVariable(builder);
                 break;
            case ShaderVariableType.VEC2:
                variable = new Vec2Variable(builder);
                break;
            case ShaderVariableType.VEC3:
                variable = new Vec3Variable(builder);
                break;
            case ShaderVariableType.VEC4:
                variable = new Vec4Variable(builder);
                break;
            case ShaderVariableType.MAT4:
                variable = new Mat4Variable(builder);
                break;
            case ShaderVariableType.SAMPLER2D:
                variable = new Sampler2DVariable(builder);
                break;
            default:
                variable = null;
                break;
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
