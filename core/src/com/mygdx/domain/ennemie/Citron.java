package com.mygdx.domain.ennemie;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.enumeration.EnnemieTypeEnum;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Citron extends Ennemie {

	public Citron() {
		super(EnnemieTypeEnum.CITRON);
	}

	@Override
	public void drawIt() {
		if (enable && body != null) {
			TextureRegion tmp = SpriteService.getInstance().getTexture(type.getName() + state.getStateName(),
					animationIndex / 2);
			animationIndexMax = SpriteService.getInstance().getAnimationSize(type.getName() + state.getStateName()) * 2;
			if (!tmp.isFlipX() && walkLeft) {
				tmp.flip(true, false);
			} else if (tmp.isFlipX() && !walkLeft) {
				tmp.flip(true, false);
			}
			game.getBatch().draw(tmp, (body.getPosition().x * Constante.GRID_BLOC_SIZE) - (tmp.getRegionWidth() / 2.0f),
					(body.getPosition().y * Constante.GRID_BLOC_SIZE)
							- (Constante.ENNEMIE_BOX_HEIGHT * Constante.GRID_BLOC_SIZE));
			animationIndex++;
			if (animationIndex >= animationIndexMax) {
				animationIndex = 0;
			}
		}
	}

	@Override
	public void think() {
		if (enable && body != null) {
			this.initView();
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
