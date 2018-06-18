package com.mygdx.domain;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.domain.common.BodyAble;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeleporterArea extends BodyAble {

	private int x;
	private int y;
	private int length;
	private boolean vertical;
	private Teleporter teleporter;

	@Override
	public void drawIt() {
		// Nothing to draw
	}

	@Override
	public void createBody() {
		if (enable) {
			BodyDef groundBodyDef = new BodyDef();
			PolygonShape groundBox = new PolygonShape();
			float halfLength = ((float) length) / 2.0f;
			float xb;
			float yb;
			if (vertical) {
				xb = x + 0.5f;
				yb = y + halfLength;
				groundBox.setAsBox(0.6f, halfLength + 0.5f);
			} else {
				xb = x + halfLength;
				yb = y + 0.5f;
				groundBox.setAsBox(halfLength + 0.5f, 0.6f);
			}
			groundBodyDef.position.set(new Vector2(xb, yb));
			body = world.createBody(groundBodyDef);
			Fixture fixture = body.createFixture(groundBox, 0.0f);
			body.setUserData(this);
			groundBox.dispose();
			Filter filter = new Filter();
			filter.categoryBits = CollisionConstante.CATEGORY_TELEPORTER;
			fixture.setFilterData(filter);
			fixture.setFriction(0.1f);
		}
	}

	@Override
	public void update() {
		if (enable && body == null) {
			createBody();
		}
		if (!enable && body != null) {
			dispose();
		}
	}

	public void playerNearest(boolean enter) {
		teleporter.setPlayerNearest(enter, true);
	}
}
