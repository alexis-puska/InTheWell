package com.mygdx.domain.common;

import com.mygdx.game.InTheWellGame;

public abstract class Drawable {

	protected InTheWellGame game;

	public void init(InTheWellGame game) {
		this.game = game;
	}

	abstract public void enable();

	abstract public void disable();

	abstract public void drawIt();
}
