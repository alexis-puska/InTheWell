package com.mygdx.domain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
import lombok.Setter;

@Getter
@Setter
public class Teleporter extends BodyAble {

	private static final String BASE_TELEPORTER = "base_teleporter";

	private int x;
	private int y;
	private int length;
	private boolean vertical;
	private int toId;
	private List<Integer> destinations;

	private List<Teleporter> teleporter;
	private int lastDrawPixel;
	private boolean playerNearest;
	private TeleporterArea area;
	
	public void init(World world, InTheWellGame game, final List<Teleporter> teleporter) {
		this.teleporter = teleporter;
		this.init(world, game);
	}

	@Override
	public void drawIt() {
		TextureRegion tmp = SpriteService.getInstance().getTexture("teleporter", 1);
		if (playerNearest) {
			tmp = SpriteService.getInstance().getTexture("teleporter", 0);
		}
		int realSize = (length * Constante.GRID_BLOC_SIZE) - 10;
		if (vertical) {
			game.getBatch().draw(SpriteService.getInstance().getTexture(BASE_TELEPORTER, 2),
					x * Constante.GRID_BLOC_SIZE, (y + length - 1) * Constante.GRID_BLOC_SIZE);
			game.getBatch().draw(SpriteService.getInstance().getTexture(BASE_TELEPORTER, 3),
					x * Constante.GRID_BLOC_SIZE, y * Constante.GRID_BLOC_SIZE);
			game.getBatch().draw(tmp.getTexture(), x * Constante.GRID_BLOC_SIZE, y * Constante.GRID_BLOC_SIZE + 5,
					tmp.getRegionWidth(), realSize, tmp.getRegionX(), lastDrawPixel, tmp.getRegionWidth(), realSize,
					false, false);

		} else {
			game.getBatch().draw(SpriteService.getInstance().getTexture(BASE_TELEPORTER, 0),
					x * Constante.GRID_BLOC_SIZE, y * Constante.GRID_BLOC_SIZE);
			game.getBatch().draw(SpriteService.getInstance().getTexture(BASE_TELEPORTER, 1),
					(x + length - 1) * Constante.GRID_BLOC_SIZE, y * Constante.GRID_BLOC_SIZE);
			game.getBatch().draw(tmp.getTexture(), x * Constante.GRID_BLOC_SIZE + 5, y * Constante.GRID_BLOC_SIZE, 10,
					10, tmp.getRegionWidth(), realSize, 1, 1, 270, tmp.getRegionX(), tmp.getRegionY() + lastDrawPixel,
					tmp.getRegionWidth(), realSize, false, false);
		}
		lastDrawPixel += 16;
		if (lastDrawPixel > 110) {
			lastDrawPixel -= 94;
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
			filter.categoryBits = CollisionConstante.CATEGORY_TELEPORTER;
			fixture.setFilterData(filter);
			fixture.setFriction(0.1f);

			area = new TeleporterArea();
			area.setEnable(true);
			area.setId(0);
			area.setLength(length);
			area.setVertical(vertical);
			area.setX(x);
			area.setY(y);
			area.setTeleporter(this);
			area.init(world, game);
		}
	}

	@Override
	public void update() {
		if (enable && body == null) {
			createBody();
		}
		if (!enable && body != null) {
			dispose();
			area.dispose();
		}
		if (enable && ! playerNearest) {
			if (ThreadLocalRandom.current().nextInt(0, 10) < 5) {
				toId = destinations.get(ThreadLocalRandom.current().nextInt(0, destinations.size()));
			}
		}
	}

	public void dispose() {
		if (body != null) {
			this.world.destroyBody(body);
			body = null;
			area.dispose();
			area = null;
		}
	}
	
	public void setPlayerNearest(boolean playerNearest, boolean fromArea) {
		this.playerNearest = playerNearest;
		if(fromArea) {
			for(Teleporter t : teleporter) {
				if(t.getId() == toId) {
					t.setPlayerNearest(playerNearest, false);
				}
			}
		}
	}

}
