package com.mygdx.view;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
import com.mygdx.service.dto.database.QuestDTO;
import com.mygdx.service.dto.database.RequiredDTO;
import com.mygdx.utils.DrawUtils;

/**
 * Ecran affichant la liste des quêtes débloquées pour un compte utilisateur.
 * 
 * @author alexispuskarczyk
 */
public class QuestScreen implements Screen {
	final InTheWellGame game;
	private BitmapFont fontRed;
	private BitmapFont fontGold;
	private BitmapFont fontGreen;
	private BitmapFont fontWhite;
	private GlyphLayout layout;
	private int questSelect;
	private int firstQuestView;
	private int offsetY;

	public QuestScreen(final InTheWellGame game) {
		this.game = game;
		this.layout = new GlyphLayout();
		this.questSelect = 0;
		this.firstQuestView = 0;
		initFont();
	}

	@Override
	public void dispose() {
		fontRed.dispose();
		fontGreen.dispose();
		fontGold.dispose();
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

		if (questSelect < firstQuestView) {
			firstQuestView = questSelect;
		} else if (questSelect > firstQuestView + 9) {
			firstQuestView = questSelect - 9;
		}

		DrawUtils.fillBackground(game.getBatch(), "menu_background_2");
		layout.setText(fontWhite, MessageService.getInstance().getMessage("menu.quests.titles"));
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(10));

		// DRAW LIST QUESTS
		int index = firstQuestView;
		for (int i = 0; i < 10; i++) {
			if (game.getAccountService().getQuestStarted().containsKey(index)) {
				layout.setText(fontGold, index + " / 75 - " + game.getAccountService().getQuestName(index));
				fontGold.draw(game.getBatch(), layout, 40, DrawUtils.invertText(i * 12 + 30));
			} else if (game.getAccountService().getQuestCompleted().containsKey(index)) {
				layout.setText(fontGreen, index + " / 75 - " + game.getAccountService().getQuestName(index));
				fontGreen.draw(game.getBatch(), layout, 40, DrawUtils.invertText(i * 12 + 30));
			} else {
				layout.setText(fontRed, index + " / 75 - ??????????");
				fontRed.draw(game.getBatch(), layout, 40, DrawUtils.invertText(i * 12 + 30));
			}
			index++;
		}

		// DRAW CURSOR
		TextureRegion cursorTextureRegion = SpriteService.getInstance().getTexture("menu_cursor", 0);
		game.getBatch().draw(cursorTextureRegion, 10,
				DrawUtils.invert((12 * (questSelect - firstQuestView)) + 24, cursorTextureRegion));

		// DRAW CONTENT

