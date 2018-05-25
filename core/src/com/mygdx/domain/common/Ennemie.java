package com.mygdx.domain.common;

import com.mygdx.enumeration.EnnemieStateEnum;
import com.mygdx.enumeration.EnnemieTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Ennemie extends BodyAble {
	private int id;
	protected boolean enable;
	protected int x;
	protected int y;
	protected EnnemieTypeEnum type;

	protected EnnemieStateEnum state;

	public boolean isDead() {
		return false;
	}

	public void kill() {

	}

	public void touchEnnemie(Ennemie o) {
		if (o.getState() == EnnemieStateEnum.ICED && o.getVelocity() > 5) {
			this.kill();
		}
	}

	private float getVelocity() {
		float vx = this.body.getLinearVelocity().x;
		float vy = this.body.getLinearVelocity().y;
		return (float) Math.sqrt((double) ((vx * vx) + (vy * vy)));
	}
}
