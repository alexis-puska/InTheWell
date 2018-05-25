package com.mygdx.domain.common;

import com.mygdx.game.InTheWellGame;

public abstract class Drawable {

	protected InTheWellGame game;

	public void init(InTheWellGame game) {
		this.game = game;
	}

	public abstract void enable();

	public abstract void disable();

	public abstract void drawIt();
}