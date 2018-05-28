package com.mygdx.domain;

import com.badlogic.gdx.Gdx;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomTimerTask extends java.util.TimerTask {

	private boolean runned;
	private Level level;
	private int function;

	@Override
	public void run() {
		this.runned = true;
		switch (function) {
		case 1:
			Gdx.app.log("timer", "pop point");
			level.addObjectPoint();
			break;
		case 0:
		default:
			Gdx.app.log("timer", "pop effet");
			level.addObjectEffet();
			break;

		}
	}
}