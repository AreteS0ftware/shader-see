package it.aretesoftware.shadersee.shaderproperties;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;

public class VertexProperties extends Properties {

    public VertexProperties(Main main) {
        super(main, main.getShaders().getShader().getVertexShaderSource());
    }

    //

    protected FileLocation createFileLocation() {
        FileLocation fileLocation = new FileLocation("Vertex: ");
        fileLocation.setFilePath(getMain().getShaders().getVertexShaderFilePath());
        getMain().addListener(new EventListener<ShaderLoadEvent>(ShaderLoadEvent.class, this) {
            @Override
            protected void fire(ShaderLoadEvent event) {
                fileLocation.setFilePath(event.vert.path());
                rebuild(event.shader.getVertexShaderSource());
            }
        });
        return fileLocation;
    }

    @Override
    protected String getTitle() {
        return "Vert Properties";
    }

    @Override
    protected boolean isVertex() {
        return true;
    }
}
