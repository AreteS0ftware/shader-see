package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.files.FileHandle;

import it.aretesoftware.shadersee.event.Event;

public class LoadShaderEvent extends Event {

    public final FileHandle vert, frag;

    public LoadShaderEvent(FileHandle vert, FileHandle frag) {
        this.vert = vert;
        this.frag = frag;
    }
}
