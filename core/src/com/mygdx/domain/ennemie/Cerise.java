package com.mygdx.domain.ennemie;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cerise extends Ennemie {

	public Cerise() {
		super();

	}

	@Override
	public void drawIt() {
		TextureRegion tmp = SpriteService.getInstance().getTexture("cerise_walk", 0);
		game.getBatch().draw(tmp, (body.getPosition().x * Constante.GRID_BLOC_SIZE) - (tmp.getRegionWidth() / 2.0f),
				(body.getPosition().y * Constante.GRID_BLOC_SIZE)
						- (Constante.ENNEMIE_BOX_HEIGHT * Constante.GRID_BLOC_SIZE));
	}

	@Override
	public void think() {
		this.initView();
		if (touchPlatform) {
			if (lastRequestAction == 3) {
				walkLeft = !walkLeft;
				body.applyForceToCenter(new Vector2(0, 600f), true);
				touchPlatform = false;
			}
			if (walkLeft) {
				body.setLinearVelocity(-5f, body.getLinearVelocity().y);
			} else {
				body.setLinearVelocity(5f, body.getLinearVelocity().y);
			}
		}
	}
}
