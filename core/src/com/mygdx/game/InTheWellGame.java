package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.enumeration.LocaleEnum;
import com.mygdx.service.JsonService;
import com.mygdx.service.MessageService;
import com.mygdx.service.SaveService;
import com.mygdx.service.SoundService;
import com.mygdx.view.SelectionLangScreen;

public class InTheWellGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public Texture img;

	public void create() {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("sprite/badlogic.jpg"));
		Gdx.app.log("InTheWell", MessageService.getMessage("menu.lang.title"));
		MessageService.setLocale(LocaleEnum.ENGLISH);
		Gdx.app.log("InTheWell", MessageService.getMessage("menu.lang.title"));
		MessageService.setLocale(LocaleEnum.SPANISH);
		Gdx.app.log("InTheWell", MessageService.getMessage("menu.lang.title"));
		SaveService.getInstance().loadAccount(0);
		JsonService.getInstance();
		SoundService.getInstance().playMusic();
		this.setScreen(new SelectionLangScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
