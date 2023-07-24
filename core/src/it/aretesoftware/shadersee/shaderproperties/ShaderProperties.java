package it.aretesoftware.shadersee.shaderproperties;

import com.kotcrab.vis.ui.widget.VisSplitPane;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.ShaderProgramUpdateEvent;
import it.aretesoftware.shadersee.event.shaderproperties.HideAttributesEvent;
import it.aretesoftware.shadersee.event.shaderproperties.HideUniformsEvent;
import it.aretesoftware.shadersee.event.shaderproperties.HideVaryingEvent;
import it.aretesoftware.shadersee.event.shaderproperties.ShowAttributesEvent;
import it.aretesoftware.shadersee.event.shaderproperties.ShowUniformsEvent;
import it.aretesoftware.shadersee.event.shaderproperties.ShowVaryingEvent;

public class ShaderProperties extends VisSplitPane {

    private final Main main;
    private final VertexProperties vertexProperties;
    private final FragmentProperties fragmentProperties;

    public ShaderProperties(Main main) {
        super(null, null, true);
        this.main = main;

        vertexProperties = new VertexProperties(main);
        fragmentProperties = new FragmentProperties(main);
        setFirstWidget(vertexProperties);
        setSecondWidget(fragmentProperties);
        setMinSplitAmount(0.05f);
        setMaxSplitAmount(0.95f);

        addListeners();
    }

    private void addListeners() {
        // ShaderLoadEvent
        main.addListener(new EventListener<ShaderProgramUpdateEvent>(ShaderProgramUpdateEvent.class, this) {
            @Override
            protected void fire(ShaderProgramUpdateEvent event) {
                vertexProperties.getFileLocation().setFilePath(event.vert.path());
                fragmentProperties.getFileLocation().setFilePath(event.frag.path());
                vertexProperties.populate(event.shader.getVertexShaderSource());
                fragmentProperties.populate(event.shader.getFragmentShaderSource());
            }
        });

        // Show
        main.addListener(new EventListener<ShowUniformsEvent>(ShowUniformsEvent.class, this) {
            @Override
            protected void fire(ShowUniformsEvent event) {
                vertexProperties.setShowUniforms(true);
                fragmentProperties.setShowUniforms(true);
                vertexProperties.rebuild();
                fragmentProperties.rebuild();
            }
        });
        main.addListener(new EventListener<ShowAttributesEvent>(ShowAttributesEvent.class, this) {
            @Override
            protected void fire(ShowAttributesEvent event) {
                vertexProperties.setShowAttributes(true);
                fragmentProperties.setShowAttributes(true);
                vertexProperties.rebuild();
                fragmentProperties.rebuild();
            }
        });
        main.addListener(new EventListener<ShowVaryingEvent>(ShowVaryingEvent.class, this) {
            @Override
            protected void fire(ShowVaryingEvent event) {
                vertexProperties.setShowVarying(true);
                fragmentProperties.setShowVarying(true);
                vertexProperties.rebuild();
                fragmentProperties.rebuild();
            }
        });

        // Hide
        main.addListener(new EventListener<HideUniformsEvent>(HideUniformsEvent.class, this) {
            @Override
            protected void fire(HideUniformsEvent event) {
                vertexProperties.setShowUniforms(false);
                fragmentProperties.setShowUniforms(false);
                vertexProperties.rebuild();
                fragmentProperties.rebuild();
            }
        });
        main.addListener(new EventListener<HideAttributesEvent>(HideAttributesEvent.class, this) {
            @Override
            protected void fire(HideAttributesEvent event) {
                vertexProperties.setShowAttributes(false);
                fragmentProperties.setShowAttributes(false);
                vertexProperties.rebuild();
                fragmentProperties.rebuild();
            }
        });
        main.addListener(new EventListener<HideVaryingEvent>(HideVaryingEvent.class, this) {
            @Override
            protected void fire(HideVaryingEvent event) {
                vertexProperties.setShowVarying(false);
                fragmentProperties.setShowVarying(false);
                vertexProperties.rebuild();
                fragmentProperties.rebuild();
            }
        });
    }

}
