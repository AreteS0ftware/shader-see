package it.aretesoftware.shadersee.shaderproperties;

import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;

public class FragmentProperties extends Properties {

    public FragmentProperties(Main main) {
        super(main, main.getShaders().getShader().getFragmentShaderSource());
    }

    //

    protected FileLocation createFileLocation() {
        FileLocation fileLocation = new FileLocation("Fragment: ");
        fileLocation.setFilePath(getMain().getShaders().getFragmentShaderFilePath());
        getMain().addListener(new EventListener<ShaderLoadEvent>(ShaderLoadEvent.class, this) {
            @Override
            protected void fire(ShaderLoadEvent event) {
                fileLocation.setFilePath(event.frag.path());
                rebuild(event.shader.getFragmentShaderSource());
            }
        });
        return fileLocation;
    }

    @Override
    protected String getTitle() {
        return "Frag Properties";
    }

    @Override
    protected boolean isVertex() {
        return false;
    }
}
