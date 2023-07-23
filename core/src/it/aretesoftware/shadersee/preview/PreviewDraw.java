package it.aretesoftware.shadersee.preview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import it.aretesoftware.shadersee.Assets;
import it.aretesoftware.shadersee.Data;
import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.Shaders;

public class PreviewDraw {

    private final Preview preview;

    PreviewDraw(Preview preview) {
        this.preview = preview;
    }

    //

    public void draw(Batch batch, Main main) {
        Data data = main.getData();
        Assets assets = main.getAssets();
        Shaders shaders = main.getShaders();
        batch.begin();
        drawCheckers(batch, assets);
        drawSolidColor(batch, assets, data);
        drawSprite(batch, shaders);
        batch.end();
    }

    private void drawCheckers(Batch batch, Assets assets) {
        float width = 5000;
        float height = 5000;
        float x = -2500;
        float y = -2500;
        float scale = ((OrthographicCamera)preview.getViewport().getCamera()).zoom * 2;
        batch.setColor(Color.WHITE);
        batch.draw(assets.getCheckeredBackgroundTexture(),
                x, y,
                width / 2f, height / 2f,
                width, height,
                1f, 1f,
                0,
                0, 0,
                (int)(width / scale), (int)(height / scale),
                false, false);
    }

    private void drawSolidColor(Batch batch, Assets assets, Data data) {
        float width = 5000;
        float height = 5000;
        float x = -2500;
        float y = -2500;
        float scale = 1f;
        batch.setColor(data.getBackgroundColor());
        batch.draw(assets.getWhitePixelTexture(),
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

    private void drawSprite(Batch batch, Shaders shaders) {
        Texture u_texture = shaders.getUTexture();
        shaders.bindShader(batch);
        batch.draw(u_texture, -u_texture.getWidth() / 2f, -u_texture.getHeight() / 2f);
        shaders.unbindShader(batch);
    }

}
