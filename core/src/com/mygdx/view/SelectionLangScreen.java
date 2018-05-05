package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.enumeration.LocaleEnum;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.Context;
import com.mygdx.service.MessageService;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

/**
 * Ecran de selection de la langue
 * 
 * @author alexispuskarczyk
 */
public class SelectionLangScreen implements Screen {

	final InTheWellGame game;
	private BitmapFont font;
	private GlyphLayout layout;

	public SelectionLangScreen(final InTheWellGame game) {
		this.game = game;
		this.layout = new GlyphLayout();
		initFont();
	}

	@Override
	public void dispose() {
		font.dispose();
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
		game.getBatch().begin();
		DrawUtils.fillBackground(game.getBatch(), "menu_background_1");
		for (int i = 0; i < 3; i++) {
			TextureRegion flagTextureRegion = SpriteService.getInstance().getTexture("flag", i);
			game.getBatch().draw(flagTextureRegion, (420 - 120) / 2,
					DrawUtils.invert(70 + (i * 150), flagTextureRegion));
		}

		layout.setText(font, MessageService.getInstance().getMessage("menu.lang.title"));
		font.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(40));
		layout.setText(font, MessageService.getInstance().getMessage("menu.lang.lang1"));
		font.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(154));
		layout.setText(font, MessageService.getInstance().getMessage("menu.lang.lang2"));
		font.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(302));
		layout.setText(font, MessageService.getInstance().getMessage("menu.lang.lang3"));
		font.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(454));

		TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_cursor", 0);
		switch (Context.getLocale()) {
		case FRENCH:
			game.getBatch().draw(cursorTextureRegion, 110, DrawUtils.invert(100, cursorTextureRegion));
			break;
		case ENGLISH:
			game.getBatch().draw(cursorTextureRegion, 110, DrawUtils.invert(250, cursorTextureRegion));
			break;
		case SPANISH:
			game.getBatch().draw(cursorTextureRegion, 110, DrawUtils.invert(400, cursorTextureRegion));
			break;
		}
		game.getBatch().end();
	}

	public void treatInput() {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			game.getScreen().dispose();
			game.setScreen(new SelectAccountScreen(game));
		}
		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_RIGHT)) {
			game.getScreen().dispose();
			game.setScreen(new SplashScreen(game));
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			switch (Context.getLocale()) {
			case FRENCH:
				break;
			case ENGLISH:
				Context.setLocale(LocaleEnum.FRENCH);
				break;
			case SPANISH:
				Context.setLocale(LocaleEnum.ENGLISH);
				break;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			switch (Context.getLocale()) {
			case FRENCH:
				Context.setLocale(LocaleEnum.ENGLISH);
				break;
			case ENGLISH:
				Context.setLocale(LocaleEnum.SPANISH);
				break;
			case SPANISH:

				break;
			}
		}
	}

	public void initFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_verdana.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 20;
		parameter.borderWidth = 0.5f;
		parameter.borderColor = new Color(255, 0, 0, 255);
		parameter.color = new Color(255, 0, 0, 255);
		font = generator.generateFont(parameter);
		generator.dispose();
	}
}
