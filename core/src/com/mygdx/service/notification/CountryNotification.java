package com.mygdx.service.notification;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.constante.Constante;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.MessageService;

public class CountryNotification extends Notification {

	private int positionNewCountry = 56;
	private int positionName = 40;

	public CountryNotification(InTheWellGame game, String text, int timeout) {
		super(game, text, timeout);
	}

	@Override
	public boolean draw(GlyphLayout layout, BitmapFont font1, BitmapFont font2) {
		if (positionNewCountry > 0) {
			layout.setText(font2, MessageService.getInstance().getMessage("game.main.country.new"));
			font2.draw(game.getBatch(), layout, Constante.SCREEN_SIZE_X - layout.width, positionNewCountry);
			layout.setText(font1, text);
			font1.draw(game.getBatch(), layout, Constante.SCREEN_SIZE_X - layout.width, positionName);
		}
		if (timeout > 0) {
			timeout--;
		}
		if(timeout <= positionNewCountry + font2.getXHeight()) {
			positionNewCountry--;
			positionName--;
		}
		return timeout <=0;
	}
}
