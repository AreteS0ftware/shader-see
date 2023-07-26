package it.aretesoftware.shadersee.dialog;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisDialog;

import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.shader.SetMat4UniformEvent;
import it.aretesoftware.shadersee.preview.previewfordialog.PreviewForDialog;

public class DialogEditMat4 extends VisDialog {

    private final String uniformName;
    private final Camera camera;
    private final Main main;
    private final PreviewForDialog dialogPreview;

    public DialogEditMat4(Main main, Camera camera, String uniformName) {
        super(uniformName);
        this.main = main;
        this.uniformName = uniformName;
        this.camera = camera;
        dialogPreview = main.getPreviewForDialog();
        dialogPreview.setCamera(camera);
        if (uniformName.equals("u_projTrans")) {
            dialogPreview.enableCameraEvents();
        }

        setKeepWithinStage(false);
        setCenterOnAdd(true);
        setResizable(true);
        setSize(600, 600);
        addCloseButton();

        Table table = getContentTable();
        table.add(dialogPreview).grow();
    }

    @Override
    public boolean remove() {
        boolean removed = super.remove();
        dialogPreview.remove();
        dialogPreview.resetCameraToDefault();
        dialogPreview.disableCameraEvents();
        main.fire(new SetMat4UniformEvent(uniformName, camera.combined));
        return removed;
    }

}
