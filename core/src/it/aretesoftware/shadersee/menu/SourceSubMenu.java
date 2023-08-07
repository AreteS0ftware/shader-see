package it.aretesoftware.shadersee.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.Shaders;
import it.aretesoftware.shadersee.dialog.DialogSource;

public class SourceSubMenu extends PopupMenu  {

    SourceSubMenu(Main main) {
        MenuItem vertexShaderSourceMenuItem = createVertexShaderSourceMenuItem(main);
        MenuItem fragmentShaderSourceMenuItem = createFragmentShaderSourceMenuItem(main);
        addItem(vertexShaderSourceMenuItem);
        addItem(fragmentShaderSourceMenuItem);
    }

    private MenuItem createVertexShaderSourceMenuItem(Main main) {
        MenuItem menuItem = new MenuItem("Vertex Shader Source");
        menuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Shaders shaders = main.getShaders();
                DialogSource dialog = new DialogSource(shaders.getVertexShaderAbsoluteFilePath(), shaders.getShader().getVertexShaderSource());
                main.getStage().addActor(dialog.fadeIn());
            }
        });
        return menuItem;
    }

    private MenuItem createFragmentShaderSourceMenuItem(Main main) {
        MenuItem menuItem = new MenuItem("Fragment Shader Source");
        menuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Shaders shaders = main.getShaders();
                DialogSource dialog = new DialogSource(shaders.getFragmentShaderAbsoluteFilePath(), shaders.getShader().getFragmentShaderSource());
                main.getStage().addActor(dialog.fadeIn());
            }
        });
        return menuItem;
    }
}
