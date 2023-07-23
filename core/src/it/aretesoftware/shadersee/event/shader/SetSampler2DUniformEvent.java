package it.aretesoftware.shadersee.event.shader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class SetSampler2DUniformEvent extends SetUniformEvent {

    public final Texture uniformValue;
    public final FileHandle fileHandle;

    public SetSampler2DUniformEvent(String uniformName, Texture uniformValue, FileHandle fileHandle) {
        super(uniformName);
        this.uniformValue = uniformValue;
        this.fileHandle = fileHandle;
    }
}
