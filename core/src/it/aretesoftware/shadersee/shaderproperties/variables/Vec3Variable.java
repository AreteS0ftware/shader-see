package it.aretesoftware.shadersee.shaderproperties.variables;


import com.badlogic.gdx.math.Vector3;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

public class Vec3Variable extends Variable<Vector3> {

    Vec3Variable(VariableBuilder builder) {
        super(builder);
    }


    @Override
    protected void populate() {
        defaults().space(5);
        add(new VisLabel(getVariableName() + ": ")).width(100);
        defaults().expandX().width(50).maxWidth(1000).fill();
        add(new VisTextField());
        add(new VisTextField());
        add(new VisTextField());
    }

    @Override
    protected void setUniform(Vector3 value) {

    }

}
