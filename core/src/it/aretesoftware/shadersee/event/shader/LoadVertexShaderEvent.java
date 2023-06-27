package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.files.FileHandle;

import it.aretesoftware.shadersee.event.Event;

public class LoadVertexShaderEvent extends Event {

    public final FileHandle vert;

    public LoadVertexShaderEvent(FileHandle vert) {
        this.vert = vert;
    }
}
