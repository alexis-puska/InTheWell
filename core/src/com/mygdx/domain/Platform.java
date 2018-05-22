package com.mygdx.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Platform implements Drawable {

	private long id;
	private boolean enable;
	private int x;
	private int y;
	private boolean vertical;
	private boolean displayed;
	private int length;

	// player collision
	private float min;
	private float max;
	private Body body;

	private World world;
	private InTheWellGame game;
	private int verticalIndex;
	private int horizontalIndex;

	public void init(World world, InTheWellGame game, int verticalIndex, int horizontalIndex) {
		this.verticalIndex = verticalIndex;
		this.world = world;
		this.game = game;
		this.verticalIndex = verticalIndex;
		this.horizontalIndex = horizontalIndex;
		BodyDef groundBodyDef = new BodyDef();
		PolygonShape groundBox = new PolygonShape();

		float halfLength = (float) length / 2.0f;
		float xb = 0f;
		float yb = 0f;
		if (vertical) {
			xb = x + 0.5f;
			yb = y + halfLength;
			groundBox.setAsBox(0.5f, halfLength);
		} else {
			xb = x + halfLength;
			yb = y + 0.5f;
			groundBodyDef.position.set(new Vector2(xb, yb));
			groundBox.setAsBox(halfLength, 0.5f);
		}
		groundBodyDef.position.set(new Vector2(xb, yb));

		body = world.createBody(groundBodyDef);
		Fixture fixture = body.createFixture(groundBox, 0.0f);
		if (vertical) {
			min = groundBodyDef.position.x - 0.5f;
			max = groundBodyDef.position.x + 0.5f;
			body.setUserData(this);
		} else {
			min = groundBodyDef.position.x - (length / 2);
			max = groundBodyDef.position.x + (length / 2);
			body.setUserData(this);
		}
		groundBox.dispose();
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_PLATFORM;
		fixture.setFilterData(filter);
		fixture.setFriction(0.1f);
	}

	public void dispose() {
		world.destroyBody(body);
	}

	@Override
	public void drawIt() {

		if (vertical) {
			// game.getBatch().draw(SpriteService.getInstance().getTexture("platform",
			// verticalIndex), x * 20, y * 20, 0,
			// 0, 20, length * 20, 1, 1, 90, true);
		} else {
			TextureRegion tmp = SpriteService.getInstance().getTexture("platform", horizontalIndex);
			game.getBatch().draw(tmp.getTexture(), x * 20, y * 20, tmp.getRegionX(), tmp.getRegionY(), length * 20, 20);
		}
	}

}
