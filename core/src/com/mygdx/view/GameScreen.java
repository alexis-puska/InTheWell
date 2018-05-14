package com.mygdx.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.constante.Constante;
import com.mygdx.domain.Ennemie;
import com.mygdx.domain.Platform;
import com.mygdx.domain.Player;
import com.mygdx.enumeration.MusicEnum;
import com.mygdx.game.CustomContactListener;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.Context;
import com.mygdx.service.SpriteService;

public class GameScreen implements Screen {

	final InTheWellGame game;

	// TEXT
	private GlyphLayout layout;
	private BitmapFont fontGold;

	// DRAW
	private FrameBuffer backgroundLayer;
	private FrameBuffer platformLayer;
	private FrameBuffer playerLayer;
	private FrameBuffer frontLayer;
	private FrameBuffer shadowLayer;
	private FrameBuffer finalLayer;
	private ShapeRenderer shapeRenderer;

	private int x;
	private int y;

	private World world;
	// private Body body1;
	private List<Body> platform;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera gameCamera;
	int remainingJumpSteps;
	Player player;
	Player player2;

	public GameScreen(final InTheWellGame game) {

		platform = new ArrayList<>();
		remainingJumpSteps = 0;
		gameCamera = new OrthographicCamera(Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y);
		float camX = 10f;
		float camY = 11.5f;
		Gdx.app.log("cam ", camX + " " + camY);
		gameCamera.position.set(camX, camY, 0);
		gameCamera.zoom = 0.05f;
		gameCamera.update();
		this.game = game;
		this.shapeRenderer = new ShapeRenderer();
		this.layout = new GlyphLayout();
		this.backgroundLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y,
				false);
		this.platformLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.playerLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.frontLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.shadowLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.finalLayer = new FrameBuffer(Format.RGBA8888, Constante.SCREEN_SIZE_X, Constante.SCREEN_SIZE_Y, false);
		this.game.getSoundService().stopMusic();
		this.game.getSoundService().playMusic(MusicEnum.HAMMERFEST);

		x = 210;
		y = 210;
		this.initFont();

		world = new World(new Vector2(0, -60), true);
		world.step(1 / 60f, 6, 2);

		createGroundBody(1l, 0, 4, 3, false);
		createGroundBody(2l, 4, 4, 3, false);
		createGroundBody(3l, 0, 0, 20, false);
		createGroundBody(4l, -1, 40, 41, true);
		createGroundBody(5l, 20, 40, 41, true);
		createGroundBody(6l, 0, 2, 3, false);
		createGroundBody(7l, 0, 4, 3, false);
		createGroundBody(8l, 0, 6, 3, false);
		createGroundBody(9l, 0, 8, 3, false);
		createGroundBody(10l, 0, 10, 3, false);
		createGroundBody(11l, 0, 12, 3, false);
		createGroundBody(12l, 0, 14, 3, false);
		createGroundBody(13l, 0, 16, 3, false);
		createGroundBody(14l, 0, 18, 3, false);
		createGroundBody(15l, 0, 20, 3, false);
		createGroundBody(16l, 0, 22, 3, false);
		createEnnemieBody(5, 5, 0.2f, false);

		debugRenderer = new Box2DDebugRenderer();

		player = new Player(world, true, true);
		// player2 = new Player(world, false, true);

