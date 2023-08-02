package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariablePrecision;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public abstract class Variable<T> extends Table implements Disposable {

    private final Main main;
    private final ShaderVariableQualifier qualifier;
    private final ShaderVariablePrecision precision;
    private final int type;
    private final String name;

    protected Variable(VariableBuilder builder) {
        this.main = builder.main;
        this.qualifier = builder.qualifier;
        this.precision = builder.precision;
        this.type = builder.type;
        this.name = builder.name;
    }

    protected abstract void populate();

    public static Variable<?> create(VariableBuilder builder) {
        Variable<?> variable;
        if (builder.qualifier != ShaderVariableQualifier.uniform) {
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
                case ShaderVariableType.IVEC2:
                    variable = new IVec2Variable(builder);
                    break;
                case ShaderVariableType.IVEC3:
                    variable = new IVec3Variable(builder);
                    break;
                case ShaderVariableType.IVEC4:
                    variable = new IVec4Variable(builder);
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
                case ShaderVariableType.MAT3:
                    variable = new Mat3Variable(builder);
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
        if (variable == null) {
            throw new GdxRuntimeException("Variable cannot be null.");
        }
        variable.populate();
        variable.setUniform(null);
        return variable;
    }

    protected Main getMain() {
        return main;
    }

    public ShaderVariableQualifier getVariableQualifier() {
        return qualifier;
    }

    public ShaderVariablePrecision getVariablePrecision() {
        return precision;
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
