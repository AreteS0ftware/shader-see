package it.aretesoftware.shadersee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kotcrab.vis.ui.VisUI;

public class Assets {

    private final Skin skin;
    private final Texture whitePixelTexture, checkeredBackgroundTexture;
    private final BitmapFont size100Font;

    Assets() {
        VisUI.load(skin = new Skin(Gdx.files.internal("neutralizer/neutralizer-ui.json")));

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        whitePixelTexture = new Texture(pixmap);
        pixmap.dispose();

        checkeredBackgroundTexture = new Texture(Gdx.files.internal("checkered_background.png"));
        checkeredBackgroundTexture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 100;
        params.borderWidth = 5f;
        params.borderColor = Color.BLACK;
        size100Font = generator.generateFont(params);
        generator.dispose();
    }

    public Skin getSkin() {
        return skin;
    }

    public Texture getCheckeredBackgroundTexture() {
        return checkeredBackgroundTexture;
    }

    public Texture getWhitePixelTexture() {
        return whitePixelTexture;
    }

    public BitmapFont getSize100Font() {
        return size100Font;
    }

}
