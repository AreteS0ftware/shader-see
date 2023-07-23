package it.aretesoftware.shadersee.menu;

import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;

import it.aretesoftware.shadersee.Main;

public abstract class StatusSubMenu extends PopupMenu {

    private final MenuItem uniformsMenuItem;
    private final MenuItem attributesMenuItem;
    private final MenuItem varyingMenuItem;
    private boolean uniforms, attributes, varying;

    StatusSubMenu(Main main) {
        uniformsMenuItem = createUniformsMenuItem(main);
        attributesMenuItem = createAttributesMenuItem(main);
        varyingMenuItem = createVaryingMenuItem(main);
    }

    //

    void addUniformsMenuItem() {
        uniforms = true;
    }

    void addAttributesMenuItem() {
        attributes = true;
    }

    void addVaryingMenuItem() {
        varying = true;
    }

    void removeUniformsMenuItem() {
        uniforms = false;
    }

    void removeAttributesMenuItem() {
        attributes = false;
    }

    void removeVaryingMenuItem() {
        varying = false;
    }

    boolean isEmpty() {
        return !uniforms && !attributes && !varying;
    }

    void rebuild() {
        clear();
        if (uniforms) addItem(uniformsMenuItem);
        if (attributes) addItem(attributesMenuItem);
        if (varying) addItem(varyingMenuItem);
    }

    //

    protected abstract MenuItem createUniformsMenuItem(Main main);

    protected abstract MenuItem createAttributesMenuItem(Main main);

    protected abstract MenuItem createVaryingMenuItem(Main main);

}
