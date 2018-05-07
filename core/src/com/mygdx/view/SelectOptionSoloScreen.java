package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.enumeration.GameOptionEnum;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.MessageService;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

/**
 * Ecran affichant la liste des options de jeu
 * 
 * @author alexispuskarczyk
 */
public class SelectOptionSoloScreen implements Screen {
	final InTheWellGame game;
	private BitmapFont fontWhite;
	private BitmapFont fontRed;
	private GlyphLayout layout;
	private int cursorPosition;

	public SelectOptionSoloScreen(final InTheWellGame game) {
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
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.mode.solo.title1"));
		fontRed.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(30));

		layout.setText(fontWhite, MessageService.getInstance().getMessage("menu.mode.solo.title2"));
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(70));

		drawOptionCheckBox(GameOptionEnum.MIROIR, 107, 147);
		drawOptionCheckBox(GameOptionEnum.CAUCHEMAR, 107, 207);
		drawOptionCheckBox(GameOptionEnum.NINJA, 107, 267);
		drawOptionCheckBox(GameOptionEnum.BOMB_EXPERT, 107, 327);
		drawOptionCheckBox(GameOptionEnum.TORNADE, 107, 387);

		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.solo.mirror"));
		fontRed.draw(game.getBatch(), layout, 170, DrawUtils.invertText(147));
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.solo.nightmare"));
		fontRed.draw(game.getBatch(), layout, 170, DrawUtils.invertText(207));
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.solo.ninjutsu"));
		fontRed.draw(game.getBatch(), layout, 170, DrawUtils.invertText(267));
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.solo.explode"));
		fontRed.draw(game.getBatch(), layout, 170, DrawUtils.invertText(327));
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.solo.tornade"));
		fontRed.draw(game.getBatch(), layout, 170, DrawUtils.invertText(387));

		TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_cursor", 0);
		switch (cursorPosition) {
		case 0:
			game.getBatch().draw(cursorTextureRegion, 70, DrawUtils.invert(147, cursorTextureRegion));
			break;
		case 1:
			game.getBatch().draw(cursorTextureRegion, 70, DrawUtils.invert(207, cursorTextureRegion));
			break;
		case 2:
			game.getBatch().draw(cursorTextureRegion, 70, DrawUtils.invert(267, cursorTextureRegion));
			break;
		case 3:
			game.getBatch().draw(cursorTextureRegion, 70, DrawUtils.invert(327, cursorTextureRegion));
			break;
		case 4:
			game.getBatch().draw(cursorTextureRegion, 70, DrawUtils.invert(387, cursorTextureRegion));
			break;
		}

		game.getBatch().end();
	}

	private void drawOptionCheckBox(GameOptionEnum gameOptionEnum, int x, int y) {
		if (game.getAccountService().getAvailableOption().contains(gameOptionEnum)) {
			if (game.getAccountService().getGameOptionSelected().contains(gameOptionEnum)) {
				TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_game_checkbox", 1);
				game.getBatch().draw(cursorTextureRegion, x, DrawUtils.invert(y, cursorTextureRegion));
			} else {
				TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_game_checkbox", 0);
				game.getBatch().draw(cursorTextureRegion, x, DrawUtils.invert(y, cursorTextureRegion));
			}
		} else {
			TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_game_checkbox", 2);
			game.getBatch().draw(cursorTextureRegion, x, DrawUtils.invert(y, cursorTextureRegion));

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

	public void treatInput() {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			game.getScreen().dispose();
			game.setScreen(new GameScreen(game));
		}
		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_RIGHT)) {
			game.getScreen().dispose();
			game.setScreen(new SelectModeScreen(game));
		}
		if (Gdx.input.isKeyJustPressed(Keys.X)) {
			switch (cursorPosition) {
			case 0:
				treatOption(GameOptionEnum.MIROIR);
				break;
			case 1:
				treatOption(GameOptionEnum.CAUCHEMAR);
				break;
			case 2:
				treatOption(GameOptionEnum.NINJA);
				break;
			case 3:
				treatOption(GameOptionEnum.BOMB_EXPERT);
				break;
			case 4:
				treatOption(GameOptionEnum.TORNADE);
				break;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (cursorPosition > 0) {
				cursorPosition--;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (cursorPosition < 4) {
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
