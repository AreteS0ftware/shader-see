package it.aretesoftware.shadersee.event.preview;

import com.badlogic.gdx.graphics.Color;

import it.aretesoftware.shadersee.event.Event;

public class BackgroundColorChangeEvent extends Event {

    public final Color newColor;

    public BackgroundColorChangeEvent(Color newColor) {
        this.newColor = newColor;
    }

}
