package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.service.JsonService;
import com.mygdx.service.SaveService;
import com.mygdx.service.SoundService;
import com.mygdx.service.SpriteService;
import com.mygdx.view.SplashScreen;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InTheWellGame extends Game {

	private SpriteBatch batch;

	public void create() {
		batch = new SpriteBatch();
		JsonService.getInstance();
		SaveService.getInstance().loadAccount(0);
		SpriteService.getInstance();
		SoundService.getInstance().playMusic();
		this.setScreen(new SplashScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
	}
}
