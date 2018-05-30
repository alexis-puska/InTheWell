package com.mygdx.domain.ennemie;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cerise extends Ennemie {

	private boolean walkLeft;

	public Cerise() {
		super();
		int rand = ThreadLocalRandom.current().nextInt(0, 10);
		walkLeft = rand % 2 == 0;
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
		if (isOnPlatformBorderLeft() && walkLeft) {
			walkLeft = false;
		}
		if (isOnPlatformBorderRight() && !walkLeft) {
			walkLeft = true;
		}

		if ((isTouchBorderLeft() || isTouchLeft()) && walkLeft) {
			walkLeft = false;
		}
		if ((isTouchBorderRight() || isTouchRight()) && !walkLeft) {
			walkLeft = true;
		}

		if (walkLeft) {
			body.setLinearVelocity(-5f, 0f);
		} else {
			body.setLinearVelocity(5f, 0f);
		}
	}

}
