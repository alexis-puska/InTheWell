package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.Constante;
import com.mygdx.domain.Level;
import com.mygdx.domain.Player;
import com.mygdx.enumeration.GameModeEnum;
import com.mygdx.enumeration.MusicEnum;
import com.mygdx.game.CustomContactListener;
import com.mygdx.game.GameInputProcessor;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.Context;
import com.mygdx.service.SoundService;
import com.mygdx.service.SpriteService;

public class GameScreen implements Screen {

	final InTheWellGame game;

	/********************
	 * --- TEXT ---
	 ********************/
	private GlyphLayout layout;
	private BitmapFont fontGold;
	private BitmapFont fontScore;

	/********************
	 * --- DRAW ---
	 ********************/
	// layout
	private FrameBuffer backgroundLayer;
	private FrameBuffer platformLayer;
	private FrameBuffer playerLayer;
	private FrameBuffer frontLayer;
	private FrameBuffer shadowLayer;
	private FrameBuffer finalLayer;
	private ShapeRenderer shapeRenderer;
	// camera
	private OrthographicCamera gridCamera;
	private OrthographicCamera gameCamera;

	/********************
	 * --- PHYSICS ---
	 ********************/
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera debugCamera;

	/********************
	 * --- LEVEL ---
	 ********************/
	private Level currentLevel;
	private int levelIndex;

	/********************
	 * --- PLAYER ---
	 ********************/
	private Player player;
	private Player player2;

	/********************
	 * --- INPUT ---
	 ********************/
	private GameInputProcessor gameInputProcessor;

	public GameScreen(final InTheWellGame game) {
		this.game = game;
		Context.setPause(false);
		Context.setShowFps(false);
		Context.setShowMap(false);

		/********************
		 * --- TEXT ---
		 ********************/
		this.layout = new GlyphLayout();
		this.initFont();

		/********************
		 * --- DRAW ---
		 ********************/
		this.backgroundLayer = new FrameBuffer(Format.RGBA8888, Constante.GAME_SCREEN_SIZE_X,
				Constante.GAME_SCREEN_SIZE_Y, false);
		this.platformLayer = new FrameBuffer(Format.RGBA8888, Constante.GRID_SCREEN_SIZE_X,
				Constante.GRID_SCREEN_SIZE_Y, false);
		this.playerLayer = new FrameBuffer(Format.RGBA8888, Constante.GRID_SCREEN_SIZE_X, Constante.GRID_SCREEN_SIZE_Y,
				false);
		this.frontLayer = new FrameBuffer(Format.RGBA8888, Constante.GAME_SCREEN_SIZE_X, Constante.GAME_SCREEN_SIZE_Y,
				false);
		this.shadowLayer = new FrameBuffer(Format.RGBA8888, Constante.GAME_SCREEN_SIZE_X, Constante.GAME_SCREEN_SIZE_Y,
				false);
		this.finalLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.shapeRenderer = new ShapeRenderer();

		this.gridCamera = new OrthographicCamera(Constante.GRID_SCREEN_SIZE_X, Constante.GRID_SCREEN_SIZE_Y);
		this.gridCamera.position.set(Constante.GRID_SCREEN_SIZE_X / 2, Constante.GRID_SCREEN_SIZE_Y / 2, 0);
		this.gridCamera.update();

		this.gameCamera = new OrthographicCamera(Constante.GAME_SCREEN_SIZE_X, Constante.GAME_SCREEN_SIZE_Y);
		this.gameCamera.position.set(Constante.GAME_SCREEN_SIZE_X / 2, Constante.GAME_SCREEN_SIZE_Y / 2, 0);
		this.gameCamera.update();

		/********************
		 * --- PHYSICS ---
		 ********************/
		this.debugRenderer = new Box2DDebugRenderer();
		this.debugCamera = new OrthographicCamera(Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y);
		float camX = 10f;
		float camY = 12f;
		this.debugCamera.position.set(camX, camY, 0);
		this.debugCamera.zoom = 0.05f;
		this.debugCamera.update();
		this.world = new World(new Vector2(0, -60), true);
		this.world.setContactListener(new CustomContactListener());
		this.world.step(1 / 60f, 6, 2);

		/********************
		 * --- LEVEL ---
		 ********************/
		currentLevel = game.getLevelService().getLevel(GameModeEnum.SOLO, levelIndex);
		currentLevel.init(world, game);

		/********************
		 * --- PLAYER ---
		 ********************/
		if (game.getAccountService().getGameModeSelected() == GameModeEnum.MULTI_COOPERATIF) {
			player = new Player(world, game, currentLevel, true);
			player2 = new Player(world, game, currentLevel, false);
		} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.SOLO) {
			player = new Player(world, game, currentLevel, true);
		}

