package com.mygdx.service.notification;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.constante.Constante;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.SpriteService;

public class MessageNotification extends Notification {

	private int position = 336;

	public MessageNotification(InTheWellGame game, String text, int timeout) {
		super(game, text, timeout);
	}

	@Override
	public boolean draw(GlyphLayout layout, BitmapFont font1, BitmapFont font2) {

		game.getBatch().draw(SpriteService.getInstance().getTexture("message1", 0), 0, position);
		layout.setText(font1, text);
		font1.draw(game.getBatch(), layout, (Constante.SCREEN_SIZE_X - layout.width) / 2f, position + 54);
		if (timeout > 0) {
			timeout--;
		}
		if (position < 436) {
			position+=10;
		}

		return timeout <= 0;
	}
}
