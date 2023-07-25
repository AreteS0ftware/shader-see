package it.aretesoftware.shadersee;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import it.aretesoftware.shadersee.event.Event;
import it.aretesoftware.shadersee.event.EventCallback;
import it.aretesoftware.shadersee.event.EventListener;
import it.aretesoftware.shadersee.event.EventManager;
import it.aretesoftware.shadersee.preview.Preview;

public class Main extends Game {

	private EventManager eventManager;
	private Assets assets;
	private Stage stage;
	private Shaders shaders;
	private Data data;
	private Preview preview;

	public Main() {
		super();
	}
	
	@Override
	public void create () {
		eventManager = new EventManager();
		assets = new Assets();

		stage = new Stage(new ScreenViewport(), new SpriteBatch());
		//stage.setDebugAll(true);
		Gdx.input.setInputProcessor(stage);

		data = new Data(this);

		shaders = new Shaders(this);
		shaders.loadDefaultShader();

		RootTable rootTable = new RootTable(this);
		rootTable.populate(preview = new Preview(this));
		stage.addActor(rootTable);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		stage.act();
		preview.draw(stage.getBatch(), this);
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

	//

	public Assets getAssets() {
		return assets;
	}

	public Shaders getShaders() {
		return shaders;
	}

	public Preview getPreview() {
		return preview;
	}

	public Data getData() {
		return data;
	}

	public Stage getStage() {
		return stage;
	}

}
