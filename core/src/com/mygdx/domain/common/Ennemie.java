package com.mygdx.domain.common;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.constante.CollisionConstante;
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

	protected static final float ENNEMIE_BOX_HEIGHT = 0.4f;
	protected static final float ENNEMIE_BOX_WIDTH = 0.4f;

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
	
	@Override
	public void createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x + 0.5f, y + 0.5f);
		body = world.createBody(bodyDef);
		body.setFixedRotation(true);
		MassData data = new MassData();
		data.mass = 100f;
		body.setMassData(data);
		body.setUserData(this);
		PolygonShape bodyBox = new PolygonShape();
		bodyBox.setAsBox(ENNEMIE_BOX_WIDTH, ENNEMIE_BOX_HEIGHT);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = bodyBox;
		fixtureDef.density = 1;
		fixtureDef.restitution = 0f;
		Fixture fixture = body.createFixture(fixtureDef);
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_ENNEMIE;
		fixture.setFilterData(filter);
		bodyBox.dispose();
	}
}
