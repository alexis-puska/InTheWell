package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.MessageService;
import com.mygdx.utils.DrawUtils;

/**
 * Ecran affichant la liste des options de jeu
 * 
 * @author alexispuskarczyk
 */
public class SelectOptionTimeAttackScreen implements Screen {
	final InTheWellGame game;
	private BitmapFont fontWhite;
	private BitmapFont fontRed;
	private GlyphLayout layout;
	private int cursorPosition;

	public SelectOptionTimeAttackScreen(final InTheWellGame game) {
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
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.main.timeAttack.title"));
		fontRed.draw(game.getBatch(), layout, 210 - (layout.width / 2), 490);

		layout.setText(fontWhite, MessageService.getInstance().getMessage("menu.main.timeAttack.description1"));
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), 450);
		layout.setText(fontWhite, MessageService.getInstance().getMessage("menu.main.timeAttack.description2"));
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), 435);
		layout.setText(fontWhite, MessageService.getInstance().getMessage("menu.main.timeAttack.description3"));
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), 420);

		game.getBatch().end();
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
		if (game.getMenuInputProcessor().pressUp()) {
			if (cursorPosition > 0) {
				cursorPosition--;
			}
		}
		if (game.getMenuInputProcessor().pressDown()) {
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
