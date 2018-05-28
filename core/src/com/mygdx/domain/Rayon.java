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
import com.mygdx.enumeration.RayonTypeEnum;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rayon extends BodyAble {
	private int x;
	private int y;
	private int length;
	private RayonTypeEnum type;
	private boolean vertical;

	private boolean f;

	@Override
	public void enable() {
		this.enable = true;
		createBody();
	}

	@Override
	public void disable() {
		dispose();
	}

	@Override
	public void drawIt() {
		if (enable) {
			TextureRegion rayon = null;
			if (f) {
				rayon = SpriteService.getInstance().getTexture("rayon", type.getIndex());
				f = false;
			} else {
				rayon = SpriteService.getInstance().getTexture("rayon", type.getIndex() + 1);
				f = true;
			}
			if (vertical) {
				game.getBatch().draw(rayon, x * Constante.GRID_BLOC_SIZE, y * Constante.GRID_BLOC_SIZE, 20,
						length * Constante.GRID_BLOC_SIZE);
			} else {
				game.getBatch().draw(rayon.getTexture(), x * Constante.GRID_BLOC_SIZE, y * Constante.GRID_BLOC_SIZE, 10,
						10, Constante.GRID_BLOC_SIZE, Constante.GRID_BLOC_SIZE * length, 1, 1, 270, rayon.getRegionX(),
						rayon.getRegionY(), Constante.GRID_BLOC_SIZE, Constante.GRID_BLOC_SIZE, false, false);
			}
		}
	}

	@Override
	public void createBody() {
		if (enable) {
			BodyDef groundBodyDef = new BodyDef();
			PolygonShape groundBox = new PolygonShape();
			float halfLength = (float) length / 2.0f;
			float xb;
			float yb;
			if (vertical) {
				xb = x + 0.5f;
				yb = y + halfLength;
				groundBox.setAsBox(0.1f, halfLength);
			} else {
				xb = x + halfLength;
				yb = y + 0.5f;
				groundBox.setAsBox(halfLength, 0.1f);
			}
			groundBodyDef.position.set(new Vector2(xb, yb));
			body = world.createBody(groundBodyDef);
			Fixture fixture = body.createFixture(groundBox, 0.0f);
			body.setUserData(this);
			groundBox.dispose();
			Filter filter = new Filter();
			filter.categoryBits = CollisionConstante.CATEGORY_PICK;
			fixture.setFilterData(filter);
			fixture.setFriction(0.1f);
		}
	}
}
