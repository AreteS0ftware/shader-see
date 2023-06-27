package it.aretesoftware.shadersee.shaderproperties.frag;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.shader.ShaderLoadEvent;
import it.aretesoftware.shadersee.shaderproperties.FileLocation;

public class FragmentProperties extends Table {

    private final Main main;
    private final FileLocation fileLocation;
    private final Table contentTable;

    public FragmentProperties(Main main) {
        super();
        this.main = main;
        fileLocation = createFileLocation();
        contentTable = new Table();
    }

    //

    private void rebuild(String fragmentShaderSource) {
        Pattern pattern = Pattern.compile("(uniform|attribute|varying) +([A-Za-z0-9]+) +([A-Za-z0-9_]+);");
        Matcher matcher = pattern.matcher(fragmentShaderSource);
        System.out.println("FRAGMENT");
        while (matcher.find()) {
            String qualifier = matcher.group(1);
            String type = matcher.group(2);
            String name = matcher.group(3);
            System.out.println(qualifier + " " + type + " " + name + ";");
        }

        contentTable.clear();
        contentTable.defaults().reset();
        contentTable.defaults().padLeft(20).padRight(10);
        contentTable.add(fileLocation).padTop(15).growX();
        //for (int i = 1; i < controls.length; i++) {
            contentTable.row();
            //contentTable.add(controls[i]).padTop(35).growX();
        //}

        VisLabel titleLabel = new VisLabel("Frag Properties", "title");
        VisScrollPane scrollPane = new VisScrollPane(contentTable);
        add(titleLabel).top();
        row();
        add(scrollPane).expand().fillX().top();
    }

    private FileLocation createFileLocation() {
        FileLocation fileLocation = new FileLocation("Fragment: ");
        main.addListener(new EventListener<ShaderLoadEvent>(ShaderLoadEvent.class, this) {
            @Override
            protected void fire(ShaderLoadEvent event) {
                fileLocation.setFilePath(event.frag.path());
                rebuild(event.shader.getFragmentShaderSource());
            }
        });
        return fileLocation;
    }
}
