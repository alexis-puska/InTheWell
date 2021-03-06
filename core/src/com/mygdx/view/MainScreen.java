package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.MessageService;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

/**
 * Ecran principale, Choix entre jouer, frigo, quêtes
 * 
 * @author alexispuskarczyk
 */
public class MainScreen implements Screen {
	final InTheWellGame game;
	private BitmapFont font;
	private GlyphLayout layout;
	private int cursorPosition;

	public MainScreen(final InTheWellGame game) {
		this.game = game;
		this.layout = new GlyphLayout();
		this.cursorPosition = 0;
		initFont();
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	@Override
	public void show() {
		// unused method
	}

	@Override
	public void resize(int width, int height) {
		game.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// unused method
	}

	@Override
	public void resume() {
		// unused method
	}

	@Override
	public void hide() {
		// unused method
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getScreenCamera().update();
		treatInput();
		game.getBatch().begin();
		DrawUtils.fillBackground(game.getBatch(), "menu_background_2");
		layout.setText(font, MessageService.getInstance().getMessage("menu.main.title"));
		font.draw(game.getBatch(), layout, 210 - (layout.width / 2), 480);

		for (int i = 0; i < 3; i++) {
			TextureRegion flagTextureRegion = SpriteService.getInstance().getTexture("menu_game", i);
			game.getBatch().draw(flagTextureRegion, 127, 328 - (90 * i));
		}

		layout.setText(font, MessageService.getInstance().getMessage("menu.main.play"));
		font.draw(game.getBatch(), layout, 210, 373);
		layout.setText(font, MessageService.getInstance().getMessage("menu.main.fridge"));
		font.draw(game.getBatch(), layout, 210, 283);
		layout.setText(font, MessageService.getInstance().getMessage("menu.main.quests"));
		font.draw(game.getBatch(), layout, 210, 193);

		TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_cursor", 0);
		switch (cursorPosition) {
		case 0:
			game.getBatch().draw(cursorTextureRegion, 110, 353);
			break;
		case 1:
			game.getBatch().draw(cursorTextureRegion, 110, 263);
			break;
		case 2:
			game.getBatch().draw(cursorTextureRegion, 110, 173);
			break;
		default:
			game.getBatch().draw(cursorTextureRegion, 110, 353);
		}
		game.getBatch().end();
	}

	public void treatInput() {
		if (game.getMenuInputProcessor().pressNext()) {
			game.getScreen().dispose();

			switch (cursorPosition) {
			case 0:
				game.setScreen(new SelectModeScreen(game));
				break;
			case 1:
				game.setScreen(new FridgeScreen(game));
				break;
			case 2:
				game.setScreen(new QuestScreen(game));
				break;
			default:
				game.setScreen(new SelectModeScreen(game));
			}
		}
		if (game.getMenuInputProcessor().pressPrevious()) {
			game.getScreen().dispose();
			game.setScreen(new SelectAccountScreen(game));
		}
		if (game.getMenuInputProcessor().pressUp() && cursorPosition > 0) {
			cursorPosition--;
		}
		if (game.getMenuInputProcessor().pressDown() && cursorPosition < 2) {
			cursorPosition++;
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
