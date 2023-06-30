package it.aretesoftware.shadersee.shaderproperties.variables;


import com.kotcrab.vis.ui.widget.VisLabel;

import it.aretesoftware.shadersee.Main;

public class Sampler2DVariable extends Variable {

    Sampler2DVariable(VariableBuilder builder) {
        super(builder);
    }


    @Override
    protected void createFunctional(Main main) {
        createNonFunctional(main);
    }

    @Override
    protected void createNonFunctional(Main main) {
        defaults().space(10);
        add(new VisLabel("sampler2D " + getVariableName() + ";"));
    }

}
