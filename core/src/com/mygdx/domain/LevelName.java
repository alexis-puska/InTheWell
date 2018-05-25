package com.mygdx.domain;

import com.mygdx.domain.common.Drawable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelName extends Drawable {
	private int id;
	private String lang;
	private String value;

	private int timeout = 200;
	private int positionNewCountry = 36;
	private int positionName = 20;

	@Override
	public void enable() {

	}

	@Override
	public void disable() {

	}

	@Override
	public void drawIt() {

	}

	public void update() {
		if (timeout > 0) {
			timeout--;
		} else {
			positionNewCountry--;
			positionName--;
		}
	}
}
