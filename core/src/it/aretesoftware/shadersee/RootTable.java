package it.aretesoftware.shadersee;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;

import it.aretesoftware.shadersee.menu.TopMenuBar;
import it.aretesoftware.shadersee.preview.Preview;
import it.aretesoftware.shadersee.previewproperties.PreviewProperties;
import it.aretesoftware.shadersee.shaderproperties.FragmentProperties;
import it.aretesoftware.shadersee.shaderproperties.ShaderProperties;
import it.aretesoftware.shadersee.shaderproperties.VertexProperties;

public class RootTable extends VisTable {

    private final Main main;

    public RootTable(Main main) {
        super();
        this.main = main;
    }

    public void populate(Preview preview) {
        setFillParent(true);

        clearChildren();
        addFileMenu();

        row();
        addSeparator().colspan(2);

        row();
        addSplitPane(preview);

        row();
        addBottomBar();
    }

    //

    private void addFileMenu() {
        TopMenuBar menuBar = new TopMenuBar(main);

        Table table = new Table();
        table.defaults().padRight(2.0f);
        add(table).padTop(2.0f).growX();

        table.add(menuBar.getTable()).left();
        table.add(new VisLabel("Shader See", "title")).expandX().center();
    }

    private void addSplitPane(Preview preview) {
        Table table = new Table();
        add(table).colspan(2).grow();

        VisSplitPane splitPane = new VisSplitPane(createLeftSideOfPane(), createRightSideOfPane(preview), false);
        splitPane.setMinSplitAmount(0.45f);
        splitPane.setMaxSplitAmount(0.55f);
        table.add(splitPane).grow();
    }

    private VisSplitPane createLeftSideOfPane() {
        return new ShaderProperties(main);
    }

    private Table createRightSideOfPane(Preview preview) {
        PreviewProperties previewProperties = new PreviewProperties(main);
        VisSplitPane splitPane = new VisSplitPane(preview, previewProperties, true);
        splitPane.setMinSplitAmount(0.40f);
        splitPane.setMaxSplitAmount(0.60f);

        VisLabel previewLabel = new VisLabel("Preview", "title");
        Table table = new Table();
        table.add(previewLabel).top();
        table.row();
        table.add(splitPane).grow().top();

        return table;
    }

    private void addBottomBar() {
        HorizontalGroup group = new HorizontalGroup();
        group.space(20).padRight(30).right();
        group.addActor(new VisLabel("v0.1.0"));
        group.addActor(new VisLabel("ARETESOFTWARE.IT"));
        group.addActor(new VisLabel("© 2023 Arete"));

        MenuBar menuBar = new MenuBar();
        menuBar.getTable().add(group).growX();
        add(menuBar.getTable()).growX();
    }

}
