package it.aretesoftware.shadersee.shaderproperties;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.aretesoftware.couscous.ArrayObjectMap;
import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.shaderproperties.variables.Variable;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public abstract class Properties extends Table {

    private final Main main;
    private final FileLocation fileLocation;
    private final ObjectMap<ShaderVariableQualifier, Array<Variable>> qualifierToVariables;

    Properties(Main main) {
        this.main = main;
        this.fileLocation = createFileLocation();
        qualifierToVariables = new ObjectMap<>();
    }

    //

    protected void rebuild(String shaderSource) {
        ArrayObjectMap<ShaderVariableQualifier, Variable> map = createVariables(shaderSource);
        Array<Table> tables = createTables(map);

        Table contentTable = new Table();
        contentTable.clear();
        contentTable.defaults().reset();
        contentTable.defaults().padLeft(20).padRight(10);
        contentTable.add(fileLocation).padTop(15).growX();
        contentTable.row();
        contentTable.defaults().padTop(35).growX();
        for (Table table : tables) {
            contentTable.row();
            contentTable.add(table);
        }
        contentTable.padBottom(15);

        VisLabel titleLabel = new VisLabel(getTitle(), "title");
        VisScrollPane scrollPane = new VisScrollPane(contentTable);
        add(titleLabel).top();
        row();
        add(scrollPane).expand().fillX().top();
    }

    private Array<Table> createTables(ArrayObjectMap<ShaderVariableQualifier, Variable> map) {
        Array<Table> tables = new Array<>(3);
        for (ObjectMap.Entry<ShaderVariableQualifier, Array<Variable>> entry : map.entries()) {
            ShaderVariableQualifier qualifier = entry.key;
            Array<Variable> variables = entry.value;
            String title = null;
            switch (qualifier) {
                case uniform:
                    title = "Uniforms";
                    break;
                case attribute:
                    title = "Attributes";
                    break;
                case varying:
                    title = "Varying";
                    break;
            }
            Table table = new Table();
            table.add(new VisLabel(title));
            table.row();
            table.defaults().growX().padTop(15);
            for (Variable variable : variables) {
                table.add(variable);
                table.row();
            }
            tables.add(table);
        }
        return tables;
    }

    private ArrayObjectMap<ShaderVariableQualifier, Variable> createVariables(String shaderSource) {
        ArrayObjectMap<ShaderVariableQualifier, Variable> map = new ArrayObjectMap<>();
        Pattern pattern = Pattern.compile("(uniform|attribute|varying) +([A-Za-z0-9]+) +([A-Za-z0-9_]+);");
        Matcher matcher = pattern.matcher(shaderSource);
        while (matcher.find()) {
            String qualifier = matcher.group(1);
            String type = matcher.group(2);
            String name = matcher.group(3);
            Variable variable = isVertex() ? Variable.createVertexVariable(getMain(), qualifier, type, name) : Variable.createFragmentVariable(getMain(), qualifier, type, name);
            map.add(variable.getVariableQualifier(), variable);
        }
        return map;
    }

    protected abstract FileLocation createFileLocation();

    protected abstract String getTitle();

    protected abstract boolean isVertex();

    protected Main getMain() {
        return main;
    }

}
