package it.aretesoftware.shadersee.shaderproperties;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.shaderproperties.variables.Variable;

public class VertexProperties extends Properties {

    public VertexProperties(Main main) {
        super(main);
    }

    //

    protected FileLocation createFileLocation() {
        VertexFileLocation fileLocation = new VertexFileLocation(getMain());
        fileLocation.setFilePath(getMain().getShaders().getVertexShaderFilePath());
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

    @Override
    protected Variable createVariable(String qualifier, String type, String name) {
        return Variable.createVertexVariable(getMain(), qualifier, type, name);
    }

}
