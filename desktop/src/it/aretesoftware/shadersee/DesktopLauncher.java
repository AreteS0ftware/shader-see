package it.aretesoftware.shadersee;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Shader See");
		config.setForegroundFPS(60);
		config.setWindowSizeLimits(800, 800, 1920, 1080);
		config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 8);

		config.setWindowIcon();
		config.setWindowIcon(Files.FileType.Internal,
				"icons/icon-16.png",
				"icons/icon-24.png",
				"icons/icon-32.png",
				"icons/icon-48.png",
				"icons/icon-128.png",
				"icons/icon-256.png",
				"icons/icon-512.png");
		new Lwjgl3Application(new Main(), config);
	}
}
