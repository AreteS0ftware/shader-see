package it.aretesoftware.shadersee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.preview.BackgroundColorChangeEvent;
import it.aretesoftware.shadersee.event.shader.ShaderProgramUpdateEvent;

public class Data {

    private final Main main;
    private final Color backgroundColor;

    Data(Main main) {
        this.main = main;
        this.backgroundColor = Color.CLEAR.cpy();
        addListeners();
    }

    private void addListeners() {
        main.addPreListener(new EventListener<ShaderProgramUpdateEvent>(ShaderProgramUpdateEvent.class, this) {
            @Override
            protected void fire(ShaderProgramUpdateEvent event) {
                String vert = event.vert.name();
                String frag = event.frag.name();
                Gdx.graphics.setTitle("Shader See - " + vert + " & " + frag);
            }
        });
        main.addPreListener(new EventListener<BackgroundColorChangeEvent>(BackgroundColorChangeEvent.class, this) {
            @Override
            protected void fire(BackgroundColorChangeEvent event) {
                backgroundColor.set(event.newColor);
            }
        });
    }


    public Color getBackgroundColor() {
        return backgroundColor;
    }

}
