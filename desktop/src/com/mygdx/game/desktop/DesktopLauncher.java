package com.mygdx.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.constante.Constante;
import com.mygdx.game.InTheWellGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "In The Well";
		config.width = Constante.SCREEN_SIZE_X;
		config.height = Constante.SCREEN_SIZE_Y;
		config.foregroundFPS = 40;
		config.backgroundFPS = 40;
		config.vSyncEnabled = false;
		config.fullscreen = false;
		config.resizable = true;
		config.x = 100;
		config.y = 100;
		config.addIcon("icon_128.png", FileType.Internal);
		config.addIcon("icon_32.png", FileType.Internal);
		config.addIcon("icon_16.png", FileType.Internal);
		new LwjglApplication(new InTheWellGame(), config);
	}
}
