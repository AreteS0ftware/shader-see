package it.aretesoftware.shadersee.shaderproperties.variables;


import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

public class BVec3Variable extends Variable<Object> {

    BVec3Variable(VariableBuilder builder) {
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
    protected void setUniform(Object value) {

    }

}
