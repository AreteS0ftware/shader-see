package it.aretesoftware.shadersee.previewproperties;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

import it.aretesoftware.couscous.Numbers;
import it.aretesoftware.shadersee.Main;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.preview.AddCameraZoomEvent;
import it.aretesoftware.shadersee.event.preview.CameraResetEvent;
import it.aretesoftware.shadersee.event.preview.CameraZoomChangeEvent;

public class CameraControls extends Table {

    CameraControls(Main main) {
        VisLabel header = new VisLabel("Camera");
        VisLabel zoom = createZoomLabel(main);
        VisTextButton zoomOut = createZoomOutButton(main);
        VisTextButton zoomIn = createZoomInButton(main);
        VisTextButton resetCamera = createResetCameraButton(main);

        clear();
        add(header).expandX().center().colspan(4);
        row();
        defaults().space(10);
        //add(new VisLabel("Zoom: "));
        add(zoomOut).right();
        Table table = new Table();
        table.add(zoom);
        add(table).width(50);
        add(zoomIn).left();
        add(resetCamera).growX();
    }

    //

    private VisLabel createZoomLabel(final Main main) {
        final VisLabel label = new VisLabel("1.0");
        main.addListener(new EventListener<CameraZoomChangeEvent>(CameraZoomChangeEvent.class, this) {
            @Override
            protected void fire(CameraZoomChangeEvent event) {
                float roundedDecimalsZoomValue = Numbers.roundOffTo2DecPlaces(event.zoom);
                label.setText(String.valueOf(roundedDecimalsZoomValue));
            }
        });
        return label;
    }

    private VisTextButton createZoomInButton(final Main main) {
        VisTextButton zoomIn = new VisTextButton("+");
        zoomIn.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                main.fire(new AddCameraZoomEvent(-0.25f));
            }
        });
        return zoomIn;
    }

    private VisTextButton createZoomOutButton(final Main main) {
        VisTextButton zoomOut = new VisTextButton("-");
        zoomOut.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                main.fire(new AddCameraZoomEvent(+0.25f));
            }
        });
        return zoomOut;
    }

    private VisTextButton createResetCameraButton(final Main main) {
        VisTextButton centerCameraButton = new VisTextButton("Reset camera");
        centerCameraButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                main.fire(new CameraResetEvent());
            }
        });
        return centerCameraButton;
    }

}
