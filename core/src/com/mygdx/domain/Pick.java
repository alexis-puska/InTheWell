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
import com.mygdx.service.SpriteService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pick extends BodyAble {
	private int x;
	private int y;
	private int direction;

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
			TextureRegion platformRegion = SpriteService.getInstance().getTexture("pick", 1);
			switch (direction) {
			case 1:
				game.getBatch().draw(platformRegion.getTexture(), x * Constante.GRID_BLOC_SIZE,
						y * Constante.GRID_BLOC_SIZE, 10, 10, Constante.GRID_BLOC_SIZE, Constante.GRID_BLOC_SIZE, 1, 1,
						270, platformRegion.getRegionX(), platformRegion.getRegionY(), Constante.GRID_BLOC_SIZE,
						Constante.GRID_BLOC_SIZE, false, false);
				break;
			case 2:
				game.getBatch().draw(platformRegion.getTexture(), x * Constante.GRID_BLOC_SIZE,
						y * Constante.GRID_BLOC_SIZE, 10, 10, Constante.GRID_BLOC_SIZE, Constante.GRID_BLOC_SIZE, 1, 1,
						180, platformRegion.getRegionX(), platformRegion.getRegionY(), Constante.GRID_BLOC_SIZE,
						Constante.GRID_BLOC_SIZE, false, false);
				break;
			case 3:
				game.getBatch().draw(platformRegion.getTexture(), x * Constante.GRID_BLOC_SIZE,
						y * Constante.GRID_BLOC_SIZE, 10, 10, Constante.GRID_BLOC_SIZE, Constante.GRID_BLOC_SIZE, 1, 1,
						90, platformRegion.getRegionX(), platformRegion.getRegionY(), Constante.GRID_BLOC_SIZE,
						Constante.GRID_BLOC_SIZE, false, false);
				break;
			case 0:
			default:
				game.getBatch().draw(platformRegion, x * Constante.GRID_BLOC_SIZE, y * Constante.GRID_BLOC_SIZE);
				break;
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
			filter.categoryBits = CollisionConstante.CATEGORY_PICK;
			fixture.setFilterData(filter);
			fixture.setFriction(0.1f);
		}
	}
}
