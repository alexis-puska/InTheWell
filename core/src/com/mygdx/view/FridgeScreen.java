package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.MessageService;
import com.mygdx.service.SpriteService;
import com.mygdx.utils.DrawUtils;

/**
 * Ecran affichant la liste des objets débloquées pour un compte utilisateur.
 * 
 * @author alexispuskarczyk
 */
public class FridgeScreen implements Screen {
	final InTheWellGame game;
	private BitmapFont fontRed;
	private BitmapFont fontWhite;
	private GlyphLayout layout;
	private int idItemSelected;
	private int idFirstItemDraw;

	public FridgeScreen(final InTheWellGame game) {
		this.game = game;
		this.layout = new GlyphLayout();
		this.idItemSelected = 0;
		this.idFirstItemDraw = 0;
		initFont();
	}

	@Override
	public void dispose() {
		fontRed.dispose();
		fontWhite.dispose();
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
		
		if (idItemSelected < idFirstItemDraw) {
			idFirstItemDraw = idItemSelected - (idItemSelected % 6);
		} else if (idItemSelected > (idFirstItemDraw + 41)) {
			idFirstItemDraw = idItemSelected - (idItemSelected % 6) - 36;
		}
		int index = idFirstItemDraw;
		
		//DRAW TITLE
		DrawUtils.fillBackground(game.getBatch(), "menu_background_2");
		layout.setText(fontRed, MessageService.getInstance().getMessage("menu.fridge.title"));
		fontRed.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(10));

		//DRAW ITEM
		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 6; x++) {
				if (index > 352) {
					break;
				}
				long qty = game.getAccountService().getFridgeQuantity(index);
				if (qty > 0) {
					TextureRegion tmp = SpriteService.getInstance().getTexture("objects", index);
					game.getBatch().draw(tmp, 10 + (x * 63), DrawUtils.invert(10 + (61 * y), tmp));
				} else {
					TextureRegion tmp = SpriteService.getInstance().getTexture("objects", 353);
					game.getBatch().draw(tmp, 10 + (x * 63), DrawUtils.invert(10 + (61 * y), tmp));
				}
				layout.setText(fontWhite, "" + qty);
				fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(30));
				index++;
			}
		}
		
		//DRAW ITEM NAME
		if (game.getAccountService().getFridgeQuantity(idItemSelected) > 0) {
			layout.setText(fontWhite, game.getAccountService().getItemName(idItemSelected));
			fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(500));
		} else {
			layout.setText(fontWhite, MessageService.getInstance().getMessage("noTranslation"));
			fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(500));
		}

		// DRAW ITEM POSITON ex : X / 353
		layout.setText(fontWhite, idItemSelected + 1 + " / 353");
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(480));
		
		// DRAW CURSOR
		int pos = idItemSelected - idFirstItemDraw;
		TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_cursor", 0);
		game.getBatch().draw(cursorTextureRegion, 10 + (pos % 6) * 63,
				DrawUtils.invert(50 + (int) (Math.floor(pos / 6)) * 61, cursorTextureRegion));
		game.getBatch().end();
	}

	public void treatInput() {
		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_RIGHT)) {
			this.game.setScreen(new MainScreen(game));
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			idItemSelected += 6;
			if (idItemSelected > 352) {
				idItemSelected = 352;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			idItemSelected -= 6;
			if (idItemSelected < 0) {
				idItemSelected = 0;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			idItemSelected--;
			if (idItemSelected < 0) {
				idItemSelected = 0;

			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			idItemSelected++;
			if (idItemSelected > 352) {
				idItemSelected = 352;
			}
		}
	}

	public void initFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_verdana.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 10;
		parameter.borderColor = new Color(255, 255, 255, 255);
		parameter.color = new Color(255, 255, 255, 255);
		fontWhite = generator.generateFont(parameter);
		parameter.size = 10;
		parameter.borderColor = new Color(255, 0, 0, 255);
		parameter.color = new Color(255, 0, 0, 255);
		fontRed = generator.generateFont(parameter);
		generator.dispose();
	}
}