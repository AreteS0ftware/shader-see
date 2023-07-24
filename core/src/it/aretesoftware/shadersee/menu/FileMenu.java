package it.aretesoftware.shadersee.menu;

import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;

import it.aretesoftware.shadersee.Main;

public class FileMenu extends Menu {

    public FileMenu(Main main) {
        super("File");

        MenuItem importMenuItem = new MenuItem("Import...");
        importMenuItem.setSubMenu(new ImportSubMenu(main));

        MenuItem exportMenuItem = new MenuItem("Export...");
        exportMenuItem.setSubMenu(new ExportSubMenu(main));

        addItem(importMenuItem);
        addItem(exportMenuItem);
    }
}
