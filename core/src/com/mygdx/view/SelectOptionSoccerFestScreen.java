package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.enumeration.GameOptionEnum;
import com.mygdx.enumeration.GameSoccerMapEnum;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.MessageService;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

/**
 * Ecran affichant la liste des options de jeu
 * 
 * @author alexispuskarczyk
 */
public class SelectOptionSoccerFestScreen implements Screen {
	final InTheWellGame game;
	private BitmapFont fontWhite;
	private BitmapFont fontRed;
	private GlyphLayout layout;
	private int cursorPosition;

	public SelectOptionSoccerFestScreen(final InTheWellGame game) {
		this.game = game;
		this.layout = new GlyphLayout();
		this.cursorPosition = 0;
		initFont();
	}

	@Override
	public void dispose() {
		fontWhite.dispose();
		fontRed.dispose();
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
		DrawUtils.fillBackground(game.getBatch(), "menu_background_2");
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.soccer.title"));
		fontRed.draw(game.getBatch(), layout, 210 - (layout.width / 2), 490);
		layout.setText(fontWhite, MessageService.getInstance().getMessage("menu.main.soccer.description1"));
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), 450);
		layout.setText(fontWhite, MessageService.getInstance().getMessage("menu.main.soccer.description2"));
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), 435);

		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.soccer.advanceControl"));
		fontRed.draw(game.getBatch(), layout, 170, 368);
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.soccer.bombe"));
		fontRed.draw(game.getBatch(), layout, 170, 308);
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.soccer.map1"));
		fontRed.draw(game.getBatch(), layout, 170, 248);
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.soccer.map2"));
		fontRed.draw(game.getBatch(), layout, 170, 188);
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.soccer.map3"));
		fontRed.draw(game.getBatch(), layout, 170, 128);
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.soccer.map4"));
		fontRed.draw(game.getBatch(), layout, 170, 68);

		TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_cursor", 0);
		game.getBatch().draw(cursorTextureRegion, 75, 353 - cursorPosition * 60);

		drawOptionCheckBox(GameOptionEnum.CONTROLE_BALLON, 107, 348);
		drawOptionCheckBox(GameOptionEnum.BOMB_EXPERT, 107, 288);

		drawRadio(GameSoccerMapEnum.GAZON_MAUDIT, 107, 228);
		drawRadio(GameSoccerMapEnum.TEMPLE_BALLON, 107, 168);
		drawRadio(GameSoccerMapEnum.VOLLEY_FEST, 107, 108);
		drawRadio(GameSoccerMapEnum.MAITRISE_AERIENNE, 107, 48);

		game.getBatch().end();
	}

	private void drawOptionCheckBox(GameOptionEnum gameOptionEnum, int x, int y) {
		if (game.getAccountService().getAvailableOption().contains(gameOptionEnum)) {
			if (game.getAccountService().getGameOptionSelected().contains(gameOptionEnum)) {
				TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_game_checkbox", 1);
				game.getBatch().draw(cursorTextureRegion, x, y);
			} else {
				TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_game_checkbox", 0);
				game.getBatch().draw(cursorTextureRegion, x, y);
			}
		} else {
			TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_game_checkbox", 2);
			game.getBatch().draw(cursorTextureRegion, x, y);

		}
	}

	private void treatOption(GameOptionEnum option) {
		if (game.getAccountService().getAvailableOption().contains(option)) {
			if (game.getAccountService().getGameOptionSelected().contains(option)) {
				game.getAccountService().removeGameOptionSelected(option);
			} else {
				game.getAccountService().addGameOptionSelected(option);
			}
		}
	}

	private void drawRadio(GameSoccerMapEnum map, int x, int y) {
		if (game.getAccountService().getGameSoccerMapSelected() == map) {
			TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_game_radio", 0);
			game.getBatch().draw(cursorTextureRegion, x, y);
		} else {
			TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_game_radio", 1);
			game.getBatch().draw(cursorTextureRegion, x, y);
		}
	}

	public void treatInput() {
		if (game.getMenuInputProcessor().pressNext()) {
			game.getScreen().dispose();
			game.setScreen(new GameScreen(game));
		}
		if (game.getMenuInputProcessor().pressPrevious()) {
			game.getScreen().dispose();
			game.setScreen(new SelectModeScreen(game));
		}
		if (game.getMenuInputProcessor().pressValide()) {
			switch (cursorPosition) {
			case 0:
				treatOption(GameOptionEnum.CONTROLE_BALLON);
				break;
			case 1:
				treatOption(GameOptionEnum.BOMB_EXPERT);
				break;
			case 2:
				game.getAccountService().setGameSoccerMapSelected(GameSoccerMapEnum.GAZON_MAUDIT);
				break;
			case 3:
				game.getAccountService().setGameSoccerMapSelected(GameSoccerMapEnum.TEMPLE_BALLON);
				break;
			case 4:
				game.getAccountService().setGameSoccerMapSelected(GameSoccerMapEnum.VOLLEY_FEST);
				break;
			case 5:
				game.getAccountService().setGameSoccerMapSelected(GameSoccerMapEnum.MAITRISE_AERIENNE);
				break;
			}
		}
		if (game.getMenuInputProcessor().pressUp()) {
			if (cursorPosition > 0) {
				cursorPosition--;
			}
		}
		if (game.getMenuInputProcessor().pressDown()) {
			if (cursorPosition < 5) {
				cursorPosition++;
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
		fontRed = generator.generateFont(parameter);
		parameter.size = 10;
		parameter.borderWidth = 0.1f;
		parameter.borderColor = new Color(255, 255, 255, 255);
		parameter.color = new Color(255, 255, 255, 255);
		fontWhite = generator.generateFont(parameter);
		generator.dispose();

	}
}
