package com.mygdx.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
public class Platform extends BodyAble {
	private int x;
	private int y;
	private boolean vertical;
	private boolean displayed;
	private int length;

	// commons

	private int verticalIndex;
	private int horizontalIndex;
	private boolean showPlatfomLevel;
	private ShapeRenderer shapeRenderer;
	private Level level;

	private Fixture fixture;

	public void init(World world, InTheWellGame game, int verticalIndex, int horizontalIndex, boolean showPlatfomLevel,
			final Level level) {
		this.shapeRenderer = new ShapeRenderer();
		this.showPlatfomLevel = showPlatfomLevel;
		this.world = world;
		this.game = game;
		this.verticalIndex = verticalIndex;
		this.horizontalIndex = horizontalIndex;
		this.level = level;
		super.init(world, game);
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
				groundBox.setAsBox(0.5f, halfLength);
			} else {
				xb = x + halfLength;
				yb = y + 0.5f;
				groundBodyDef.position.set(new Vector2(xb, yb));
				groundBox.setAsBox(halfLength, 0.5f);
			}
			
			groundBodyDef.position.set(new Vector2(xb, yb));

			body = world.createBody(groundBodyDef);
			fixture = body.createFixture(groundBox, 0.0f);
			fixture.setFriction(0f);
			body.setUserData(this);
			groundBox.dispose();
			Filter filter = new Filter();
			filter.categoryBits = CollisionConstante.CATEGORY_PLATFORM;
			fixture.setFilterData(filter);
		}
	}

	@Override
	public void drawIt() {
		if (showPlatfomLevel && displayed && enable) {
			if (vertical) {
				TextureRegion platformRegion = SpriteService.getInstance().getTexture("platform", verticalIndex);
				TextureRegion endPlatformRegion = SpriteService.getInstance().getTexture("end_platform", verticalIndex);
				game.getBatch().draw(platformRegion.getTexture(), x * Constante.GRID_BLOC_SIZE,
						y * Constante.GRID_BLOC_SIZE, 10, 10, length * Constante.GRID_BLOC_SIZE,
						Constante.GRID_BLOC_SIZE, 1, 1, 90, platformRegion.getRegionX(), platformRegion.getRegionY(),
						length * 20, 20, true, false);
				game.getBatch().draw(endPlatformRegion.getTexture(), (x + 1) * Constante.GRID_BLOC_SIZE,
						y * Constante.GRID_BLOC_SIZE, 0, 0, 6, Constante.GRID_BLOC_SIZE, 1, 1, 90,
						endPlatformRegion.getRegionX(), endPlatformRegion.getRegionY(), 6, Constante.GRID_BLOC_SIZE,
						true, false);
			} else {
				TextureRegion platformRegion = SpriteService.getInstance().getTexture("platform", horizontalIndex);
				TextureRegion endPlatformRegion = SpriteService.getInstance().getTexture("end_platform",
						horizontalIndex);
				game.getBatch().draw(platformRegion.getTexture(), x * Constante.GRID_BLOC_SIZE,
						y * Constante.GRID_BLOC_SIZE, platformRegion.getRegionX(), platformRegion.getRegionY(),
						(length * Constante.GRID_BLOC_SIZE) - 6, 20);
				game.getBatch().draw(endPlatformRegion.getTexture(), ((x + length - 1) * Constante.GRID_BLOC_SIZE) + 14,
						y * Constante.GRID_BLOC_SIZE, endPlatformRegion.getRegionX(), endPlatformRegion.getRegionY(), 6,
						Constante.GRID_BLOC_SIZE);
			}
		}
	}

	public void drawShadow() {
		if (showPlatfomLevel && displayed && enable) {
			game.getBatch().end();
			shapeRenderer.setProjectionMatrix(game.getBatch().getProjectionMatrix());
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(new Color(0f, 0f, 0f, 0.2f));
			if (vertical) {
				shapeRenderer.rect((x * Constante.GRID_BLOC_SIZE) + 6, y * Constante.GRID_BLOC_SIZE - 4, 18,
						(length * Constante.GRID_BLOC_SIZE) - 1);
			} else {
				shapeRenderer.rect((x * Constante.GRID_BLOC_SIZE) + 6, y * Constante.GRID_BLOC_SIZE - 4,
						(length * Constante.GRID_BLOC_SIZE) - 2, 19);
			}
			shapeRenderer.end();
			game.getBatch().begin();
		}
	}

	public void setFrixion(int iceValue) {
		float value = (1f / 255f) * iceValue;
		fixture.setFriction(value);
	}

	@Override
	public void update() {
		if (enable && body == null) {
			createBody();
			level.updateGrid(Boolean.TRUE, vertical, x, y, length);
		}
		if (!enable && body != null) {
			dispose();
			level.updateGrid(Boolean.FALSE, vertical, x, y, length);
		}
	}
}
