package com.mygdx.game.raspberry;

import com.badlogic.gdx.backends.jogamp.JoglNewtApplication;
import com.badlogic.gdx.backends.jogamp.JoglNewtApplicationConfiguration;
import com.mygdx.game.InTheWellGame;

public class RaspberryLauncher {
	public static void main(String[] arg) {
		JoglNewtApplicationConfiguration config = new JoglNewtApplicationConfiguration();
		config.title = "Drop";
		config.width = 420;
		config.height = 520;
		config.fullscreen = true;
		new JoglNewtApplication(new InTheWellGame(), config);
	}
}
