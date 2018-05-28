package com.mygdx.domain.common;

import com.mygdx.game.InTheWellGame;

public abstract class Drawable extends Enableable {

	protected InTheWellGame game;

	public void init(InTheWellGame game) {
		this.game = game;
	}

	public abstract void drawIt();
}
