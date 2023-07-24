package it.aretesoftware.shadersee.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.dialog.DialogAbout;

public class HelpMenu extends Menu {

    HelpMenu(Main main) {
        super("Help");

        MenuItem aboutMenuItem = new MenuItem("About...");
        aboutMenuItem.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DialogAbout dialog = new DialogAbout();
                main.getStage().addActor(dialog.fadeIn());
            }
        });
        addItem(aboutMenuItem);
    }
}
