package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.enumeration.LocaleEnum;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.ConfigurationContext;
import com.mygdx.service.MessageService;
import com.mygdx.utils.AxisUtils;

public class SelectAccountScreen implements Screen {

	private final InTheWellGame game;
	private BitmapFont font;
	private BitmapFont fpsFont;
	private GlyphLayout layout;
	private Texture img;
	
	public SelectAccountScreen(final InTheWellGame game) {
		this.game = game;
		this.layout = new GlyphLayout();
		this.img = new Texture(Gdx.files.internal("sprite/sprite_animation.png"));
		initFont();
	}

	@Override
	public void render(float delta) {
		ConfigurationContext.setShowFps(false);
		treatInput();
		
		Gdx.gl.glClearColor(1.0f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().begin();
		game.getBatch().draw(img, 0, AxisUtils.invert(0, img));
		if (ConfigurationContext.isShowFps()) {
			layout.setText(fpsFont, "fps : " + Gdx.graphics.getFramesPerSecond());
			fpsFont.draw(game.getBatch(), layout, 10, AxisUtils.invertText(100));
		}
		layout.setText(font, MessageService.getMessage("menu.lang.title"));
		font.draw(game.getBatch(), layout, 10, AxisUtils.invertText(60));
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
		Gdx.app.log("select account", "dispose");
		font.dispose();
		fpsFont.dispose();
		img.dispose();
	}

	private void initFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_satans.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 20;
		parameter.color = new Color(0, 255, 0, 255);
		font = generator.generateFont(parameter);
		generator.dispose();
		FreeTypeFontGenerator generatorImpact = new FreeTypeFontGenerator(Gdx.files.internal("font/font_impact.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameterImpact = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameterImpact.size = 10;
		parameterImpact.color = new Color(0, 255, 255, 255);
		fpsFont = generatorImpact.generateFont(parameterImpact);
		generatorImpact.dispose();
	}

	private void treatInput() {
		if (Gdx.input.isKeyJustPressed(Keys.B)) {
			game.getScreen().dispose();
			game.setScreen(new SplashScreen(game));
		}
		if (Gdx.input.isKeyJustPressed(Keys.E)) {
			ConfigurationContext.setLocale(LocaleEnum.ENGLISH);
		}
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			ConfigurationContext.setLocale(LocaleEnum.FRENCH);
		}
		if (Gdx.input.isKeyJustPressed(Keys.T)) {
			ConfigurationContext.setLocale(LocaleEnum.SPANISH);
		}
		if (Gdx.input.isKeyPressed(Keys.F)) {
			ConfigurationContext.setShowFps(true);
		}
	}
}
