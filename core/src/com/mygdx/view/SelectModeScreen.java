package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.enumeration.GameModeEnum;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.MessageService;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

/**
 * Ecran affichant la liste mode de jeu.
 * 
 * @author alexispuskarczyk
 */
public class SelectModeScreen implements Screen {
	final InTheWellGame game;
	private BitmapFont font;
	private GlyphLayout layout;

	public SelectModeScreen(final InTheWellGame game) {
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
		DrawUtils.fillBackground(game.getBatch(), "menu_background_2");
		layout.setText(font, MessageService.getInstance().getMessage("menu.mode.title"));
		font.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(30));

		// DRAW ICON
		for (int i = 0; i < 5; i++) {
			TextureRegion gameModeIcon = SpriteService.getInstance().getTexture("menu_game_option", i);
			game.getBatch().draw(gameModeIcon, 107, DrawUtils.invert(147 + (60 * i), gameModeIcon));
		}

		// DRAW TEXT MODE
		layout.setText(font, MessageService.getInstance().getMessage("menu.mode.solo"));
		font.draw(game.getBatch(), layout, 170, DrawUtils.invertText(147));
		layout.setText(font, MessageService.getInstance().getMessage("menu.main.tutorial"));
		font.draw(game.getBatch(), layout, 170, DrawUtils.invertText(207));
		if (game.getAccountService().getAvailableMode().contains(GameModeEnum.TIME_ATTACK)) {
			layout.setText(font, MessageService.getInstance().getMessage("menu.main.timeAttack"));
		} else {
			layout.setText(font, MessageService.getInstance().getMessage("noTranslation"));
		}
		font.draw(game.getBatch(), layout, 170, DrawUtils.invertText(267));

		if (game.getAccountService().getAvailableMode().contains(GameModeEnum.MULTI_COOPERATIF)) {
			layout.setText(font, MessageService.getInstance().getMessage("menu.main.multi"));
		} else {
			layout.setText(font, MessageService.getInstance().getMessage("noTranslation"));
		}
		font.draw(game.getBatch(), layout, 170, DrawUtils.invertText(327));
		layout.setText(font, MessageService.getInstance().getMessage("menu.main.soccer"));
		font.draw(game.getBatch(), layout, 170, DrawUtils.invertText(387));

		// DRAW CURSOE
		int offset = 0;
		if (game.getAccountService().getGameModeSelected() == GameModeEnum.SOLO) {
			offset = 0;
		} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.TUTORIAL) {
			offset = 1;
		} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.TIME_ATTACK) {
			offset = 2;
		} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.MULTI_COOPERATIF) {
			offset = 3;
		} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.SOCCERFEST) {
			offset = 4;
		}
		TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_cursor", 0);
		game.getBatch().draw(cursorTextureRegion, 75, DrawUtils.invert(147 + offset * 60, cursorTextureRegion));
		game.getBatch().end();
	}

	public void treatInput() {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			game.getScreen().dispose();
			if (game.getAccountService().getGameModeSelected() == GameModeEnum.SOLO) {
				game.getAccountService().resetGameOptionSelected();
				game.setScreen(new SelectOptionSoloScreen(game));
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.TUTORIAL) {
				game.getAccountService().resetGameOptionSelected();
				game.setScreen(new SelectOptionTutorialScreen(game));
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.TIME_ATTACK) {
				game.getAccountService().resetGameOptionSelected();
				game.setScreen(new SelectOptionTimeAttackScreen(game));
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.MULTI_COOPERATIF) {
				game.getAccountService().resetGameOptionSelected();
				game.setScreen(new SelectOptionMultiScreen(game));
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.SOCCERFEST) {
				game.getAccountService().resetGameOptionSelected();
				game.setScreen(new SelectOptionSoccerFestScreen(game));
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_RIGHT)) {
			game.getScreen().dispose();
			game.setScreen(new MainScreen(game));
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (game.getAccountService().getGameModeSelected() == GameModeEnum.TUTORIAL) {
				game.getAccountService().setGameModeSelected(GameModeEnum.SOLO);
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.TIME_ATTACK) {
				game.getAccountService().setGameModeSelected(GameModeEnum.TUTORIAL);
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.MULTI_COOPERATIF) {
				if (game.getAccountService().getAvailableMode().contains(GameModeEnum.TIME_ATTACK)) {
					game.getAccountService().setGameModeSelected(GameModeEnum.TIME_ATTACK);
				} else {
					game.getAccountService().setGameModeSelected(GameModeEnum.TUTORIAL);
				}
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.SOCCERFEST) {
				if (game.getAccountService().getAvailableMode().contains(GameModeEnum.MULTI_COOPERATIF)) {
					game.getAccountService().setGameModeSelected(GameModeEnum.MULTI_COOPERATIF);
				} else if (game.getAccountService().getAvailableMode().contains(GameModeEnum.TIME_ATTACK)) {
					game.getAccountService().setGameModeSelected(GameModeEnum.TIME_ATTACK);
				} else {
					game.getAccountService().setGameModeSelected(GameModeEnum.TUTORIAL);
				}
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (game.getAccountService().getGameModeSelected() == GameModeEnum.SOLO) {
				game.getAccountService().setGameModeSelected(GameModeEnum.TUTORIAL);
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.TUTORIAL) {
				if (game.getAccountService().getAvailableMode().contains(GameModeEnum.TIME_ATTACK)) {
					game.getAccountService().setGameModeSelected(GameModeEnum.TIME_ATTACK);
				} else if (game.getAccountService().getAvailableMode().contains(GameModeEnum.MULTI_COOPERATIF)) {
					game.getAccountService().setGameModeSelected(GameModeEnum.MULTI_COOPERATIF);
				} else {
					game.getAccountService().setGameModeSelected(GameModeEnum.SOCCERFEST);
				}
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.TIME_ATTACK) {
				if (game.getAccountService().getAvailableMode().contains(GameModeEnum.MULTI_COOPERATIF)) {
					game.getAccountService().setGameModeSelected(GameModeEnum.MULTI_COOPERATIF);
				} else {
					game.getAccountService().setGameModeSelected(GameModeEnum.SOCCERFEST);
				}
			} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.MULTI_COOPERATIF) {
				game.getAccountService().setGameModeSelected(GameModeEnum.SOCCERFEST);
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