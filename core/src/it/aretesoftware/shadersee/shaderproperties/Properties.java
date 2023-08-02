package it.aretesoftware.shadersee.shaderproperties;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.damios.guacamole.tuple.Pair;
import it.aretesoftware.couscous.ArrayObjectMap;
import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.shaderproperties.variables.Variable;
import it.aretesoftware.shadersee.shaderproperties.variables.VariableBuilder;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;

public abstract class Properties extends Table {

    private final Main main;
    private final FileLocation fileLocation;
    private ArrayObjectMap<ShaderVariableQualifier, Variable<?>> variables;
    private Array<Pair<ShaderVariableQualifier, Table>> tables;
    private boolean showUniforms, showAttributes, showVarying;

    Properties(Main main) {
        this.main = main;
        this.fileLocation = createFileLocation();
        variables = new ArrayObjectMap<>();
        tables = new Array<>();
        showUniforms = showAttributes = showVarying = true;
        populate(getInitialShaderSource());
    }

    //

    protected void populate(String shaderSource) {
        for (Array<Variable<?>> variables : variables.values()) {
            for (Variable<?> variable : variables) {
                variable.dispose(); // clear event manager references
            }
        }
        variables = createVariables(shaderSource);
        tables = createTables(variables);
        rebuild();
    }

    protected void rebuild() {
        Table contentTable = new Table();
        contentTable.clear();
        contentTable.defaults().reset();
        contentTable.defaults().padLeft(20).padRight(10);
        contentTable.add(fileLocation).padTop(15).growX();
        contentTable.row();
        contentTable.defaults().padTop(45).growX();
        for (Pair<ShaderVariableQualifier, Table> pair : tables) {
            ShaderVariableQualifier qualifier = pair.x;
            Table table = pair.y;
            switch (qualifier) {
                case uniform:
                    if (!showUniforms) continue;
                    break;
                case attribute:
                    if (!showAttributes) continue;
                    break;
                case varying:
                    if (!showVarying) continue;
                    break;
            }
            contentTable.row();
            contentTable.add(table);
        }
        contentTable.padBottom(15);

        VisLabel titleLabel = new VisLabel(getTitle(), "title");
        VisScrollPane scrollPane = new VisScrollPane(contentTable);
        clear();
        add(titleLabel).top();
        row();
        add(scrollPane).expand().fillX().top();
    }

    private Array<Pair<ShaderVariableQualifier, Table>> createTables(ArrayObjectMap<ShaderVariableQualifier, Variable<?>> map) {
        Array<Pair<ShaderVariableQualifier, Table>> tables = new Array<>(3);
        for (ObjectMap.Entry<ShaderVariableQualifier, Array<Variable<?>>> entry : map.entries()) {
            ShaderVariableQualifier qualifier = entry.key;
            Array<Variable<?>> variables = entry.value;
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
            table.defaults().growX().padTop(25);
            for (Variable<?> variable : variables) {
                table.add(variable);
                table.row();
            }
            tables.add(new Pair<>(qualifier, table));
        }
        return tables;
    }

    private ArrayObjectMap<ShaderVariableQualifier, Variable<?>> createVariables(String shaderSource) {
        ArrayObjectMap<ShaderVariableQualifier, Variable<?>> map = new ArrayObjectMap<>();
        Pattern pattern = Pattern.compile("(uniform|attribute|varying) +(?i)(lowp|mediump|highp +)? *([A-Za-z0-9]+) +([A-Za-z0-9_]+);");
        Matcher matcher = pattern.matcher(shaderSource);
        while (matcher.find()) {
            String qualifier = matcher.group(1);
            String precision = matcher.group(2);
            String type = matcher.group(3);
            String name = matcher.group(4);
            VariableBuilder builder = new VariableBuilder(main, qualifier, precision, type, name);
            Variable<?> variable = Variable.create(builder);
            map.add(variable.getVariableQualifier(), variable);
        }
        return map;
    }

    protected abstract FileLocation createFileLocation();

    protected abstract String getInitialShaderSource();

    protected abstract String getTitle();

    protected Main getMain() {
        return main;
    }

    protected FileLocation getFileLocation() {
        return fileLocation;
    }

    void setShowUniforms(boolean showUniforms) {
        this.showUniforms = showUniforms;
    }

    void setShowAttributes(boolean showAttributes) {
        this.showAttributes = showAttributes;
    }

    void setShowVarying(boolean showVarying) {
        this.showVarying = showVarying;
    }

}
