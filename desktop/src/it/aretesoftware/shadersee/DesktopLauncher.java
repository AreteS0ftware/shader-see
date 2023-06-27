package it.aretesoftware.shadersee;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import it.aretesoftware.shadersee.Main;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("shader-see");
		config.setForegroundFPS(60);
		config.setWindowSizeLimits(800, 800, 1920, 1080);
		config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 8);
		new Lwjgl3Application(new Main(), config);
	}
}
