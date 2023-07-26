package it.aretesoftware.shadersee.preview.previewfordialog;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.preview.CameraZoomChangeEvent;
import it.aretesoftware.shadersee.preview.CameraController;
import it.aretesoftware.shadersee.preview.Preview;

public class PreviewForDialogCameraController extends CameraController {

    private boolean eventsEnabled;

    public PreviewForDialogCameraController(Main main, Preview preview) {
        super(main, preview);
    }

    //

    public void enableEvents() {
        eventsEnabled = true;
    }

    public void disableEvents() {
        eventsEnabled = false;
    }

    @Override
    protected void addCameraZoom(float zoomIncrease) {
        super.addCameraZoom(zoomIncrease);
        if (eventsEnabled) {
            getMain().fire(new CameraZoomChangeEvent(getCamera().zoom));
        }
    }

    @Override
    protected void resetCamera() {
        super.resetCamera();
        if (eventsEnabled) {
            getMain().fire(new CameraZoomChangeEvent(getCamera().zoom));
        }
    }

}
