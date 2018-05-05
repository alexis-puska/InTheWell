package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.service.AccountService;
import com.mygdx.service.JsonService;
import com.mygdx.service.MessageService;
import com.mygdx.service.SoundService;
import com.mygdx.service.SpriteService;
import com.mygdx.view.SplashScreen;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InTheWellGame extends Game {

	private SpriteBatch batch;
	private AccountService accountService;
	private JsonService jsonService;
	private SoundService soundService;

	public void create() {
		MessageService.getInstance();
		SpriteService.getInstance();
		batch = new SpriteBatch();
		accountService = new AccountService();
		jsonService = new JsonService();
		soundService = new SoundService();
		soundService.playMusicBoss2();
		this.setScreen(new SplashScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
	}
}
