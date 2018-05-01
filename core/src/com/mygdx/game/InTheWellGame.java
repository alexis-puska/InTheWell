package com.mygdx.game;

import org.apache.log4j.Logger;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.service.Sound;

public class InTheWellGame extends Game {

	private final static Logger LOG = Logger.getLogger(InTheWellGame.class);

	SpriteBatch batch;
	Texture img;

	public void create() {
		LOG.info("create");
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		Sound.getINSTANCE().playMusic();
	}

	public void render() {
		LOG.info("render");
		super.render();
		Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	public void dispose() {
		LOG.info("dispose");
		batch.dispose();
		img.dispose();
	}
}
