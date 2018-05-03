package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.InTheWellGame;

public class SelectionLangScreen implements Screen {

	final InTheWellGame game;

	public Texture img;

	public SelectionLangScreen(final InTheWellGame game) {
		this.game = game;
		this.img = new Texture(Gdx.files.internal("sprite/sprite_menu.png"));
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			game.getScreen().dispose();
			game.setScreen(new SelectAccountScreen(game));
		}
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().begin();
		game.getBatch().draw(img, 0, 0);
		game.getBatch().end();
	}

	@Override
	public void show() {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		img.dispose();
	}

}