		if (game.getAccountService().getQuestStarted().containsKey(questSelect)
				|| game.getAccountService().getQuestCompleted().containsKey(questSelect)) {
			QuestDTO quest = game.getAccountService().getQuestDTO(questSelect);
			List<RequiredDTO> required = quest.getRequire();
			int x = 0;
			int y = 0;
			int offsetX = 21;
			int offsetX2 = 52;

			switch (required.size()) {
			case 1:
				offsetX = 178;
				offsetX2 = 21;
				break;
			case 2:
				offsetX = 147;
				offsetX2 = 21;
				break;
			case 3:
				offsetX = 115;
				offsetX2 = 21;
				break;
			case 4:
				offsetX = 84;
				offsetX2 = 21;
				break;
			case 5:
				offsetX = 52;
				offsetX2 = 21;
				break;
			case 6:
				offsetX = 21;
				offsetX2 = 21;
				break;
			case 7:
				offsetX = 21;
				offsetX2 = 178;
				break;
			case 8:
				offsetX = 21;
				offsetX2 = 147;
				break;
			case 9:
				offsetX = 21;
				offsetX2 = 115;
				break;
			case 10:
				offsetX = 21;
				offsetX2 = 84;
				break;
			case 11:
				offsetX = 21;
				offsetX2 = 52;
				break;
			case 12:
				offsetX = 21;
				offsetX2 = 21;
				break;
			}
			for (RequiredDTO r : required) {
				if (y == 0) {
					TextureRegion object = SpriteService.getInstance().getTexture("objects", r.getId());
					game.getBatch().draw(object, offsetX + (x * 63), DrawUtils.invert(200 + (61 * y), object));

					layout.setText(fontWhite,
							game.getAccountService().getFridgeQuantity(r.getId()) + " / " + r.getVal());
					fontWhite.draw(game.getBatch(), layout, offsetX + 31 + (x * 63) - (layout.width / 2),
							DrawUtils.invertText(263 + (61 * y)));

				} else {
					TextureRegion object = SpriteService.getInstance().getTexture("objects", r.getId());
					game.getBatch().draw(object, offsetX2 + (x * 63), DrawUtils.invert(200 + (61 * y), object));

					layout.setText(fontWhite,
							game.getAccountService().getFridgeQuantity(r.getId()) + " / " + r.getVal());
					fontWhite.draw(game.getBatch(), layout, offsetX2 + 31 + (x * 63) - (layout.width / 2),
							DrawUtils.invertText(263 + (61 * y)));
				}
				x++;
				if (x >= 6) {
					x = 0;
					y++;
				}
			}
		} else {
			layout.setText(fontWhite, MessageService.getInstance().getMessage("noTranslation"));
			fontWhite.draw(game.getBatch(), layout, 210, DrawUtils.invertText(200));
		}

		// DESCRIPTION
		if (game.getAccountService().getQuestStarted().containsKey(questSelect)) {
			layout.setText(fontWhite, MessageService.getInstance().getMessage("noTranslation"));
			fontWhite.draw(game.getBatch(), layout, 210, DrawUtils.invertText(352));
		} else if (game.getAccountService().getQuestCompleted().containsKey(questSelect)) {
			int idx = 0;
			offsetY = 0;
			int lastSpace = 0;
			int lastSplit = 0;
			int slice = 0;
			String description = game.getAccountService().getQuestDescription(questSelect);
			while (true) {
				if (idx >= description.length() - 1) {
					drawSplitDescription(description.substring(lastSplit,  description.length()));
					break;
				}
				if (description.charAt(idx) == ' ') {
					if (slice > 52) {
						drawSplitDescription(description.substring(lastSplit, lastSpace));
						offsetY++;
						slice = 0;
						lastSplit = lastSpace;
					}
					lastSpace = idx;
				}
				slice++;
				idx++;
			}
		} else {
			layout.setText(fontWhite, MessageService.getInstance().getMessage("noTranslation"));
			fontWhite.draw(game.getBatch(), layout, 210, DrawUtils.invertText(352));
		}
		game.getBatch().end();
	}

	private void drawSplitDescription(String split) {
		layout.setText(fontWhite, split);
		fontWhite.draw(game.getBatch(), layout, 210 - (layout.width / 2), DrawUtils.invertText(352 + (offsetY * 12)));
	}

	public void treatInput() {
		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_RIGHT)) {
			game.getScreen().dispose();
			game.setScreen(new MainScreen(game));
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (questSelect > 0) {
				questSelect--;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (questSelect < Constante.NB_QUEST) {
				questSelect++;
			}
		}
	}

	public void initFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_verdana.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 10;
		parameter.borderWidth = 0.2f;
		parameter.borderColor = new Color(255, 0, 0, 255);
		parameter.color = new Color(255, 0, 0, 255);
		fontRed = generator.generateFont(parameter);
		parameter.borderColor = new Color(255, 255, 0, 255);
		parameter.color = new Color(255, 255, 0, 255);
		fontGold = generator.generateFont(parameter);
		parameter.borderColor = new Color(0, 255, 0, 255);
		parameter.color = new Color(0, 255, 0, 255);
		fontGreen = generator.generateFont(parameter);
		parameter.borderColor = new Color(255, 255, 255, 255);
		parameter.color = new Color(255, 255, 255, 255);
		fontWhite = generator.generateFont(parameter);
		generator.dispose();
	}
}