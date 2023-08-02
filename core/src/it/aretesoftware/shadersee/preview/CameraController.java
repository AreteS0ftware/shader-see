package it.aretesoftware.shadersee.preview;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import it.aretesoftware.shadersee.Main;

public class CameraController extends InputListener {

    private final Main main;
    private final Preview preview;
    private final Vector2 position = new Vector2();

    public CameraController(Main main, Preview preview) {
        this.main = main;
        this.preview = preview;
    }


    //

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Stage stage = event.getStage();
        Actor listenerActor = event.getListenerActor();
        stage.setScrollFocus(listenerActor);

        if (button != Input.Buttons.MIDDLE) {
            return false;
        }

        position.set(x, y);
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        final OrthographicCamera camera = (OrthographicCamera) preview.getViewport().getCamera();
        final float cameraZoom = camera.zoom;

        float addX = (position.x - x) * cameraZoom;
        float addY = (position.y - y) * cameraZoom;
        camera.position.add(addX, addY, 0);
        position.set(x, y);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
        addCameraZoom(amountY * 0.1f);
        return true;
    }

    //

    protected void addCameraZoom(float zoomIncrease) {
        OrthographicCamera camera = (OrthographicCamera) preview.getViewport().getCamera();
        camera.zoom += zoomIncrease;
        camera.zoom = MathUtils.clamp(camera.zoom, 0.5f, 2.5f);
    }

    protected void resetCamera() {
        OrthographicCamera camera = (OrthographicCamera) preview.getViewport().getCamera();
        camera.position.setZero();
        camera.zoom = 1f;
    }

    protected OrthographicCamera getCamera() {
        return (OrthographicCamera) preview.getViewport().getCamera();
    }

    protected Main getMain() {
        return main;
    }

}
