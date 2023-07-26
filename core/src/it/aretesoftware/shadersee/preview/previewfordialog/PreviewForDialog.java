package it.aretesoftware.shadersee.preview.previewfordialog;

import com.badlogic.gdx.graphics.Camera;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.preview.CameraController;
import it.aretesoftware.shadersee.preview.Preview;

public class PreviewForDialog extends Preview {

    private final Camera defaultCamera;
    private PreviewForDialogCameraController cameraController;

    public PreviewForDialog(Main main) {
        super(main);
        defaultCamera = getViewport().getCamera();
    }

    @Override
    protected PreviewForDialogCameraController createCameraController(Main main) {
        cameraController = new PreviewForDialogCameraController(main, this);
        return cameraController;
    }

    public void setCamera(Camera camera) {
        getViewport().setCamera(camera);
    }

    public void resetCameraToDefault() {
        getViewport().setCamera(defaultCamera);
    }

    public void enableCameraEvents() {
        cameraController.enableEvents();
    }

    public void disableCameraEvents() {
        cameraController.disableEvents();
    }


}
