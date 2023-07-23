package it.aretesoftware.shadersee.previewproperties;

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
import com.kotcrab.vis.ui.widget.color.ColorPicker;
import com.kotcrab.vis.ui.widget.color.ColorPickerAdapter;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.preview.BackgroundColorChangeEvent;

public class BackgroundColor extends Table {

    private final Main main;

    BackgroundColor(final Main main) {
        this.main = main;
        add(new VisLabel("Background")).expandX().center().colspan(2);
        row();
        defaults().space(10);
        add(createColorButton(main)).growX();
    }

    private Table createColorButton(final Main main) {
        VisImageButton.VisImageButtonStyle style = new VisImageButton.VisImageButtonStyle();
        Drawable drawable = new SpriteDrawable(new Sprite(main.getAssets().getWhitePixelTexture()));
        style.down = drawable;
        style.up = drawable;
        final VisImageButton setColorButton = new VisImageButton(style);
        final ColorPicker dialogColor = new ColorPicker();
        dialogColor.setListener(new ColorPickerListener(setColorButton));
        setColorButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                main.getStage().addActor(dialogColor.fadeIn());
            }
        });
        setColorButton.setColor(Color.CLEAR);
        setColorButton.setName("ColorButton");

        NinePatch ninePatch = new NinePatch(main.getAssets().getCheckeredBackgroundTexture(), 0, 0, 20, 20);
        VisImage image = new VisImage(ninePatch);
        Stack stack = new Stack();
        stack.add(image);
        stack.add(setColorButton);

        Table table = new Table();
        table.defaults().space(8);
        table.add(new VisLabel("Color: "));
        table.add(stack).expand().width(50).maxWidth(1500).height(20).maxHeight(100).fill();

        return table;
    }

    private class ColorPickerListener extends ColorPickerAdapter {

        private final VisImageButton button;

        ColorPickerListener(VisImageButton button) {
            this.button = button;
        }

        @Override
        public void finished(Color newColor) {
            button.setColor(newColor);
            main.fire(new BackgroundColorChangeEvent(newColor));
        }

    }

}
