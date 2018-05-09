package com.mygdx.game.desktop;

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
		config.foregroundFPS = 60;
		config.vSyncEnabled = false;
		config.fullscreen = false;
		config.resizable = false;
		new LwjglApplication(new InTheWellGame(), config);
	}
}
