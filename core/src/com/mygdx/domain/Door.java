package com.mygdx.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.constante.CollisionConstante;
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
public class Door extends BodyAble {
	private int x;
	private int y;
	private int type;
	private boolean locked;
	private int toLevel;
	private GameKeyEnum key;
	private Lock lock;

	@Override
	public void drawIt() {
		if (enable) {
			TextureRegion tmp = null;
			if (locked) {
				tmp = SpriteService.getInstance().getTexture("doors", type * 2);
			} else {
				tmp = SpriteService.getInstance().getTexture("doors", (type * 2) + 1);
			}
			game.getBatch().draw(tmp,
					(x * Constante.GRID_BLOC_SIZE) + Constante.GRID_BLOC_SIZE / 2 - (tmp.getRegionWidth() / 2.0f),
					y * Constante.GRID_BLOC_SIZE);
		}
		if (locked) {
			if (lock != null && lock.isEnable()) {
				locked = false;
			}
		}
	}

	@Override
	public void createBody() {
		if (enable) {
			BodyDef groundBodyDef = new BodyDef();
			PolygonShape groundBox = new PolygonShape();
			float xb = x + 0.5f;
			float yb = y + 0.5f;
			groundBodyDef.position.set(new Vector2(xb, yb));
			groundBox.setAsBox(0.5f, 0.5f);
			groundBodyDef.position.set(new Vector2(xb, yb));
			body = world.createBody(groundBodyDef);
			Fixture fixture = body.createFixture(groundBox, 0.0f);
			body.setUserData(this);
			groundBox.dispose();
			Filter filter = new Filter();
			filter.categoryBits = CollisionConstante.CATEGORY_DOOR;
			fixture.setFilterData(filter);
			fixture.setFriction(0.1f);
		}
	}

	public void unlock() {
		locked = false;
	}
}
