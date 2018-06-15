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
public class Pomme extends Ennemie {

	public Pomme() {
		super();
		walkLeft = ThreadLocalRandom.current().nextInt(0, 10) % 2 == 0;
		touchPlatform = false;
	}

	@Override
	public void drawIt() {
		TextureRegion tmp = SpriteService.getInstance().getTexture("pomme_walk", 0);
		if (!tmp.isFlipX() && walkLeft) {
			tmp.flip(true, false);
		} else if (tmp.isFlipX() && !walkLeft) {
			tmp.flip(true, false);
		}
		game.getBatch().draw(tmp, (body.getPosition().x * Constante.GRID_BLOC_SIZE) - (tmp.getRegionWidth() / 2.0f),
				(body.getPosition().y * Constante.GRID_BLOC_SIZE)
						- (Constante.ENNEMIE_BOX_HEIGHT * Constante.GRID_BLOC_SIZE));
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
