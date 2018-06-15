package com.mygdx.domain.ennemie;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Kiwi extends Ennemie {

	public Kiwi() {
		super();
	}

	@Override
	public void drawIt() {
		TextureRegion tmp = SpriteService.getInstance().getTexture("kiwi_walk", 0);
		game.getBatch().draw(tmp, (body.getPosition().x * Constante.GRID_BLOC_SIZE) - (tmp.getRegionWidth() / 2.0f),
				(body.getPosition().y * Constante.GRID_BLOC_SIZE) - (Constante.ENNEMIE_BOX_HEIGHT * Constante.GRID_BLOC_SIZE));
	}
	
	@Override
	public void think() {
		this.initView();
		if (touchPlatform) {
			if (walkLeft) {
				body.setLinearVelocity(-5f, body.getLinearVelocity().y);
			} else {
				body.setLinearVelocity(5f, body.getLinearVelocity().y);
			}
		}
	}
}
