package it.aretesoftware.shadersee.shaderproperties.variables;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerListener;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.SetVec4UniformEvent;
import it.aretesoftware.shadersee.previewproperties.BackgroundColor;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public class Vec4Variable extends Variable<Object> {

    Vec4Variable(VariableBuilder builder) {
        super(builder);
    }


    @Override
    protected void populate() {
        defaults().space(10);
        add(new VisLabel(getVariableName()));
        add(createColorButton()).growX();
    }

    private Table createColorButton() {
        VisImageButton.VisImageButtonStyle style = new VisImageButton.VisImageButtonStyle();
        Drawable drawable = new SpriteDrawable(new Sprite(getMain().getAssets().getWhitePixelTexture()));
        style.down = drawable;
        style.up = drawable;
        final VisImageButton setColorButton = new VisImageButton(style);
        final ColorPicker dialogColor = new ColorPicker();
        dialogColor.setListener(new ColorPickerListener() {
            @Override
            public void canceled(Color oldColor) {

            }

            @Override
            public void changed(Color newColor) {

            }

            @Override
            public void reset(Color previousColor, Color newColor) {

            }

            @Override
            public void finished(Color newColor) {
                setColorButton.setColor(newColor);
                getMain().fire(new SetVec4UniformEvent(getVariableName(), newColor));
            }
        });
        setColorButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                getMain().getStage().addActor(dialogColor.fadeIn());
            }
        });
        setColorButton.setColor(Color.CLEAR);
        setColorButton.setName("ColorButton");

        NinePatch ninePatch = new NinePatch(getMain().getAssets().getCheckeredBackgroundTexture(), 0, 0, 20, 20);
        VisImage image = new VisImage(ninePatch);
        Stack stack = new Stack();
        stack.add(image);
        stack.add(setColorButton);

        Table table = new Table();
        table.defaults().space(8);
        table.add(stack).expand().width(50).maxWidth(1500).height(20).maxHeight(100).fill();

        return table;
    }

    @Override
    protected void setUniform(Object value) {

    }

}
