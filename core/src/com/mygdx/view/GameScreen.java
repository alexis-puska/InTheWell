package com.mygdx.view;

import java.util.logging.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.constante.Constante;
import com.mygdx.enumeration.MusicEnum;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.Context;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

public class GameScreen implements Screen {

	final InTheWellGame game;

	//TEXT
	private GlyphLayout layout;
	private BitmapFont fontGold;
	
	//DRAW
	private FrameBuffer backgroundLayer;
	private FrameBuffer platformLayer;
	private FrameBuffer playerLayer;
	private FrameBuffer frontLayer;
	private FrameBuffer shadowLayer;
	private FrameBuffer finalLayer;
	private ShapeRenderer shapeRenderer;
	
	
	private Level currentLevel;
	private int x;
	private int y;

	public GameScreen(final InTheWellGame game) {
		this.game = game;
		this.shapeRenderer = new ShapeRenderer();
		this.layout = new GlyphLayout();
		this.backgroundLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.platformLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.playerLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.frontLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.shadowLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.finalLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.game.getSoundService().stopMusic();
		this.game.getSoundService().playMusic(MusicEnum.HAMMERFEST);
		x = 210;
		y = 210;
		this.initFont();
	}

	@Override
	public void dispose() {
		backgroundLayer.dispose();
		platformLayer.dispose();
		playerLayer.dispose();
		frontLayer.dispose();
		shadowLayer.dispose();
		finalLayer.dispose();
		fontGold.dispose();
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
	public void render(float delta) {
		treatInput();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		drawBackground();
		initPlatform();
		initPlayer();
		initFront();
		initShadowMask(x, y);
		mergeFinalTexture();
		game.getBatch().begin();
		game.getBatch().draw(finalLayer.getColorBufferTexture(), 0, 0);
		showFPS();
		game.getBatch().end();
	}

	/**
	 * Draw the final image to be renderer to the screen
	 */
	private void mergeFinalTexture() {
		finalLayer.begin();
		game.getBatch().begin();
		game.getBatch().draw(backgroundLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().draw(platformLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().draw(playerLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().draw(frontLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().draw(shadowLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().end();
		finalLayer.end();
	}

	private void drawBackground() {
		TextureRegion textureRegionBackground = SpriteService.getInstance().getTexture("menu_background_1", 0);
		backgroundLayer.begin();
		game.getBatch().begin();
		int idx = 0;
		while (idx < Constante.SCREEN_SIZE_X) {
			game.getBatch().draw(textureRegionBackground, idx, DrawUtils.invert(0, textureRegionBackground));
			idx += textureRegionBackground.getRegionWidth();
		}
		game.getBatch().end();
		backgroundLayer.end();
	}

	private void initPlatform() {
		platformLayer.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		platformLayer.end();
	}

	private void initPlayer() {
		playerLayer.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		playerLayer.end();
	}

	private void initFront() {
		frontLayer.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		frontLayer.end();
	}

	private void initShadowMask(int x, int y) {
		shadowLayer.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glColorMask(false, false, false, true);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0f, 0f, 0f, 0f));
		shapeRenderer.circle(x, y, 100);
		shapeRenderer.end();
		Gdx.gl.glColorMask(true, true, true, true);
		shadowLayer.end();
	}

	private void treatInput() {
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
				&& Gdx.input.isKeyJustPressed(Keys.K)) {
			game.getScreen().dispose();
			this.game.getSoundService().playMusic(MusicEnum.BOSS2);
			game.setScreen(new SelectOptionSoloScreen(game));
		}
		if (Gdx.input.isKeyJustPressed(Keys.F)) {
			if (Context.isShowFps()) {
				Context.setShowFps(false);
			} else {
				Context.setShowFps(true);
			}
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
	}

	private void initFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_verdana.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 10;
		parameter.borderWidth = 0.1f;
		parameter.borderColor = new Color(255, 255, 0, 255);
		parameter.color = new Color(255, 255, 0, 255);
		fontGold = generator.generateFont(parameter);
		generator.dispose();
	}

	private void showFPS() {
		if (Context.isShowFps()) {
			layout.setText(fontGold, Gdx.graphics.getFramesPerSecond() + " fps");
			fontGold.draw(game.getBatch(), layout, Constante.SCREEN_SIZE_X - layout.width, DrawUtils.invertText(10));
		}
	}
}
