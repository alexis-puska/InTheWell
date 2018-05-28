package com.mygdx.domain;

import com.mygdx.domain.common.Drawable;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Decor extends Drawable {
	private int x;
	private int y;
	private boolean back;
	private int indexAnim;

	@Override
	public void drawIt() {
		if (enable) {
			game.getBatch().draw(SpriteService.getInstance().getDecor(indexAnim), x, y);
		}
	}
}
