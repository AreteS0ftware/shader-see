package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public abstract class Variable extends Table {

    private final Main main;
    private final ShaderVariableQualifier qualifier;
    private final int type;
    private final String name;

    protected Variable(VariableBuilder builder) {
        this.main = builder.main;
        this.qualifier = builder.qualifier;
        this.type = builder.type;
        this.name = builder.name;
    }

    protected abstract void createFunctional();

    protected abstract void createNonFunctional();

    public static Variable createVertexVariable(Main main, String qualifier, String type, String name) {
        Variable variable = create(main, qualifier, type, name);
        variable.createNonFunctional();
        return variable;
    }

    public static Variable createFragmentVariable(Main main, String qualifier, String type, String name) {
        Variable variable = create(main, qualifier, type, name);
        if (variable.getVariableQualifier() == ShaderVariableQualifier.uniform) {
            variable.createFunctional();
        }
        else {
            variable.createNonFunctional();
        }
        return variable;
    }

    public static Variable create(Main main, String qualifier, String type, String name) {
        VariableBuilder builder = new VariableBuilder(main, ShaderVariableQualifier.valueOf(qualifier), ShaderVariableType.valueOf(type), name);
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

    public Main getMain() {
        return main;
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
