package it.aretesoftware.shadersee.preview;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.preview.AddCameraZoomEvent;
import it.aretesoftware.shadersee.event.preview.CameraResetEvent;
import it.aretesoftware.shadersee.event.preview.CameraZoomChangeEvent;

public class PreviewCameraController extends CameraController {

    public PreviewCameraController(Main main, Preview preview) {
        super(main, preview);
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
    protected void addCameraZoom(float zoomIncrease) {
        super.addCameraZoom(zoomIncrease);
        getMain().fire(new CameraZoomChangeEvent(getCamera().zoom));
    }

    @Override
    protected void resetCamera() {
        super.resetCamera();
        getMain().fire(new CameraZoomChangeEvent(getCamera().zoom));
    }

}
