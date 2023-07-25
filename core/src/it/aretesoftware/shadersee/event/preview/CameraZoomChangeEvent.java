package it.aretesoftware.shadersee.event.preview;

import it.aretesoftware.shadersee.event.Event;

public class CameraZoomChangeEvent extends Event {

    public final float zoom;

    public CameraZoomChangeEvent(float zoom) {
        this.zoom = zoom;
    }
}
