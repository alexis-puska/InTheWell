package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.enumeration.MusicEnum;
import com.mygdx.service.AccountService;
import com.mygdx.service.LevelService;
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
	private LevelService levelService;
	private SoundService soundService;

	public void create() {
		MessageService.getInstance();
		SpriteService.getInstance();
		batch = new SpriteBatch();
		accountService = new AccountService();
		levelService = new LevelService();
		soundService = new SoundService();
		soundService.playMusic(MusicEnum.BOSS2);;
		this.setScreen(new SplashScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
	}
}