		world.setContactListener(new CustomContactListener());

	}

	public void createGroundBody(long id, float x, float y, float length, boolean vertical) {
		BodyDef groundBodyDef = new BodyDef();
		PolygonShape groundBox = new PolygonShape();
		if (vertical) {
			groundBodyDef.position.set(new Vector2(x + 0.5f, y - (length / 2) + 0.5f));
			groundBox.setAsBox(0.5f, length / 2);
		} else {
			groundBodyDef.position.set(new Vector2(x + (length / 2), y));
			groundBox.setAsBox(length / 2, 0.5f);
		}
		Body groundBody = world.createBody(groundBodyDef);
		Fixture fixture = groundBody.createFixture(groundBox, 0.0f);
		if (vertical) {
			groundBody.setUserData(
					new Platform(id, vertical, groundBodyDef.position.x - 0.5f, groundBodyDef.position.x + 0.5f));
		} else {
			groundBody.setUserData(new Platform(id, vertical, groundBodyDef.position.x - (length / 2),
					groundBodyDef.position.x + (length / 2)));
		}
		groundBox.dispose();
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_PLATFORM;
		fixture.setFilterData(filter);
		fixture.setFriction(0.1f);
		platform.add(groundBody);
	}

	public void createEnnemieBody(float x, float y, float length, boolean vertical) {
		BodyDef groundBodyDef = new BodyDef();
		PolygonShape groundBox = new PolygonShape();
		if (vertical) {
			groundBodyDef.position.set(new Vector2(x, y - (length / 2) + 0.5f));
			groundBox.setAsBox(0.5f, length / 2);
		} else {
			groundBodyDef.position.set(new Vector2(x + (length / 2) - 0.5f, y));
			groundBox.setAsBox(length / 2, 0.5f);
		}
		Body groundBody = world.createBody(groundBodyDef);
		Fixture fixture = groundBody.createFixture(groundBox, 0.0f);

		groundBody.setUserData(new Ennemie());
		groundBox.dispose();
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_PLATFORM;
		fixture.setFilterData(filter);
		platform.add(groundBody);
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
		// clear screen
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		drawBackground();
		drawPlatform();
		drawPlayer();
		drawFront();
		drawShadowMask(x, y);

		// merge 5 layers
		mergeFinalTexture();

		// draw finale image to screen
		game.getBatch().begin();
		game.getBatch().draw(finalLayer.getColorBufferTexture(), 0, 0);
		showFPS();
		game.getBatch().end();

		world.step(1 / 60f, 6, 2);
		treatInput();

		player.update();
		// player2.update();

		if (Constante.DEBUG) {
			debugRenderer.render(world, gameCamera.combined);
		}
	}

	/**
	 * Draw the final image to be renderer to the screen
	 */
	private void mergeFinalTexture() {
		finalLayer.begin();
		game.getBatch().begin();
		game.getBatch().draw(backgroundLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().draw(platformLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().draw(playerLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().draw(frontLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().draw(shadowLayer.getColorBufferTexture(), 0, 0);
		game.getBatch().end();
		finalLayer.end();
	}

	private void drawBackground() {
		TextureRegion textureRegionBackground = SpriteService.getInstance().getTexture("menu_background_1", 0);
		backgroundLayer.begin();
		game.getBatch().begin();
		int idx = 0;
		while (idx < Constante.SCREEN_SIZE_X) {
			game.getBatch().draw(textureRegionBackground, idx, 0);
			idx += textureRegionBackground.getRegionWidth();
		}
		game.getBatch().end();
		backgroundLayer.end();
	}

	private void drawPlatform() {
		platformLayer.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		platformLayer.end();
	}

	private void drawPlayer() {
		playerLayer.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		playerLayer.end();
	}

	private void drawFront() {
		frontLayer.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		frontLayer.end();
	}

	private void drawShadowMask(int x, int y) {
		shadowLayer.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0.7f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glColorMask(false, false, false, true);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0f, 0f, 0f, 0f));
		shapeRenderer.circle(x, y, 100);
		shapeRenderer.end();
		Gdx.gl.glColorMask(true, true, true, true);
		shadowLayer.end();
	}

	private void treatInput() {
		// CTRL + shift + Left -> exit game
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
				&& Gdx.input.isKeyJustPressed(Keys.K)) {
			game.getScreen().dispose();
			this.game.getSoundService().playMusic(MusicEnum.BOSS2);
			game.setScreen(new SelectOptionSoloScreen(game));
		}
		// display FPS
		if (Gdx.input.isKeyJustPressed(Keys.F)) {
			if (Context.isShowFps()) {
				Context.setShowFps(false);
			} else {
				Context.setShowFps(true);
			}
		}
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
	}

	private void showFPS() {
		if (Context.isShowFps()) {
			layout.setText(fontGold, Gdx.graphics.getFramesPerSecond() + " fps");
			fontGold.draw(game.getBatch(), layout, Constante.SCREEN_SIZE_X - layout.width, 510);
		}
	}
}
