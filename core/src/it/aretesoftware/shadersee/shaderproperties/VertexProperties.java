package it.aretesoftware.shadersee.shaderproperties;

import it.aretesoftware.shadersee.Main;

public class VertexProperties extends Properties {

    VertexProperties(Main main) {
        super(main);
    }

    //

    protected FileLocation createFileLocation() {
        VertexFileLocation fileLocation = new VertexFileLocation(getMain());
        fileLocation.setFilePath(getMain().getShaders().getVertexShaderAbsoluteFilePath());
        return fileLocation;
    }

    @Override
    protected String getInitialShaderSource() {
        return getMain().getShaders().getShader().getVertexShaderSource();
    }

    @Override
    protected String getTitle() {
        return "Vert Properties";
    }

}
