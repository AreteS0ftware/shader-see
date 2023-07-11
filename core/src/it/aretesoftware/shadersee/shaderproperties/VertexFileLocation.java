package it.aretesoftware.shadersee.shaderproperties;

import com.badlogic.gdx.files.FileHandle;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.shader.LoadVertexShaderEvent;

public class VertexFileLocation extends FileLocation {

    public VertexFileLocation(Main main) {
        super(main);
    }

    @Override
    protected Event getLoadShaderEvent(FileHandle fileHandle) {
        return new LoadVertexShaderEvent(fileHandle);
    }

    @Override
    protected String getTitleText() {
        return "Vertex Shader Source";
    }
}
