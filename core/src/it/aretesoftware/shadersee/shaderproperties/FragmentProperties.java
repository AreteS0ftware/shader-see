package it.aretesoftware.shadersee.shaderproperties;


import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.shaderproperties.variables.Variable;

public class FragmentProperties extends Properties {

    FragmentProperties(Main main) {
        super(main);
    }

    //

    protected FileLocation createFileLocation() {
        FragmentFileLocation fileLocation = new FragmentFileLocation(getMain());
        fileLocation.setFilePath(getMain().getShaders().getFragmentShaderFilePath());
        return fileLocation;
    }

    @Override
    protected String getInitialShaderSource() {
        return getMain().getShaders().getShader().getFragmentShaderSource();
    }

    @Override
    protected String getTitle() {
        return "Frag Properties";
    }

    @Override
    protected Variable createVariable(String qualifier, String type, String name) {
        return Variable.createFragmentVariable(getMain(), qualifier, type, name);
    }

}
