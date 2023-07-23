package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import it.aretesoftware.shadersee.event.Event;

public class SetUTextureEvent extends SetUniformEvent {

    public final Texture u_texture;
    public final FileHandle fileHandle;

    public SetUTextureEvent(Texture u_texture, FileHandle fileHandle) {
        super("u_texture");
        this.u_texture = u_texture;
        this.fileHandle = fileHandle;
    }
}
