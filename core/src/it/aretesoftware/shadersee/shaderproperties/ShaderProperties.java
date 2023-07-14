package it.aretesoftware.shadersee.shaderproperties;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisSplitPane;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;

public class ShaderProperties extends VisSplitPane {

    private final VertexProperties vertexProperties;
    private final FragmentProperties fragmentProperties;

    public ShaderProperties(Main main) {
        super(null, null, true);
        setMinSplitAmount(0.05f);
        setMaxSplitAmount(0.95f);

        vertexProperties = new VertexProperties(main);
        fragmentProperties = new FragmentProperties(main);
        main.addListener(new EventListener<ShaderLoadEvent>(ShaderLoadEvent.class, this) {
            @Override
            protected void fire(ShaderLoadEvent event) {
                vertexProperties.getFileLocation().setFilePath(event.vert.path());
                fragmentProperties.getFileLocation().setFilePath(event.frag.path());
                vertexProperties.rebuild(event.shader.getVertexShaderSource());
                fragmentProperties.rebuild(event.shader.getFragmentShaderSource());
            }
        });

        setFirstWidget(vertexProperties);
        setSecondWidget(fragmentProperties);
    }

}
