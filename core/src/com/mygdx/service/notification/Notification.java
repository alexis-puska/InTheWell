package com.mygdx.service.notification;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.InTheWellGame;

public abstract class Notification {

	protected InTheWellGame game;
	protected String text;
	protected int timeout;

	public Notification(InTheWellGame game, String text, int timeout) {
		this.game = game;
		this.text = text;
		this.timeout = timeout;
	}

	public abstract boolean draw(GlyphLayout layout, BitmapFont font1, BitmapFont font2);

}
