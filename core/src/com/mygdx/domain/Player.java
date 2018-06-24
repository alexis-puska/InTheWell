package com.mygdx.domain;

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
import com.mygdx.enumeration.EventNotificationType;
import com.mygdx.enumeration.RayonTypeEnum;
import com.mygdx.enumeration.SoundEnum;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.SoundService;
import com.mygdx.service.SpriteService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author alexispuskarczyk
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player extends BodyAble {

	// PlayerType
	private boolean igor;

	// Button
	private boolean jumpPressed;
	private boolean walkLeftPressed;
	private boolean walkRightPressed;
	private boolean pushPressed;
	private boolean dropPressed;
	private boolean touchPlatormTop;

	private int destinationId;
	private Vector2 teleport;

	private BombeTypeEnum bombeType;
	private Level level;

	public Player(World world, InTheWellGame game, Level level, boolean igor) {
		init(world, game);
		this.level = level;
		this.igor = igor;
		this.destinationId = -1;
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
		bodyBox.setAsBox(Constante.PLAYER_BOX_WIDTH, Constante.PLAYER_BOX_HEIGHT);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = bodyBox;
		fixtureDef.density = 1;
		fixtureDef.restitution = 0f;
		Fixture fixture = body.createFixture(fixtureDef);
		fixture.setFriction(0f);
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_PLAYER;
		filter.maskBits = CollisionConstante.GROUP_PLAYER;
		fixture.setFilterData(filter);
		bodyBox.dispose();
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
				(body.getPosition().y * Constante.GRID_BLOC_SIZE)
						- (Constante.PLAYER_BOX_HEIGHT * Constante.GRID_BLOC_SIZE));
	}

	public void update() {
		if (teleport != null) {
			body.setTransform(teleport, body.getAngle());
			teleport = null;
		}

		if (walkRightPressed) {
			if (!isInsidePlatform()) {
				body.setLinearVelocity(Constante.PLAYER_WALK_RIGHT_VELOCITY, body.getLinearVelocity().y);
			} else {
				body.setLinearVelocity(0, body.getLinearVelocity().y);
			}
		} else if (walkLeftPressed) {
			if (!isInsidePlatform()) {
				body.setLinearVelocity(Constante.PLAYER_WALK_LEFT_VELOCITY, body.getLinearVelocity().y);
			} else {
				body.setLinearVelocity(0, body.getLinearVelocity().y);
			}
		}

		if (body.getLinearVelocity().y < Constante.PLAYER_NORMAL_FALL_VELOCITY) {
			body.setLinearVelocity(body.getLinearVelocity().x, Constante.PLAYER_NORMAL_FALL_VELOCITY);
		}

		if (touchPlatormTop && jumpPressed && !isInsidePlatform() && body.getLinearVelocity().y <= 0f) {
			body.setLinearVelocity(body.getLinearVelocity().x, Constante.PLAYER_JUMP_VELOCITY);
			touchPlatormTop = false;
		}

		if (body.getPosition().y < -10) {
			Vector2 pos = new Vector2(body.getPosition().x, 35.0f);
			body.setTransform(pos, body.getAngle());
		}
	}

	/****************************
	 * --- FUNCTION FOR LIGHT ---
	 ****************************/
	public int getX() {
		return (int) (body.getPosition().x * 20.0f);
	}

	public int getY() {
		return (int) (body.getPosition().y * 20.0f);
	}

	/**
	 * Change type of bombe when player touch a ray
	 * 
	 * @param rayonType
	 *            the ray type
	 */
	public void changeBombeType(RayonTypeEnum rayonType) {
		if (rayonType.getBombeType() != null && rayonType.getBombeType() != bombeType) {
			bombeType = rayonType.getBombeType();
			SoundService.getInstance().playSound(SoundEnum.CHANGETYPE);
		}
	}

	/**
	 * Kill the player
	 */
	public void kill() {
		SoundService.getInstance().playSound(SoundEnum.DEAD);
		level.notifyEvent(EventNotificationType.DEATH_PLAYER);
	}

	/**
	 * Apparition du joueur
	 */
	public void birth() {
		SoundService.getInstance().playSound(SoundEnum.DEAD);
		level.notifyEvent(EventNotificationType.BIRTH_PLAYER);
	}

	/**
	 * Unlock a lock with the correct key
	 * 
	 * @param lock
	 *            the lock to unlock
	 */
	public void unlockLock(final Lock lock) {
		long quantity = game.getAccountService().getFridgeQuantity(lock.getKey().getItemId());
		if (quantity > 0l) {
			lock.enable();
		}
	}

	/**
	 * Player unlock the door if he have the key
	 * 
	 * @param door
	 *            the door to unlock
	 */
	public void unlockDoor(final Door door) {
		if (door.getKey() != null) {
			long quantity = game.getAccountService().getFridgeQuantity(door.getKey().getItemId());
			if (quantity > 0l) {
				door.unlock();
			}
		}
	}

	/**
	 * The teleporter the teleporter in the collision with the player
	 * 
	 * @param teleporter
	 *            the teleporter
	 * @param points
	 *            all points generate by the collision
	 */
	public void teleporte(Teleporter teleporter, Vector2[] points) {
		if (teleport == null && destinationId == -1) {
			for (Teleporter tel : level.getTeleporter()) {
				if (tel.getId() == teleporter.getToId()) {
					float moyX = 0;
					float moyY = 0;
					for (int i = 0; i < points.length; i++) {
						moyX += points[i].x;
						moyY += points[i].y;
					}
					moyX = moyX / (float) points.length;
					moyY = moyY / (float) points.length;
					float diffX = moyX - (teleporter.getX());
					float diffY = moyY - (teleporter.getY());
					float posX = (tel.getX()) + diffX;
					float posY = (tel.getY()) + diffY;
					teleport = new Vector2(posX, posY);
					destinationId = teleporter.getToId();
					if (teleporter.isInvX()) {
						this.body.setLinearVelocity(-this.body.getLinearVelocity().x, this.body.getLinearVelocity().y);
					}
					if (teleporter.isInvY()) {
						this.body.setLinearVelocity(this.body.getLinearVelocity().x, -this.body.getLinearVelocity().y);
					}
					break;
				}
			}
			SoundService.getInstance().playSound(SoundEnum.TELEPORTER);
		}
	}

	/**
	 * Player go out of the destination teleporter
	 * 
	 * @param teleporter
	 *            the teleporter of the collision
	 */
	public void teleporteOut(Teleporter teleporter) {
		if (destinationId != -1 && !teleporter.getDestinations().contains(destinationId)) {
			destinationId = -1;
		}
	}

	/**
	 * Player notify change Level
	 * 
	 * @param level
	 *            the new Level
	 */
	public void enterLevel(Level level) {
		this.level = level;
		this.bombeType = BombeTypeEnum.WHITE;
	}

	/**
	 * Pick an item in level. Add this in temporary fridge for game
	 * 
	 * @param item
	 *            the Item
	 */
	public void pickItem(Item item) {
		if (game.getAccountService().addItemInGameFridge(item.getItemId())) {
			SoundService.getInstance().playSound(SoundEnum.TAKE_OBJECT);
		} else {
			SoundService.getInstance().playSound(SoundEnum.LETTEREFFECTOBJECT);
		}
		game.getNotificationService().addItemPickedNotification(item.getItemId(),
				game.getAccountService().getItemName(item.getItemId()), 200);
		item.disable();
	}

	/********************************************************
	 * --------------------- DIRECTION ---------------------
	 ********************************************************/

	/**
	 * Press left Button
	 */
	public void pressLeft() {
		walkLeftPressed = true;
	}

	/**
	 * Release left Button
	 */
	public void releaseLeft() {
		walkLeftPressed = false;
		if (walkRightPressed) {
			body.setLinearVelocity(Constante.PLAYER_WALK_RIGHT_VELOCITY, body.getLinearVelocity().y);
		} else {
			body.setLinearVelocity(0, body.getLinearVelocity().y);
		}
	}

	/**
	 * Press right Button
	 */
	public void pressRight() {
		walkRightPressed = true;
	}

	/**
	 * Relea se right Button
	 */
	public void releaseRight() {
		walkRightPressed = false;
		if (walkLeftPressed) {
			body.setLinearVelocity(Constante.PLAYER_WALK_LEFT_VELOCITY, body.getLinearVelocity().y);
		} else {
			body.setLinearVelocity(0, body.getLinearVelocity().y);
		}
	}

	/**
	 * Press Jump Button
	 */
	public void pressJump() {
		jumpPressed = true;
	}

	/**
	 * Release jump Button
	 */
	public void releaseJump() {
		jumpPressed = false;
	}

	/**
	 * Press drop Button
	 */
	public void pressDrop() {
		dropPressed = true;
	}

	/**
	 * Release drop Button
	 */
	public void releaseDrop() {
		dropPressed = false;
	}

	/**
	 * Press Push Button (down)
	 */
	public void pressPush() {
		pushPressed = true;
	}

	/**
	 * release Push Button (down)
	 */
	public void releasePush() {
		pushPressed = false;
	}

	public boolean isInsidePlatform() {
		Boolean[][] grid = level.getGrid();
		int xx = (int) body.getPosition().x;
		int yy = (int) body.getPosition().y;
		if (yy < 0 || yy > 24) {
			return true;
		}

		if (xx < 0 || xx > 19) {
			return true;
		}
		return grid[xx][yy];
	}

}
