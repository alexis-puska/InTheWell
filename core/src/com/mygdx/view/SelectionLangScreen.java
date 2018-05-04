package com.mygdx.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.mygdx.constante.Constante;
import com.mygdx.game.InTheWellGame;
import com.mygdx.utils.AxisUtils;

public class SelectionLangScreen implements Screen {

	final InTheWellGame game;

	public Texture img;
	private FrameBuffer fb;

	public SelectionLangScreen(final InTheWellGame game) {
		this.game = game;
		this.img = new Texture(Gdx.files.internal("sprite/sprite_menu.png"));
		this.fb = new FrameBuffer(Format.RGB888, 420, 520, false); // test

	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			game.getScreen().dispose();
			game.setScreen(new SplashScreen(game));
		}
		// if ( Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) &&
		// Gdx.input.isKeyJustPressed(Keys.F)) {
		// if (Gdx.graphics.isFullscreen()) {
		// Gdx.graphics.setWindowedMode(Constante.SCREEN_SIZE_X,
		// Constante.SCREEN_SIZE_Y);
		// } else {
		// DisplayMode currentMode = Gdx.graphics.getDisplayMode();
		// Gdx.graphics.setFullscreenMode(currentMode);
		// }
		// }

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		TextureRegion textureRegion = new TextureRegion(img, 128, 520);

		fb.begin();
		game.getBatch().begin();
		int idx = 0;
		while (idx < Constante.SCREEN_SIZE_X) {
			game.getBatch().draw(textureRegion, idx, AxisUtils.invert(0, textureRegion));
			idx += textureRegion.getRegionWidth();
		}
		game.getBatch().end();
		fb.end();

		game.getBatch().begin();
		TextureRegion fboRegion = new TextureRegion(fb.getColorBufferTexture());
		fboRegion.flip(false, true);
		game.getBatch().draw(fboRegion, 0, AxisUtils.invert(0, fb.getColorBufferTexture()));
		game.getBatch().end();
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
	public void dispose() {
		img.dispose();
	}

}
