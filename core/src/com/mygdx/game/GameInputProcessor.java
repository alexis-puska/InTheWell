package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.domain.Player;
import com.mygdx.service.Context;
import com.mygdx.view.GameScreen;

public class GameInputProcessor implements InputProcessor {

	private Player p1;
	private Player p2;
	private final GameScreen gameScreen;
	private final InTheWellGame game;
	private boolean multi;

	private boolean playMusic;
	private boolean ctrlPressed;
	private boolean shiftPressed;
	private boolean kPressed;

	public GameInputProcessor(Player p1, Player p2, GameScreen gameScreen, InTheWellGame game) {
		this.p1 = p1;
		this.p2 = p2;
		this.gameScreen = gameScreen;
		this.game = game;
		this.playMusic = true;
		if (p2 != null) {
			multi = true;
		}
	}

	/***************************
	 * --- KEYBOARD PART ---
	 ***************************/
	@Override
	public boolean keyDown(int keycode) {
		/*****************
		 * PLAYER KEYCODE
		 *****************/
		if (multi) {
			switch (keycode) {
			// P1
			case Keys.LEFT:
				break;
			case Keys.RIGHT:
				break;
			case Keys.UP:
				break;
			case Keys.DOWN:
				break;
			case Keys.ENTER:
				break;
			// P2
			case Keys.Q:
				break;
			case Keys.S:
				break;
			case Keys.D:
				break;
			case Keys.Z:
				break;
			case Keys.A:
				break;
			}
		} else {
			switch (keycode) {
			case Keys.LEFT:
				break;
			case Keys.RIGHT:
				break;
			case Keys.UP:
				break;
			case Keys.DOWN:
				break;
			case Keys.SPACE:
				break;

			}
		}

		/*****************
		 * COMMON KEYCODE
		 *****************/

		switch (keycode) {
		// sequence to exit game
		case Keys.CONTROL_LEFT:
		case Keys.CONTROL_RIGHT:
			ctrlPressed = true;
			checkExitGame();
			break;
		case Keys.SHIFT_LEFT:
		case Keys.SHIFT_RIGHT:
			shiftPressed = true;
			checkExitGame();
			break;
		case Keys.K:
			kPressed = true;
			checkExitGame();
			break;
		case Keys.M:
			toogleMusic();
			break;
		case Keys.C:
			toogleShowMap();
			break;
		case Keys.P:
			tooglePause();
			break;
		case Keys.F:
			toogleDisplayFps();
			break;
		case Keys.G:
			gameScreen.decLevel();
			break;
		case Keys.H:
			gameScreen.incLevel();
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (multi) {
			switch (keycode) {
			// P1
			case Keys.LEFT:
				break;
			case Keys.RIGHT:
				break;
			case Keys.UP:
				break;
			case Keys.DOWN:
				break;
			case Keys.ENTER:
				break;
			// P2
			case Keys.Q:
				break;
			case Keys.S:
				break;
			case Keys.D:
				break;
			case Keys.Z:
				break;
			case Keys.A:
				break;
			}

		} else {
			switch (keycode) {
			case Keys.LEFT:
				break;
			case Keys.RIGHT:
				break;
			case Keys.UP:
				break;
			case Keys.DOWN:
				break;
			case Keys.SPACE:
				break;
			}
		}

		switch (keycode) {
		// sequence to exit game
		case Keys.CONTROL_LEFT:
		case Keys.CONTROL_RIGHT:
			ctrlPressed = false;
			break;
		case Keys.SHIFT_LEFT:
		case Keys.SHIFT_RIGHT:
			shiftPressed = false;
			break;
		case Keys.K:
			kPressed = false;
			break;
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/***************************
	 * --- TOUCHSCREEN PART ---
	 ***************************/
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	/***************************
	 * --- COMMONS PART ---
	 ***************************/

	/**
	 * Stop / play music
	 */
	private void toogleMusic() {
		Gdx.app.log("input processor game", "toogle music");
		if (playMusic) {
			game.getSoundService().stopMusic();
			playMusic = false;
		} else {
			game.getSoundService().playLastMusic();
			playMusic = true;
		}
	}

	/**
	 * Hide / show FPS
	 */
	private void toogleDisplayFps() {
		if (Context.isShowFps()) {
			Context.setShowFps(false);
		} else {
			Context.setShowFps(true);
		}
	}

	/**
	 * Hide / show map
	 */
	private void toogleShowMap() {
		if (Context.isShowMap()) {
			Context.setShowMap(false);
		} else {
			Context.setShowMap(true);
		}
	}

	/**
	 * Pause / Unpause the game
	 */
	private void tooglePause() {
		if (Context.isPause()) {
			Context.setPause(false);
		} else {
			Context.setPause(true);
		}
	}

	private void checkExitGame() {
		if (ctrlPressed && shiftPressed && kPressed) {
			gameScreen.dispose();
		}
	}
}
