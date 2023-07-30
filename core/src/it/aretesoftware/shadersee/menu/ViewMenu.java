package it.aretesoftware.shadersee.menu;

import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shaderproperties.HideAttributesEvent;
import it.aretesoftware.shadersee.event.shaderproperties.HideUniformsEvent;
import it.aretesoftware.shadersee.event.shaderproperties.HideVaryingEvent;
import it.aretesoftware.shadersee.event.shaderproperties.ShowAttributesEvent;
import it.aretesoftware.shadersee.event.shaderproperties.ShowUniformsEvent;
import it.aretesoftware.shadersee.event.shaderproperties.ShowVaryingEvent;

public class ViewMenu extends Menu {

    private final Main main;
    private final MenuItem showMenuItem, hideMenuItem;
    private final ShowSubMenu showSubMenu;
    private final HideSubMenu hideSubMenu;

    public ViewMenu(Main main) {
        super("View");
        this.main = main;

        showMenuItem = new MenuItem("Show...");
        showMenuItem.setSubMenu(showSubMenu = new ShowSubMenu(main));

        hideMenuItem = new MenuItem("Hide...");
        hideMenuItem.setSubMenu(hideSubMenu = new HideSubMenu(main));
        updateShowAndHideMenus();

        MenuItem sourceMenuItem = new MenuItem("Source...");
        sourceMenuItem.setSubMenu(new SourceSubMenu(main));

        addItem(showMenuItem);
        addItem(hideMenuItem);
        addItem(sourceMenuItem);
        addListeners();
    }

    private void addListeners() {
        // Show
        main.addListener(new EventListener<ShowUniformsEvent>(ShowUniformsEvent.class, this) {
            @Override
            protected void fire(ShowUniformsEvent event) {
                showSubMenu.removeUniformsMenuItem();
                hideSubMenu.addUniformsMenuItem();
                updateShowAndHideMenus();
            }
        });
        main.addListener(new EventListener<ShowAttributesEvent>(ShowAttributesEvent.class, this) {
            @Override
            protected void fire(ShowAttributesEvent event) {
                showSubMenu.removeAttributesMenuItem();
                hideSubMenu.addAttributesMenuItem();
                updateShowAndHideMenus();
            }
        });
        main.addListener(new EventListener<ShowVaryingEvent>(ShowVaryingEvent.class, this) {
            @Override
            protected void fire(ShowVaryingEvent event) {
                showSubMenu.removeVaryingMenuItem();
                hideSubMenu.addVaryingMenuItem();
                updateShowAndHideMenus();
            }
        });

        // Hide
        main.addListener(new EventListener<HideUniformsEvent>(HideUniformsEvent.class, this) {
            @Override
            protected void fire(HideUniformsEvent event) {
                showSubMenu.addUniformsMenuItem();
                hideSubMenu.removeUniformsMenuItem();
                updateShowAndHideMenus();
            }
        });
        main.addListener(new EventListener<HideAttributesEvent>(HideAttributesEvent.class, this) {
            @Override
            protected void fire(HideAttributesEvent event) {
                showSubMenu.addAttributesMenuItem();
                hideSubMenu.removeAttributesMenuItem();
                updateShowAndHideMenus();
            }
        });
        main.addListener(new EventListener<HideVaryingEvent>(HideVaryingEvent.class, this) {
            @Override
            protected void fire(HideVaryingEvent event) {
                showSubMenu.addVaryingMenuItem();
                hideSubMenu.removeVaryingMenuItem();
                updateShowAndHideMenus();
            }
        });
    }

    private void updateShowAndHideMenus() {
        showSubMenu.rebuild();
        hideSubMenu.rebuild();
        if (showSubMenu.isEmpty()) {
            showMenuItem.setSubMenu(null);
        }
        else {
            showMenuItem.setSubMenu(showSubMenu);
        }
        if (hideSubMenu.isEmpty()) {
            hideMenuItem.setSubMenu(null);
        }
        else {
            hideMenuItem.setSubMenu(hideSubMenu);
        }
    }

}
