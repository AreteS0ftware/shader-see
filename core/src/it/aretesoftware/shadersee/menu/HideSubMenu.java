package it.aretesoftware.shadersee.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.MenuItem;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shaderproperties.HideAttributesEvent;
import it.aretesoftware.shadersee.event.shaderproperties.HideUniformsEvent;
import it.aretesoftware.shadersee.event.shaderproperties.HideVaryingEvent;

public class HideSubMenu extends StatusSubMenu {

    HideSubMenu(Main main) {
        super(main);
        addUniformsMenuItem();
        addAttributesMenuItem();
        addVaryingMenuItem();
    }

    //

    @Override
    protected MenuItem createUniformsMenuItem(Main main) {
        MenuItem menuItem = new MenuItem("Hide Uniforms");
        menuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.fire(new HideUniformsEvent());
            }
        });
        return menuItem;
    }

    @Override
    protected MenuItem createAttributesMenuItem(Main main) {
        MenuItem menuItem = new MenuItem("Hide Attributes");
        menuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.fire(new HideAttributesEvent());
            }
        });
        return menuItem;
    }

    @Override
    protected MenuItem createVaryingMenuItem(Main main) {
        MenuItem menuItem = new MenuItem("Hide Varying");
        menuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.fire(new HideVaryingEvent());
            }
        });
        return menuItem;
    }

}