		gameInputProcessor = new GameInputProcessor(player, player2, this);
		Gdx.input.setInputProcessor(gameInputProcessor);

		/********************
		 * --- INIT MUSIC ---
		 ********************/
		SoundService.getInstance().stopMusic();
		SoundService.getInstance().playMusic(MusicEnum.HAMMERFEST);
	}

	@Override
	public void dispose() {
		backgroundLayer.dispose();
		platformLayer.dispose();
		playerLayer.dispose();
		frontLayer.dispose();
		shadowLayer.dispose();
		finalLayer.dispose();
		fontGold.dispose();
		SoundService.getInstance().playMusic(MusicEnum.BOSS2);
		game.getMenuInputProcessor().reset();
		Gdx.input.setInputProcessor(game.getMenuInputProcessor());
		game.setScreen(new SelectOptionSoloScreen(game));
	}

	@Override
	public void show() {
		// unused method

	}

	@Override
	public void resize(int width, int height) {
		game.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// unused method
	}

	@Override
	public void resume() {
		// unused method
	}

	@Override
	public void hide() {
		// unused method
	}

	@Override
	public void render(float delta) {
		// clear screen
		game.getScreenCamera().update();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		drawBackground();
		drawPlatform();
		drawPlayer();
		drawFront();
		int x1 = player.getX();
		int y1 = player.getY();
		int x2 = -1;
		int y2 = -1;
		if (player2 != null) {
			x2 = player2.getX();
			y2 = player2.getY();
		}
		drawShadowMask(x1, y1, x2, y2);

		// merge 5 layers
		mergeFinalTexture();

		// draw finale image to screen

		game.getViewport().apply();
		game.getBatch().begin();
		game.getBatch().draw(finalLayer.getColorBufferTexture(), 0, 0, 420, 520);
		drawInformation();
		game.getBatch().end();

		world.step(1 / 60f, 6, 2);

		currentLevel.update();

		if (game.getAccountService().getGameModeSelected() == GameModeEnum.SOLO) {
			player.update();
		} else if (game.getAccountService().getGameModeSelected() == GameModeEnum.MULTI_COOPERATIF) {
			player.update();
			player2.update();
		}

		if (Constante.DEBUG) {
			debugRenderer.render(world, debugCamera.combined);
		}
	}

	/**
	 * Draw information (FPS, Message, score and border)
	 */
	private void drawInformation() {
		// message
		this.game.getNotificationService().update();
		// bottom border
		game.getBatch().draw(SpriteService.getInstance().getTexture("border_score", 0), 0, 0);
		// SCORE
		layout.setText(fontScore, Long.toString(game.getAccountService().getGameScore()));
		fontScore.draw(game.getBatch(), layout, Constante.SCREEN_SIZE_X - layout.width, layout.height + 5);
		// FPS
		if (Context.isShowFps()) {
			layout.setText(fontGold, Gdx.graphics.getFramesPerSecond() + " fps");
			fontGold.draw(game.getBatch(), layout, Constante.SCREEN_SIZE_X - layout.width, 510);
		}
	}

	/**
	 * Draw the final image to be renderer to the screen
	 */
	private void mergeFinalTexture() {
		finalLayer.begin();
		game.getBatch().setProjectionMatrix(game.getScreenCamera().combined);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().begin();
		game.getBatch().draw(backgroundLayer.getColorBufferTexture(), 0, 0, Constante.GAME_SCREEN_SIZE_X,
				Constante.GAME_SCREEN_SIZE_Y);
		game.getBatch().draw(platformLayer.getColorBufferTexture(), 10, 0, Constante.GRID_SCREEN_SIZE_X,
				Constante.GRID_SCREEN_SIZE_Y);
		game.getBatch().draw(playerLayer.getColorBufferTexture(), 10, 0, Constante.GRID_SCREEN_SIZE_X,
				Constante.GRID_SCREEN_SIZE_Y);
		game.getBatch().draw(frontLayer.getColorBufferTexture(), 0, 0, Constante.GAME_SCREEN_SIZE_X,
				Constante.GAME_SCREEN_SIZE_Y);
		game.getBatch().draw(shadowLayer.getColorBufferTexture(), 0, 0, Constante.GAME_SCREEN_SIZE_X,
				Constante.GAME_SCREEN_SIZE_Y);
		game.getBatch().end();
		finalLayer.end();

	}

	private void drawBackground() {
		backgroundLayer.begin();
		game.getBatch().setProjectionMatrix(gameCamera.combined);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().begin();
		currentLevel.drawBackground();
		game.getBatch().end();
		backgroundLayer.end();
		game.getBatch().setProjectionMatrix(game.getScreenCamera().combined);
	}

	private void drawPlatform() {
		platformLayer.begin();
		game.getBatch().setProjectionMatrix(gridCamera.combined);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().begin();
		currentLevel.drawOnPlatformLayer();
		game.getBatch().end();
		platformLayer.end();
		game.getBatch().setProjectionMatrix(game.getScreenCamera().combined);
	}

	private void drawPlayer() {
		playerLayer.begin();
		game.getBatch().setProjectionMatrix(gridCamera.combined);
		game.getBatch().begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		currentLevel.drawOnPlayerLayer();
		player.drawIt();
		if (player2 != null) {
			player2.drawIt();
		}
		game.getBatch().end();
		playerLayer.end();
		game.getBatch().setProjectionMatrix(game.getScreenCamera().combined);
	}

	private void drawFront() {
		frontLayer.begin();
		game.getBatch().setProjectionMatrix(gameCamera.combined);
		game.getBatch().begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().draw(SpriteService.getInstance().getTexture("border_left", 0), 0, 0);
		game.getBatch().draw(SpriteService.getInstance().getTexture("border_right", 0), 405, 0);
		currentLevel.drawOnFrontLayer();
		game.getBatch().end();
		frontLayer.end();
		game.getBatch().setProjectionMatrix(game.getScreenCamera().combined);
	}

	private void drawShadowMask(int x, int y, int x2, int y2) {
		shadowLayer.begin();
		game.getBatch().setProjectionMatrix(gameCamera.combined);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0.3f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glColorMask(false, false, false, true);
		shapeRenderer.setProjectionMatrix(gameCamera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0f, 0f, 0f, 0f));
		shapeRenderer.circle(x, y, 100);
		if (player2 != null) {
			shapeRenderer.circle(x2, y2, 100);
		}
		shapeRenderer.end();
		Gdx.gl.glColorMask(true, true, true, true);
		shadowLayer.end();
		game.getBatch().setProjectionMatrix(game.getScreenCamera().combined);
	}

	public void incLevel() {
		levelIndex++;
		currentLevel.dispose();
		game.getNotificationService().dispose();
		currentLevel = game.getLevelService().getLevel(GameModeEnum.SOLO, levelIndex);
		currentLevel.init(world, game);
		player.enterLevel(currentLevel);
	}

	public void decLevel() {
		levelIndex--;
		currentLevel.dispose();
		game.getNotificationService().dispose();
		currentLevel = game.getLevelService().getLevel(GameModeEnum.SOLO, levelIndex);
		currentLevel.init(world, game);
		player.enterLevel(currentLevel);
	}

	private void initFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_verdana.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 10;
		parameter.borderWidth = 0.1f;
		parameter.borderColor = new Color(255, 255, 0, 255);
		parameter.color = new Color(255, 255, 0, 255);
		fontGold = generator.generateFont(parameter);
		generator.dispose();

		generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_impact.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 14;
		parameter.borderWidth = 2f;
		parameter.borderColor = new Color(0.2f, 0.2f, 0.2f, 1f);
		parameter.color = new Color(1f, 1f, 1f, 1f);
		fontScore = generator.generateFont(parameter);

		generator.dispose();
	}
}
