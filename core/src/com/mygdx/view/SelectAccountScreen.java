package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.constante.Constante;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.MessageService;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

/**
 * Ecran de sélection de compte joueur
 * 
 * @author alexispuskarczyk
 */
public class SelectAccountScreen implements Screen {

	final InTheWellGame game;
	private BitmapFont font;
	private GlyphLayout layout;
	private int indexAccount;

	public SelectAccountScreen(final InTheWellGame game) {
		this.game = game;
		this.layout = new GlyphLayout();
		this.indexAccount = 0;
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
		for (int i = 0; i < 4; i++) {
			TextureRegion menuPlayerTextureRegion = SpriteService.getInstance().getTexture("menu_player", i);
			game.getBatch().draw(menuPlayerTextureRegion, 67, 309 - (90 * i));
			if (i != indexAccount) {
				TextureRegion menuIceTextureRegion = SpriteService.getInstance().getTexture("menu_ice", 0);
				game.getBatch().draw(menuIceTextureRegion, 60, 302 - (90 * i));
			}
		}
		layout.setText(font, MessageService.getInstance().getMessage("menu.save.title1"));
		font.draw(game.getBatch(), layout, 210 - (layout.width / 2), 490);
		layout.setText(font, MessageService.getInstance().getMessage("menu.save.title2"));
		font.draw(game.getBatch(), layout, 210 - (layout.width / 2), 460);
		layout.setText(font, MessageService.getInstance().getMessage("menu.save.name1"));
		font.draw(game.getBatch(), layout, 150, 353);
		layout.setText(font, MessageService.getInstance().getMessage("menu.save.name2"));
		font.draw(game.getBatch(), layout, 150, 263);
		layout.setText(font, MessageService.getInstance().getMessage("menu.save.name3"));
		font.draw(game.getBatch(), layout, 150, 173);
		layout.setText(font, MessageService.getInstance().getMessage("menu.save.name4"));
		font.draw(game.getBatch(), layout, 150, 83);
		game.getBatch().end();
	}

	public void treatInput() {
		if (game.getMenuInputProcessor().pressNext()) {
			game.getAccountService().loadAccount(indexAccount);
			game.getScreen().dispose();
			game.setScreen(new MainScreen(game));
		}
		if (game.getMenuInputProcessor().pressPrevious()) {
			game.getScreen().dispose();
			game.setScreen(new SelectionLangScreen(game));
		}
		if (game.getMenuInputProcessor().pressUp()) {
			if (indexAccount > 0) {
				indexAccount--;
			}
		}
		if (game.getMenuInputProcessor().pressDown()) {
			if (indexAccount < Constante.NB_SAVE_PER_FILE - 1) {
				indexAccount++;
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
