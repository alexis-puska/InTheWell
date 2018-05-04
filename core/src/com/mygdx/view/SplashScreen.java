package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

public class SplashScreen implements Screen {

	final InTheWellGame game;

	public SplashScreen(final InTheWellGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			game.getScreen().dispose();
			game.setScreen(new SelectionLangScreen(game));
		}
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		TextureRegion textureRegionTitle = SpriteService.getInstance().getTexture("menu_title", 0);
		game.getBatch().begin();
		DrawUtils.fillBackground(game.getBatch(), "menu_background_1");
		game.getBatch().draw(textureRegionTitle, (420 / 2) - (textureRegionTitle.getRegionWidth() / 2),
				DrawUtils.invert((520 / 2) - textureRegionTitle.getRegionHeight(), textureRegionTitle));
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
	}

}
