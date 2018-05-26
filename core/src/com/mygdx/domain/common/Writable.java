package com.mygdx.domain.common;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.InTheWellGame;

public abstract class Writable extends Drawable {

	protected GlyphLayout layout;
	protected BitmapFont fontWhite;
	protected BitmapFont smallFontWhite;

	public void init(InTheWellGame game, GlyphLayout layout, BitmapFont fontWhite, BitmapFont smallFontWhite) {
		this.init(game);
		this.layout = layout;
		this.fontWhite = fontWhite;
		this.smallFontWhite = smallFontWhite;

	}

	public abstract void update();

	public abstract void writeIt();
}
