package it.aretesoftware.shadersee.menu;

import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;

import it.aretesoftware.shadersee.Main;

public class TopMenuBar extends MenuBar {

    public TopMenuBar(Main main) {
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        addMenu(file);
        addMenu(edit);
        addMenu(new ViewMenu(main));
        addMenu(help);
    }
}
