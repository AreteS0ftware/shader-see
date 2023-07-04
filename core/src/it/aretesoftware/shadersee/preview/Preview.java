package it.aretesoftware.shadersee.preview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.Shaders;

public class Preview extends WidgetGroup {

    private final Vector2 temp;
    private final Viewport viewport;
    private int viewportOriginalX, viewportOriginalY;
    private final PreviewDraw previewDraw;

    public Preview() {
        this.temp = new Vector2();
        this.viewport = new ScreenViewport();
        previewDraw = new PreviewDraw();
        addListener(new CameraController(this));
    }

    @Override
    public void act(float delta) {
        temp.set(0, 0);
        localToScreenCoordinates(temp);
        viewport.setScreenPosition(viewportOriginalX + MathUtils.round(temp.x), viewportOriginalY + MathUtils.round(Gdx.graphics.getHeight() - temp.y));
    }

    @Override
    public void layout() {
        temp.set(0, 0);
        localToScreenCoordinates(temp);
        viewport.update(MathUtils.round(getWidth()), MathUtils.round(getHeight()));
        viewportOriginalX = viewport.getScreenX();
        viewportOriginalY = viewport.getScreenY();
        getStage().getViewport().apply();
    }

    //

    public void draw(Batch batch, Main main) {
        Camera camera = viewport.getCamera();
        camera.update();
        viewport.apply(false);
        batch.setProjectionMatrix(camera.combined);
        previewDraw.draw(batch, main);
    }

    public Viewport getViewport() {
        return viewport;
    }

}
