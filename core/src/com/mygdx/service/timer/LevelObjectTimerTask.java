package com.mygdx.service.timer;

import java.util.TimerTask;

import com.mygdx.domain.Level;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LevelObjectTimerTask extends TimerTask {

	private boolean runned;
	private Level level;
	private int function;

	@Override
	public void run() {
		this.runned = true;
		switch (function) {
		case 1:
			level.addObjectPoint();
			break;
		case 0:
		default:
			level.addObjectEffet();
			break;

		}
	}
}
