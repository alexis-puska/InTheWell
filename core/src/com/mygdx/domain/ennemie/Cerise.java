package com.mygdx.domain.ennemie;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cerise extends Ennemie {

	private int animationIndex = 0;

	public Cerise() {
		super();

	}

	@Override
	public void drawIt() {
		TextureRegion tmp = SpriteService.getInstance().getTexture("cerise_angry", animationIndex/2);
		if(!tmp.isFlipX() && walkLeft) {
			tmp.flip(true, false);
		}else if(tmp.isFlipX() && !walkLeft) {
			tmp.flip(true, false);
		}
		game.getBatch().draw(tmp, (body.getPosition().x * Constante.GRID_BLOC_SIZE) - (tmp.getRegionWidth() / 2.0f),
				(body.getPosition().y * Constante.GRID_BLOC_SIZE)
						- (Constante.ENNEMIE_BOX_HEIGHT * Constante.GRID_BLOC_SIZE));
		animationIndex++;
		if (animationIndex >= 26) {
			animationIndex = 0;
		}
	}

	@Override
	public void think() {
		this.initView();
		if (touchPlatform) {
			if (lastRequestAction == 3) {
				walkLeft = !walkLeft;
			}
			if (touchPlatform) {
				if (walkLeft) {
					body.setLinearVelocity(-Constante.ENNEMIE_ANGRY_RUN, body.getLinearVelocity().y);
				} else {
					body.setLinearVelocity(Constante.ENNEMIE_ANGRY_RUN, body.getLinearVelocity().y);
				}
			}
		}
	}
}
