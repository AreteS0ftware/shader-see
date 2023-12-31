package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import it.aretesoftware.shadersee.event.Event;

public class ShaderProgramUpdateEvent extends Event {

    public final ShaderProgram shader;
    public final FileHandle vert, frag;

    public ShaderProgramUpdateEvent(ShaderProgram shader, FileHandle vert, FileHandle frag) {
        this.shader = shader;
        this.vert = vert;
        this.frag = frag;
    }
}
