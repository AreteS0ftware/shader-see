package it.aretesoftware.shadersee.shaderproperties.variables;


import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class Mat4Variable extends Variable {

    Mat4Variable(ShaderVariableQualifier qualifier, int type, String uniformName) {
        super(qualifier, type, uniformName);
    }


    @Override
    protected void createFunctional(Main main) {
        createNonFunctional(main);
    }

    @Override
    protected void createNonFunctional(Main main) {
        defaults().space(10);
        add(new VisLabel("mat4 " + getVariableName()));
    }

}