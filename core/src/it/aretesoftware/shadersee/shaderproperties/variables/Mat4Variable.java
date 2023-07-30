package it.aretesoftware.shadersee.shaderproperties.variables;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.dialog.DialogEditMat4;
import it.aretesoftware.shadersee.event.shader.SetMat4UniformEvent;

public class Mat4Variable extends Variable<Matrix4> {

    Mat4Variable(VariableBuilder builder) {
        super(builder);
    }

    @Override
    protected void populate() {
        final Main main = getMain();
        final Camera originalCamera = main.getPreview().getViewport().getCamera();
        final Camera camera = isUProjTrans() ? originalCamera : new OrthographicCamera();
        camera.position.set(originalCamera.position);
        camera.up.set(originalCamera.up);
        camera.direction.set(originalCamera.direction);
        camera.view.set(originalCamera.view);
        camera.invProjectionView.set(originalCamera.invProjectionView);
        camera.projection.set(originalCamera.projection);
        camera.combined.set(originalCamera.combined);
        camera.viewportWidth = originalCamera.viewportWidth;
        camera.viewportHeight = originalCamera.viewportHeight;
        camera.far = originalCamera.far;
        camera.near = originalCamera.near;
        camera.update();
        setUniform(camera.combined);

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

    @Override
    protected void setUniform(Matrix4 value) {
        if (value != null) {
            getMain().fire(new SetMat4UniformEvent(getVariableName(), value));
        }
    }

    private boolean isUProjTrans() {
        return getVariableName().equals("u_projTrans");
    }

}
