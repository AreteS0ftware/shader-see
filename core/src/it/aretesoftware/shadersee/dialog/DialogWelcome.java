package it.aretesoftware.shadersee.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.LoadShaderEvent;

public class DialogWelcome extends VisDialog {

    private final Main main;

    public DialogWelcome(Main main) {
        super("Welcome");
        this.main = main;

        setKeepWithinStage(true);
        setCenterOnAdd(true);
        setResizable(false);
        setMovable(false);
        setSize(300, 450);
        addCloseButton();

        VisTextButton testSamples = createLoadShaderTextButton("Samples", "shaders/default.vert", "shaders/test_samples.frag");
        VisTextButton testScalars = createLoadShaderTextButton("Scalars", "shaders/test_scalars.vert", "shaders/test_scalars.frag");
        VisTextButton testMatrices = createLoadShaderTextButton("Matrices", "shaders/test_matrices.vert", "shaders/test_matrices.frag");
        VisTextButton testVec2 = createLoadShaderTextButton("vec2", "shaders/default.vert", "shaders/test_vec2.frag");
        VisTextButton testVec3 = createLoadShaderTextButton("vec3", "shaders/default.vert", "shaders/test_vec3.frag");
        VisTextButton testVec4 = createLoadShaderTextButton("vec4", "shaders/default.vert", "shaders/test_vec4.frag");
        VisTextButton exampleGrayscale = createLoadShaderTextButton("Grayscale", "shaders/default.vert", "shaders/grayscale.frag");
        VisTextButton exampleCrt = createLoadShaderTextButton("CRT TV", "shaders/default.vert", "shaders/crt.frag");

        VisTable table = new VisTable();
        table.defaults().space(10).colspan(3);
        table.add(new VisLabel("Test Shaders"));
        table.row();
        table.add(testSamples);
        table.row();
        table.add(testScalars);
        table.row();
        table.add(testMatrices);
        table.row();
        table.defaults().colspan(1);
        table.add(testVec2);
        table.add(testVec3);
        table.add(testVec4);
        table.row();
        table.defaults().colspan(3);
        table.addSeparator().space(25);
        table.add(new VisLabel("Example Shaders"));
        table.row();
        table.add(exampleGrayscale);
        table.row();
        table.add(exampleCrt);
        table.row();

        getContentTable().add(table);
    }

    private VisTextButton createLoadShaderTextButton(String text, String internalVertFilePath, String internalFragFilePath) {
        VisTextButton textButton = new VisTextButton(text);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                FileHandle vert = Gdx.files.internal(internalVertFilePath);
                FileHandle frag = Gdx.files.internal(internalFragFilePath);
                main.fire(new LoadShaderEvent(vert, frag));
                close();
            }
        });
        return textButton;
    }


}
