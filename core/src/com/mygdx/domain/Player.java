package com.mygdx.domain;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.BodyAble;
import com.mygdx.enumeration.BombeTypeEnum;
import com.mygdx.enumeration.RayonTypeEnum;
import com.mygdx.enumeration.SoundEnum;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.SoundService;
import com.mygdx.service.SpriteService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player extends BodyAble {

	private final float PLAYER_BOX_WIDTH = 0.2f;
	private final float PLAYER_BOX_HEIGHT = 0.4f;

	// PlayerType
	private boolean igor;

	// Button
	private boolean jumpPressed;
	private boolean walkLeftPressed;
	private boolean walkRightPressed;
	private boolean pushPressed;
	private boolean dropPressed;

	private Teleporter takeTeleporter;

	private Set<Long> insidePlatform;
	private BombeTypeEnum bombeType;
	private boolean touchPlatorm;
	private Level level;
	private Vector2 teleportPos;

	public Player(World world, InTheWellGame game, Level level, boolean igor) {
		init(world, game);
		this.level = level;
		this.igor = igor;
		this.insidePlatform = new HashSet<>();
	}

	@Override
	public void createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(10, 10);
		body = world.createBody(bodyDef);
		body.setFixedRotation(true);
		MassData data = new MassData();
		data.mass = 100f;
		body.setMassData(data);
		body.setUserData(this);
		PolygonShape bodyBox = new PolygonShape();
		bodyBox.setAsBox(PLAYER_BOX_WIDTH, PLAYER_BOX_HEIGHT);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = bodyBox;
		fixtureDef.density = 1;
		fixtureDef.restitution = 0f;
		Fixture fixture = body.createFixture(fixtureDef);
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_PLAYER;
		filter.maskBits = CollisionConstante.GROUP_PLAYER;
		fixture.setFilterData(filter);
		bodyBox.dispose();
	}

	@Override
	public void enable() {
	}

	@Override
	public void disable() {
	}

	@Override
	public void drawIt() {
		TextureRegion tmp;
		if (igor) {
			tmp = SpriteService.getInstance().getTexture("igor_right_wait", 0);
		} else {
			tmp = SpriteService.getInstance().getTexture("sandy_right_wait", 0);
		}
		game.getBatch().draw(tmp, (body.getPosition().x * Constante.GRID_BLOC_SIZE) - (tmp.getRegionWidth() / 2.0f),
				(body.getPosition().y * Constante.GRID_BLOC_SIZE) - (PLAYER_BOX_HEIGHT * Constante.GRID_BLOC_SIZE));
	}

	public void update() {
		Gdx.app.log("InsidePlatformSize", insidePlatform + " blocks");
		if (body.getLinearVelocity().y < Constante.PLAYER_NORMAL_FALL_VELOCITY) {
			body.setLinearVelocity(body.getLinearVelocity().x, Constante.PLAYER_NORMAL_FALL_VELOCITY);
		}
		if (touchPlatorm && jumpPressed) {
			body.setLinearVelocity(body.getLinearVelocity().x, Constante.PLAYER_JUMP_VELOCITY);
		}

		if (body.getPosition().y < -10) {
			Vector2 pos = new Vector2(body.getPosition().x, 35.0f);
			body.setTransform(pos, body.getAngle());
		}

		if (teleportPos != null) {
			body.setTransform(teleportPos, body.getAngle());
			teleportPos = null;
		}

	}

	public void touchPlatorm(long idFrame) {
		Gdx.app.log("touch platform : ", idFrame + "");
		touchPlatorm = true;
	}

	public boolean isTouchPlatorm() {
		return touchPlatorm;
	}

	public void leavePlatorm() {
		touchPlatorm = false;
	}

	public void enterPlatform(long platformId) {
		insidePlatform.add(platformId);
	}

	public void goOutPlatform(long platformId) {
		insidePlatform.remove(platformId);
	}

	public void walkLeft() {
		body.setLinearVelocity(-10f, body.getLinearVelocity().y);
	}

	public void walkRight() {
		body.setLinearVelocity(10f, body.getLinearVelocity().y);
	}

	public void stop() {
		body.setLinearVelocity(0f, body.getLinearVelocity().y);
	}

	public void jump() {
		jumpPressed = true;
		if (touchPlatorm) {
			body.setLinearVelocity(body.getLinearVelocity().x, Constante.PLAYER_JUMP_VELOCITY);
		}
	}

	public void jumpStop() {
		jumpPressed = false;
	}

	public void drop() {

	}

	public void push() {

	}

	public int getX() {
		return (int) (body.getPosition().x * 20.0f);
	}

	public int getY() {
		return (int) (body.getPosition().y * 20.0f);
	}

	public void changeBombeType(RayonTypeEnum rayonType) {
		if (rayonType.getBombeType() != null && rayonType.getBombeType() != bombeType) {
			bombeType = rayonType.getBombeType();
			SoundService.getInstance().playSound(SoundEnum.CHANGETYPE);
		}
	}

	public void kill() {

	}

	public void unlockLock(final Lock lock) {
		long quantity = game.getAccountService().getFridgeQuantity(lock.getKey().getItemId());
		if (quantity > 0l) {
			lock.enable();
		}
	}

	public void unlockDoor(final Door door) {
		if (door.getKey() != null) {
			long quantity = game.getAccountService().getFridgeQuantity(door.getKey().getItemId());
			if (quantity > 0l) {
				door.unlock();
			}
		}
	}

	public void teleporte(Teleporter teleporter) {
		if (takeTeleporter == null) {
			takeTeleporter = teleporter;
			teleporter.getToId();
			for (Teleporter tel : level.getTeleporter()) {
				if (tel.getId() == takeTeleporter.getToId()) {
					teleportPos = new Vector2(tel.getX(), tel.getY());
				}
			}
			SoundService.getInstance().playSound(SoundEnum.TELEPORTER);
		}
	}

	public void teleporteOut(Teleporter teleporter) {
		if (takeTeleporter.getToId() == teleporter.getId()) {
			takeTeleporter = null;
		}
	}

	public void changeLevel(Level level) {
		this.level = level;
	}
}
