package com.mygdx.domain;

import com.mygdx.domain.common.Drawable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Decor extends Drawable {
	private int id;
	private int x;
	private int y;
	private boolean enable;
	private boolean back;
	private int indexAnim;

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
