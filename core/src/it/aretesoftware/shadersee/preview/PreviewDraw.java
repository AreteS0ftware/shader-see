package it.aretesoftware.shadersee.preview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.aretesoftware.shadersee.Assets;
import it.aretesoftware.shadersee.Data;
import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.Shaders;

public class PreviewDraw {

    PreviewDraw() {

    }

    //

    public void draw(Batch batch, Viewport viewport, Main main) {
        Data data = main.getData();
        Assets assets = main.getAssets();
        Shaders shaders = main.getShaders();
        batch.begin();
        drawCheckers(batch, viewport, assets);
        drawSolidColor(batch, assets, data);
        drawSprite(batch, shaders);
        batch.end();
    }

    private void drawCheckers(Batch batch, Viewport viewport, Assets assets) {
        final float VALUE = 500000;
        float width = VALUE;
        float height = VALUE;
        float x = -(VALUE / 2f);
        float y = -(VALUE / 2f);
        float scale = ((OrthographicCamera)viewport.getCamera()).zoom * 2;
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
        final float VALUE = 500000;
        float width = VALUE;
        float height = VALUE;
        float x = -(VALUE / 2f);
        float y = -(VALUE / 2f);
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
