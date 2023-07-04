package it.aretesoftware.shadersee;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;

import it.aretesoftware.shadersee.preview.Preview;
import it.aretesoftware.shadersee.previewproperties.PreviewProperties;
import it.aretesoftware.shadersee.shaderproperties.FragmentProperties;
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
    }

    //

    private void addFileMenu() {
        Table table = new Table();
        table.defaults().padRight(2.0f);
        add(table).padTop(2.0f).growX();

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu project = new Menu("Project");
        Menu help = new Menu("Help");
        menuBar.addMenu(file);
        menuBar.addMenu(edit);
        menuBar.addMenu(project);
        menuBar.addMenu(help);

        table.add(menuBar.getTable());
        table.add(new VisLabel("Shader See", "title")).expandX().center();
    }

    private void addSplitPane(Preview preview) {
        Table table = new Table();
        add(table).colspan(2).grow();

        VisSplitPane splitPane = new VisSplitPane(createLeftSideOfPane(), createRightSideOfPane(preview), false);
        splitPane.setMinSplitAmount(0.40f);
        splitPane.setMaxSplitAmount(0.60f);
        table.add(splitPane).grow();
    }

    private VisSplitPane createLeftSideOfPane() {
        VertexProperties vertexProperties = new VertexProperties(main);
        FragmentProperties fragmentProperties = new FragmentProperties(main);
        VisSplitPane splitPane = new VisSplitPane(vertexProperties, fragmentProperties, true);
        splitPane.setMinSplitAmount(0.05f);
        splitPane.setMaxSplitAmount(0.95f);
        return splitPane;
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

}
