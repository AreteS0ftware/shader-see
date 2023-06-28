package it.aretesoftware.shadersee.preview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import it.aretesoftware.shadersee.Main;

public class PreviewDraw {

    private final Texture checkeredBackground;
    private final Texture whitePixel;
    private final Texture badlogic;

    public PreviewDraw() {
        checkeredBackground = new Texture(Gdx.files.internal("checkered_background.png"));
        checkeredBackground.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        whitePixel = new Texture(pixmap);
        pixmap.dispose();

        badlogic = new Texture("badlogic.jpg");
    }

    //

    public void draw(Batch batch, ShaderProgram shader) {
        batch.begin();
        drawCheckers(batch);
        drawSolidColor(batch);
        drawSprite(batch, shader);
        batch.end();
    }

    private void drawCheckers(Batch batch) {
        float width = 5000;
        float height = 5000;
        float x = -2500;
        float y = -2500;
        float scale = 1;
        batch.setColor(Color.WHITE);
        batch.draw(checkeredBackground,
                x, y,
                width / 2f, height / 2f,
                width, height,
                1f, 1f,
                0,
                0, 0,
                (int)(width / scale), (int)(height / scale),
                false, false);
    }

    private void drawSolidColor(Batch batch) {
        float width = 5000;
        float height = 5000;
        float x = 0;
        float y = 0;
        float scale = 1;
        batch.setColor(Color.CLEAR);
        batch.draw(whitePixel,
                x, y,
                width / 2f, height / 2f,
                width, height,
                1f, 1f,
                0,
                0, 0,
                (int)(width / scale), (int)(height / scale),
                false, false);
        batch.setColor(Color.WHITE);
    }

    private void drawSprite(Batch batch, ShaderProgram shader) {
        batch.setShader(shader);
        batch.draw(badlogic, 0, 0);
        batch.setShader(null);
    }

}
