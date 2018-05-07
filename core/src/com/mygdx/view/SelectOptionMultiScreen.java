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
public class SelectOptionMultiScreen implements Screen {
	final InTheWellGame game;
	private BitmapFont fontWhite;
	private BitmapFont fontRed;
	private GlyphLayout layout;
	private int cursorPosition;

	public SelectOptionMultiScreen(final InTheWellGame game) {
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
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.multi.title"));
		fontRed.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(30));
		layout.setText(fontWhite, MessageService.getInstance().getMessage("menu.main.multi.description"));
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(60));

		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.multi.mirror"));
		fontRed.draw(game.getBatch(), layout, 170, DrawUtils.invertText(147));
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.multi.nightmare"));
		fontRed.draw(game.getBatch(), layout, 170, DrawUtils.invertText(207));
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.multi.share"));
		fontRed.draw(game.getBatch(), layout, 170, DrawUtils.invertText(267));
		drawOptionCheckBox(GameOptionEnum.MIROIR_MULTI, 107, 147);
		drawOptionCheckBox(GameOptionEnum.CHAUCHEMAR_MULTI, 107, 207);
		drawOptionCheckBox(GameOptionEnum.PARTAGE_VIE, 107, 267);

		// CURSOR
		TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_cursor", 0);
		game.getBatch().draw(cursorTextureRegion, 75, DrawUtils.invert(147 + cursorPosition * 60, cursorTextureRegion));
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
				treatOption(GameOptionEnum.MIROIR_MULTI);
				break;
			case 1:
				treatOption(GameOptionEnum.CHAUCHEMAR_MULTI);
				break;
			case 2:
				treatOption(GameOptionEnum.PARTAGE_VIE);
				break;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (cursorPosition > 0) {
				cursorPosition--;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (cursorPosition < 2) {
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
