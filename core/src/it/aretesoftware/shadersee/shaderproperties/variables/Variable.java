package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public abstract class Variable<T> extends Table implements Disposable {

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

    protected abstract void populate();

    public static Variable create(Main main, String qualifier, String type, String name) {
        VariableBuilder builder = new VariableBuilder(main, ShaderVariableQualifier.valueOf(qualifier), ShaderVariableType.toInt(type), name);
        Variable variable;
        if (ShaderVariableQualifier.valueOf(qualifier) != ShaderVariableQualifier.uniform) {
            variable = new DummyVariable(builder);
        }
        else {
            switch (builder.type) {
                case ShaderVariableType.BOOL:
                    variable = new BoolVariable(builder);
                    break;
                case ShaderVariableType.INT:
                case ShaderVariableType.UINT:
                    variable = new IntVariable(builder);
                    break;
                case ShaderVariableType.FLOAT:
                case ShaderVariableType.DOUBLE:
                    variable = new FloatVariable(builder);
                    break;
                case ShaderVariableType.BVEC2:
                    variable = new BVec2Variable(builder);
                    break;
                case ShaderVariableType.BVEC3:
                    variable = new BVec3Variable(builder);
                    break;
                case ShaderVariableType.BVEC4:
                    variable = new BVec4Variable(builder);
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
        }
        variable.populate();
        variable.setUniform(null);
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

    @Override
    public void dispose () {
        main.removeListenersOfBind(this);
    }

    protected abstract void setUniform(T value);

}
