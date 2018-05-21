package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.constante.Constante;
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
	private OrthographicCamera gameCamera;
	private Viewport viewport;
	private AccountService accountService;
	private LevelService levelService;
	private SoundService soundService;
	private MenuInputProcessor menuInputProcessor;

	public void create() {
		MessageService.getInstance();
		SpriteService.getInstance();

		/****************************************
		 * Camera and viewport to draw fit image
		 ****************************************/
		gameCamera = new OrthographicCamera();
		gameCamera.position.set(Constante.SCREEN_SIZE_X / 2, Constante.SCREEN_SIZE_Y / 2, 0);
		gameCamera.update();
		viewport = new FitViewport(Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, gameCamera);
		viewport.apply();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(gameCamera.combined);
		accountService = new AccountService();
		levelService = new LevelService();
		menuInputProcessor = new MenuInputProcessor();
		soundService = new SoundService();
		soundService.playMusic(MusicEnum.BOSS2);
		Gdx.input.setInputProcessor(menuInputProcessor);
		this.setScreen(new SplashScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
	}
}
