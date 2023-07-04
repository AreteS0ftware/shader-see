package it.aretesoftware.shadersee.shaderproperties.variables;


import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisRadioButton;
import com.kotcrab.vis.ui.widget.VisTextField;

public class Vec2Variable extends Variable {

    Vec2Variable(VariableBuilder builder) {
        super(builder);
    }


    @Override
    protected void createFunctional() {
        defaults().space(10);
        add(new VisLabel(getVariableName() + ": ")).width(100);
        defaults().expandX().width(50).maxWidth(1000).fill();
        add(new VisTextField());
        add(new VisTextField());
        row();
        defaults().reset();

        ButtonGroup<VisRadioButton> buttonGroup = new ButtonGroup<>();
        VisRadioButton resolutionRadioButton = new VisRadioButton("Resolution");
        VisRadioButton pointerRadioButton = new VisRadioButton("Pointer");
        VisRadioButton noneRadioButton = new VisRadioButton("None");
        buttonGroup.add(resolutionRadioButton);
        buttonGroup.add(pointerRadioButton);
        buttonGroup.add(noneRadioButton);
        noneRadioButton.setChecked(true);

        add(resolutionRadioButton);
        add(pointerRadioButton);
        add(noneRadioButton);
    }

    @Override
    protected void createNonFunctional() {
        defaults().space(10);
        add(new VisLabel("vec2 " + getVariableName() + ";"));
    }
}
