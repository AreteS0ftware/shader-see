package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.files.FileHandle;

import it.aretesoftware.shadersee.event.Event;

public class LoadFragmentShaderEvent extends Event {

    public final FileHandle frag;

    public LoadFragmentShaderEvent(FileHandle frag) {
        this.frag = frag;
    }
}
