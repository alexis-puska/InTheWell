package com.mygdx.domain;

import com.mygdx.constante.Constante;
import com.mygdx.domain.common.Writable;
import com.mygdx.service.Context;
import com.mygdx.service.MessageService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelName extends Writable {
	private int id;
	private String lang;
	private String value;

	private int timeout = 200;
	private int positionNewCountry = 36;
	private int positionName = 20;

	@Override
	public void writeIt() {
		if (positionNewCountry > 0 && !value.equals("")) {
			if (lang.equals(Context.getLocale().getCode())) {
				layout.setText(smallFontWhite, MessageService.getInstance().getMessage("game.main.country.new"));
				smallFontWhite.draw(game.getBatch(), layout, Constante.SCREEN_SIZE_X - layout.width,
						positionNewCountry);
				layout.setText(fontWhite, value);
				fontWhite.draw(game.getBatch(), layout, Constante.SCREEN_SIZE_X - layout.width, positionName);
			}
		}
		this.update();
	}

	@Override
	public void update() {
		if (timeout > 0) {
			timeout--;
		} else {
			positionNewCountry--;
			positionName--;
		}
	}

	@Override
	public void enable() {
	}

	@Override
	public void disable() {
	}

	@Override
	public void drawIt() {
	}
}
