package com.porkopolis.crawler.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.porkopolis.crawler.Main;

public class DesktopLauncher {
	public static void main(String[] arg) {

		// if (Assets.rebuildAtlas) {
		Settings settings = new Settings();
		settings.maxWidth = 2048;
		settings.maxHeight = 2048;
		settings.debug = false;
		try {
			// TexturePacker.process(settings, "assets-raw",
			// "../android/assets",
			// "assets");
		} catch (Exception e) {

		}
		// }

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		config.disableAudio = true;
		// config.vSyncEnabled = LauncherConstants.VSYNC;
		new LwjglApplication(new Main(), config);
	}
}
