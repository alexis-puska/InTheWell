package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.service.SoundService;

public class InTheWellGame extends Game {

	SpriteBatch batch;
	Texture img;

	public void create() {
		Gdx.app.log("tag", "create");
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		SoundService.getInstance().playMusic();
	}

	public void render() {
		Gdx.app.log("tag", "render");
		super.render();
		Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	public void dispose() {
		Gdx.app.log("tag", "dispose");
		batch.dispose();
		img.dispose();
	}
}
