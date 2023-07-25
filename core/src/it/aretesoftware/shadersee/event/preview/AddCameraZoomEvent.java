package it.aretesoftware.shadersee.event.preview;

import it.aretesoftware.shadersee.event.Event;

public class AddCameraZoomEvent extends Event {

    public final float zoomOffset;

    public AddCameraZoomEvent(float zoomOffset) {
        this.zoomOffset = zoomOffset;
    }
}
