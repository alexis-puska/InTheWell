package com.mygdx.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.BodyAble;
import com.mygdx.enumeration.GameKeyEnum;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lock extends BodyAble {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private GameKeyEnum key;

	@Override
	public void enable() {

	}

	@Override
	public void disable() {

	}

	@Override
	public void drawIt() {
		TextureRegion tmp = null;
		if (enable) {
			tmp = SpriteService.getInstance().getTexture("serrure", 1);
		} else {
			tmp = SpriteService.getInstance().getTexture("serrure", 0);
		}
		game.getBatch().draw(tmp,
				(x * Constante.GRID_BLOC_SIZE) + Constante.GRID_BLOC_SIZE / 2 - (tmp.getRegionWidth() / 2.0f),
				y * Constante.GRID_BLOC_SIZE);
	}

	@Override
	public void createBody() {

	}

}
