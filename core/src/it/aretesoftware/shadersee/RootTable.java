package it.aretesoftware.shadersee;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisSplitPane;
import com.kotcrab.vis.ui.widget.VisTable;

public class RootTable extends VisTable {

    public RootTable() {
        super();
    }

    public void populate() {
        setFillParent(true);

        clearChildren();
        addFileMenu();

        row();
        addSeparator().colspan(2);

        row();
        addSplitPane();
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

    private void addSplitPane() {
        Table table = new Table();
        add(table).colspan(2).grow();

        VisSplitPane splitPane = new VisSplitPane(createLeftSideOfPane(), createRightSideOfPane(), false);
        splitPane.setMinSplitAmount(0.40f);
        splitPane.setMaxSplitAmount(0.60f);
        table.add(splitPane).grow();
    }

    private Table createLeftSideOfPane() {
        Table contentTable = new Table();
        contentTable.defaults().padLeft(20).padRight(10);
        contentTable.add(new VisLabel("TEST1")).padTop(15).growX();
        contentTable.row();
        contentTable.add(new VisLabel("TEST2")).padTop(35).growX();

        VisLabel titleLabel = new VisLabel("Shader Properties", "title");
        VisScrollPane scrollPane = new VisScrollPane(contentTable);

        Table table = new Table();
        table.add(titleLabel).top();
        table.row();
        table.add(scrollPane).expand().fillX().top();
        return table;
    }

    private Table createRightSideOfPane() {
        VisSplitPane splitPane = new VisSplitPane(new Table(), new Table(), true);
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
