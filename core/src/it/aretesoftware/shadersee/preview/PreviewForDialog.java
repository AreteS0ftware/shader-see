package it.aretesoftware.shadersee.preview;

import com.badlogic.gdx.graphics.Camera;

import it.aretesoftware.shadersee.Main;

public class PreviewForDialog extends Preview {

    private final Camera defaultCamera;

    public PreviewForDialog(Main main) {
        super(main);
        defaultCamera = getViewport().getCamera();
    }

    @Override
    protected CameraController createCameraController(Main main) {
        return new CameraController(main, this);
    }

    public void setCamera(Camera camera) {
        getViewport().setCamera(camera);
    }

    public void resetCameraToDefault() {
        getViewport().setCamera(defaultCamera);
    }


}
