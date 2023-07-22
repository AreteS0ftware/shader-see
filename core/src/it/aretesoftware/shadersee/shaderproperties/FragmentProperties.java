package it.aretesoftware.shadersee.shaderproperties;


import it.aretesoftware.shadersee.Main;

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

}
