package com.mygdx.service.notification;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.InTheWellGame;

public class NeedItemNotification extends Notification {

	public NeedItemNotification(InTheWellGame game, String itemName, int timeout) {
		super(game, itemName, timeout);
	}

	@Override
	public boolean draw(GlyphLayout layout, BitmapFont font1, BitmapFont font2) {
		font1.getData().markupEnabled = true;
		layout.setText(font1, "[RED]My test string[]\n requise");
		font1.draw(game.getBatch(), layout, 10, 518);
		layout.setText(font2, "Passe partout de tuberculoz");
		font2.draw(game.getBatch(), layout, 10, 502);

		if (timeout > 0) {
			timeout--;
		}
		return timeout <= 0;
	}
}
