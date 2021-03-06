package com.mygdx.service.input_processor;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.domain.Player;
import com.mygdx.service.Context;
import com.mygdx.service.SoundService;
import com.mygdx.view.GameScreen;

public class GameInputProcessor implements InputProcessor {

	private Player p1;
	private Player p2;
	private final GameScreen gameScreen;
	private boolean multi;

	private boolean ctrlPressed;
	private boolean shiftPressed;
	private boolean kPressed;

	public GameInputProcessor(Player p1, Player p2, GameScreen gameScreen) {
		this.p1 = p1;
		this.p2 = p2;
		this.gameScreen = gameScreen;
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
				p1.pressLeft();
				break;
			case Keys.RIGHT:
				p1.pressRight();
				break;
			case Keys.UP:
				p1.pressJump();
				break;
			case Keys.DOWN:
				p1.pressPush();
				break;
			case Keys.ENTER:
				p1.pressDrop();
				break;
			// P2
			case Keys.Q:
				p2.pressLeft();
				break;
			case Keys.S:
				p2.pressRight();
				break;
			case Keys.D:
				p2.pressJump();
				break;
			case Keys.Z:
				p2.pressPush();
				break;
			case Keys.A:
				p2.pressDrop();
				break;
			default:
			}
		} else {
			switch (keycode) {
			case Keys.LEFT:
				p1.pressLeft();
				break;
			case Keys.RIGHT:
				p1.pressRight();
				break;
			case Keys.UP:
				p1.pressJump();
				break;
			case Keys.DOWN:
				p1.pressPush();
				break;
			case Keys.SPACE:
				p1.pressDrop();
				break;
			default:
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
		default:
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (multi) {
			switch (keycode) {
			// P1
			case Keys.LEFT:
				p1.releaseLeft();
				break;
			case Keys.RIGHT:
				p1.releaseRight();
				break;
			case Keys.UP:
				p1.releaseJump();
				break;
			case Keys.DOWN:
				p1.releasePush();
				break;
			case Keys.ENTER:
				p1.releaseDrop();
				break;
			// P2
			case Keys.Q:
				p2.releaseLeft();
				break;
			case Keys.S:
				p2.releaseRight();
				break;
			case Keys.D:
				p2.releaseJump();
				break;
			case Keys.Z:
				p2.releasePush();
				break;
			case Keys.A:
				p2.releaseDrop();
				break;
			default:

			}

		} else {
			switch (keycode) {
			case Keys.LEFT:
				p1.releaseLeft();
				break;
			case Keys.RIGHT:
				p1.releaseRight();
				break;
			case Keys.UP:
				p1.releaseJump();
				break;
			case Keys.DOWN:
				p1.releasePush();
				break;
			case Keys.SPACE:
				p1.releaseDrop();
				break;
			default:
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
		default:
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
		if (Context.isPlaySound()) {
			SoundService.getInstance().stopMusic();
			Context.setPlaySound(false);
		} else {
			SoundService.getInstance().playLastMusic();
			Context.setPlaySound(true);
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
