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

    Assets() {
        VisUI.load(skin = new Skin(Gdx.files.internal("skins/neutralizer/neutralizer-ui.json")));

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        whitePixelTexture = new Texture(pixmap);
        pixmap.dispose();

        checkeredBackgroundTexture = new Texture(Gdx.files.internal("textures/checkered_background.png"));
        checkeredBackgroundTexture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
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

}
