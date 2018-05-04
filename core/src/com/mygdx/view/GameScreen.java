package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.constante.Constante;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

public class GameScreen implements Screen {

	final InTheWellGame game;

	private FrameBuffer fb;
	private ShapeRenderer shapeRenderer;
	private int x;
	private int y;

	public GameScreen(final InTheWellGame game) {
		this.game = game;
		this.shapeRenderer = new ShapeRenderer();
		this.fb = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false); // test
		x = 210;
		y = 210;
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			game.getScreen().dispose();
			game.setScreen(new SelectAccountScreen(game));
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= 10;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += 10;
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			y += 10;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= 10;
		}

		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		TextureRegion textureRegionBackground = SpriteService.getInstance().getTexture("menu_background_1", 0);
		TextureRegion textureRegionTitle = SpriteService.getInstance().getTexture("menu_title", 0);

		initShadowMask(x, y);

		game.getBatch().begin();
		int idx = 0;
		while (idx < Constante.SCREEN_SIZE_X) {
			game.getBatch().draw(textureRegionBackground, idx, DrawUtils.invert(0, textureRegionBackground));
			idx += textureRegionBackground.getRegionWidth();
		}
		game.getBatch().draw(textureRegionTitle, (420 / 2) - (textureRegionTitle.getRegionWidth() / 2),
				DrawUtils.invert((520 / 2) - textureRegionTitle.getRegionHeight(), textureRegionTitle));
		drawShadow();
		game.getBatch().end();
	}

	private void initShadowMask(int x, int y) {
		fb.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glColorMask(false, false, false, true);

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0f, 0f, 0f, 0f));
		shapeRenderer.circle(x, y, 100);
		shapeRenderer.end();

		Gdx.gl.glColorMask(true, true, true, true);
		fb.end();
	}

	private void drawShadow() {
		Texture t = fb.getColorBufferTexture();
		t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion fboRegion = new TextureRegion(t);
		fboRegion.flip(false, true);
		game.getBatch().draw(fboRegion, 0, DrawUtils.invert(0, fb.getColorBufferTexture()));
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
		fb.dispose();
	}

}
