package it.aretesoftware.shadersee.shaderproperties;

import com.badlogic.gdx.files.FileHandle;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.shader.LoadFragmentShaderEvent;

public class FragmentFileLocation extends FileLocation {

    public FragmentFileLocation(Main main) {
        super(main);
    }

    @Override
    protected Event getLoadShaderEvent(FileHandle fileHandle) {
        return new LoadFragmentShaderEvent(fileHandle);
    }

    @Override
    protected String getTitleText() {
        return "Fragment Shader Source";
    }
}
