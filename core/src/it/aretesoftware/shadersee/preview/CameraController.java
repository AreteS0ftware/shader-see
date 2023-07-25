package it.aretesoftware.shadersee.preview;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.preview.AddCameraZoomEvent;
import it.aretesoftware.shadersee.event.preview.CameraResetEvent;
import it.aretesoftware.shadersee.event.preview.CameraZoomChangeEvent;
import it.aretesoftware.shadersee.preview.Preview;

public class CameraController extends InputListener {

    private final Main main;
    private final Preview preview;
    private final Vector2 position = new Vector2();

    public CameraController(Main main, Preview preview) {
        this.main = main;
        this.preview = preview;

        main.addListener(new EventListener<AddCameraZoomEvent>(AddCameraZoomEvent.class, this) {
            @Override
            protected void fire(AddCameraZoomEvent event) {
                addCameraZoom(event.zoomOffset);
            }
        });
        main.addListener(new EventListener<CameraResetEvent>(CameraResetEvent.class, this) {
            @Override
            protected void fire(CameraResetEvent event) {
                resetCamera();
            }
        });
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

    private void addCameraZoom(float zoomIncrease) {
        OrthographicCamera camera = (OrthographicCamera) preview.getViewport().getCamera();
        camera.zoom += zoomIncrease;
        camera.zoom = MathUtils.clamp(camera.zoom, 0.5f, 7.5f);
        main.fire(new CameraZoomChangeEvent(camera.zoom));
    }

    private void resetCamera() {
        OrthographicCamera camera = (OrthographicCamera) preview.getViewport().getCamera();
        camera.position.setZero();
        camera.zoom = 1f;
        main.fire(new CameraZoomChangeEvent(camera.zoom));
    }

}
