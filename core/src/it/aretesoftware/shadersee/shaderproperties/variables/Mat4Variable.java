package it.aretesoftware.shadersee.shaderproperties.variables;


import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class Mat4Variable extends Variable {

    Mat4Variable(VariableBuilder builder) {
        super(builder);
    }


    @Override
    protected void createFunctional() {
        createNonFunctional();
    }

    @Override
    protected void createNonFunctional() {
        defaults().space(10);
        add(new VisLabel("mat4 " + getVariableName() + ";"));
    }

}
