package it.aretesoftware.shadersee.shaderproperties.variables;


import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class Vec2Variable extends Variable {

    Vec2Variable(ShaderVariableQualifier qualifier, int type, String uniformName) {
        super(qualifier, type, uniformName);
    }


    @Override
    protected void createFunctional(Main main) {
        defaults().space(10);
        add(new VisLabel(getVariableName() + ": ")).width(100);
        defaults().expandX().width(50).maxWidth(1000).fill();
        add(new VisTextField());
        add(new VisTextField());
    }

    @Override
    protected void createNonFunctional(Main main) {
        defaults().space(10);
        add(new VisLabel("vec2 " + getVariableName()));
    }
}
