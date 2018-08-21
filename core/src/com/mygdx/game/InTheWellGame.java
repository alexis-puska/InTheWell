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
import com.mygdx.service.NotificationService;
import com.mygdx.service.SoundService;
import com.mygdx.service.SpriteService;
import com.mygdx.service.input_processor.MenuInputProcessor;
import com.mygdx.view.SplashScreen;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InTheWellGame extends Game {

	private SpriteBatch batch;
	private OrthographicCamera screenCamera;
	private Viewport viewport;
	private AccountService accountService;
	private LevelService levelService;
	private NotificationService notificationService;
	private MenuInputProcessor menuInputProcessor;

	public void create() {
		Gdx.app.setLogLevel(Constante.LIBGDX_LOG_LEVEL);
		MessageService.getInstance();
		SpriteService.getInstance();

		/****************************************
		 * Camera and viewport to draw fit image
		 ****************************************/
		screenCamera = new OrthographicCamera();
		screenCamera.position.set(Constante.SCREEN_SIZE_X / 2, Constante.SCREEN_SIZE_Y / 2, 0);
		screenCamera.update();
		viewport = new FitViewport(Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, screenCamera);
		viewport.apply();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(screenCamera.combined);
		accountService = new AccountService();
		levelService = new LevelService();
		notificationService = new NotificationService(this);
		menuInputProcessor = new MenuInputProcessor();
		SoundService.getInstance().playMusic(MusicEnum.BOSS2);
		Gdx.input.setInputProcessor(menuInputProcessor);
		this.setScreen(new SplashScreen(this));

	}

	public void dispose() {
		batch.dispose();
	}
}
