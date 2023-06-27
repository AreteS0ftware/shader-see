package it.aretesoftware.shadersee;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;

import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.EventCallback;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.EventManager;

public class Main extends Game {

	private Stage stage;
	private EventManager eventManager;
	private Shaders shaders;

	public Main() {
		super();
	}
	
	@Override
	public void create () {
		eventManager = new EventManager();

		Skin skin;
		VisUI.load(skin = new Skin(Gdx.files.internal("neutralizer/neutralizer-ui.json")));
		Gdx.input.setInputProcessor(stage = new Stage(new ScreenViewport(), new SpriteBatch()));
		stage.setDebugAll(true);

		RootTable rootTable = new RootTable(this);
		rootTable.populate();
		stage.addActor(rootTable);

		shaders = new Shaders(this);
		shaders.loadDefaultShader();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		stage.act();
		stage.getViewport().apply();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public Stage getStage() {
		return stage;
	}

	//

	public void fire(Event event) {
		eventManager.fire(event);
	}

	public void fire(Event event, EventCallback<?> callback) {
		eventManager.fire(event, callback);
	}

	public void addPreListener(EventListener<? extends Event> listener) {
		eventManager.addPreListener(listener);
	}

	public void addListener(EventListener<? extends Event> listener) {
		eventManager.addListener(listener);
	}

	public void removeListenersOfBind(Object bind) {
		eventManager.removeListenersOfBind(bind, true);
	}

}
