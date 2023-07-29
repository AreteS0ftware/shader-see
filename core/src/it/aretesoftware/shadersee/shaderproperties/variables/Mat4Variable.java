package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.dialog.DialogEditMat4;

public class Mat4Variable extends Variable {

    Mat4Variable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        final Main main = getMain();
        final Camera camera = isUProjTrans() ? main.getPreview().getViewport().getCamera() : new OrthographicCamera();

        VisTextButton editButton = new VisTextButton("Edit");
        editButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                DialogEditMat4 dialog = new DialogEditMat4(getMain(), camera, getVariableName());
                main.getStage().addActor(dialog.fadeIn());
            }
        });

        defaults().space(7);
        add(new VisLabel(getVariableName()));
        add(editButton).growX();
    }

    private boolean isUProjTrans() {
        return getVariableName().equals("u_projTrans");
    }

}
