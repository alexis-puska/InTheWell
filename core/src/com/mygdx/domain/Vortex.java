package com.mygdx.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.BodyAble;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vortex extends BodyAble {
	private int x;
	private int y;
	private double zoomX;
	private double zoomY;
	private int destination;

	private static final String ANIMATION_NAME = "vortex";
	private int index;
	private int animationSize;

	@Override
	public void drawIt() {
		if (enable) {
			TextureRegion tmp = null;
			tmp = SpriteService.getInstance().getTexture(ANIMATION_NAME, index);
			game.getBatch().draw(tmp,
					(x * Constante.GRID_BLOC_SIZE) + Constante.GRID_BLOC_SIZE / 2 - (tmp.getRegionWidth() / 2.0f),
					y * Constante.GRID_BLOC_SIZE);
			index++;
			if (index >= animationSize) {
				index = 0;
			}
		}
	}

	public void init(World world, InTheWellGame game) {
		this.init(game);
		this.world = world;
		createBody();
		animationSize = SpriteService.getInstance().getAnimationSize(ANIMATION_NAME);
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
			filter.categoryBits = CollisionConstante.CATEGORY_VORTEX;
			fixture.setFilterData(filter);
			fixture.setFriction(0.1f);
		}
	}
}
