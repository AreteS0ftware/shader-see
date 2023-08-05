package it.aretesoftware.shadersee.menu;

import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;

import it.aretesoftware.shadersee.Main;

public class TopMenuBar extends MenuBar {

    public TopMenuBar(Main main) {
        addMenu(new FileMenu(main));
        addMenu(new ViewMenu(main));
        addMenu(new HelpMenu(main));
    }
}
