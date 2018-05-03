package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.InTheWellGame;

public class SelectionLangScreen implements Screen {

	final InTheWellGame game;
	OrthographicCamera camera;

	public Texture img;

	public SelectionLangScreen(final InTheWellGame game) {
		this.game = game;
		this.img = new Texture(Gdx.files.internal("sprite/sprite_menu.png"));
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Keys.A)) {
			Gdx.app.log("selectionLangScreen", "A PRESSED !!!");	
		}
		Gdx.app.log("selectionLangScreen", "delta : " + Gdx.graphics.getFramesPerSecond());
		Gdx.app.log("SelectionLangScreen", "draw");
		Gdx.gl.glClearColor(0.2f, 0.2f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(img, 0, 0);
		game.batch.end();
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

	}

}
