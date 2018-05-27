package com.mygdx.service.notification;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.constante.Constante;
import com.mygdx.game.InTheWellGame;

public class ItemPickedNotification extends Notification {

	public ItemPickedNotification(InTheWellGame game, int objectId, String text, int timeout) {
		super(game, text, timeout);
	}

	@Override
	public boolean draw(GlyphLayout layout, BitmapFont font1, BitmapFont font2) {
		BitmapFont font = new BitmapFont(font1.getData(), font1.getRegion(), font1.usesIntegerPositions());
		if (timeout < 60) {
			font.setColor(1f, 1f, 1f, (1f / 60f) * (float) timeout);
		}
		layout.setText(font, text);
		font.draw(game.getBatch(), layout, (Constante.SCREEN_SIZE_X - layout.width) / 2f, 510);
		if (timeout > 0) {
			timeout--;
		}
		return timeout <= 0;
	}
}
