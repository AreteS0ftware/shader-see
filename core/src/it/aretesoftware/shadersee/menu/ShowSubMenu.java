package it.aretesoftware.shadersee.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.MenuItem;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shaderproperties.ShowAttributesEvent;
import it.aretesoftware.shadersee.event.shaderproperties.ShowUniformsEvent;
import it.aretesoftware.shadersee.event.shaderproperties.ShowVaryingEvent;

public class ShowSubMenu extends StatusSubMenu {

    ShowSubMenu(Main main) {
        super(main);
        removeUniformsMenuItem();
        removeAttributesMenuItem();
        removeVaryingMenuItem();
    }

    //

    @Override
    protected MenuItem createUniformsMenuItem(Main main) {
        MenuItem menuItem = new MenuItem("Show Uniforms");
        menuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.fire(new ShowUniformsEvent());
            }
        });
        return menuItem;
    }

    @Override
    protected MenuItem createAttributesMenuItem(Main main) {
        MenuItem menuItem = new MenuItem("Show Attributes");
        menuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.fire(new ShowAttributesEvent());
            }
        });
        return menuItem;
    }

    @Override
    protected MenuItem createVaryingMenuItem(Main main) {
        MenuItem menuItem = new MenuItem("Show Varying");
        menuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.fire(new ShowVaryingEvent());
            }
        });
        return menuItem;
    }

}
