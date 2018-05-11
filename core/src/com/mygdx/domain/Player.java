package com.mygdx.domain;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.Body;
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

public class Player {

	private Body playerBody;
	private boolean igor;
	private boolean multi;

	private Set<Long> insidePlatform;
	private boolean touchPlatorm;

	public Player(World world, boolean igor, boolean multi) {
		BodyDef bodyDef = new BodyDef();
		this.igor = igor;
		this.multi = multi;
		this.insidePlatform = new HashSet<>();

		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(10, 10);
		playerBody = world.createBody(bodyDef);
		playerBody.setFixedRotation(true);
		MassData data = new MassData();
		data.mass = 100f;
		playerBody.setMassData(data);
		playerBody.setUserData(this);
		PolygonShape bodyBox = new PolygonShape();
		bodyBox.setAsBox(0.2f, 0.4f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = bodyBox;
		fixtureDef.density = 1;
		
		fixtureDef.restitution = 0f;

		Fixture fixture = playerBody.createFixture(fixtureDef);
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_PLAYER;
		filter.maskBits = CollisionConstante.GROUP_PLAYER;
		fixture.setFilterData(filter);
		bodyBox.dispose();
	}

	public void update() {
		treatInput();
		Gdx.app.log("InsidePlatformSize", insidePlatform + " blocks");
		if (playerBody.getLinearVelocity().y < Constante.PLAYER_NORMAL_FALL_VELOCITY) {
			playerBody.setLinearVelocity(playerBody.getLinearVelocity().x, Constante.PLAYER_NORMAL_FALL_VELOCITY);
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

	private void treatInput() {
		if (multi) {
			if (igor) {
				if (Gdx.input.isKeyPressed(Keys.LEFT)) {
					walkLeft();
				}
				if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
					walkRight();
				}
				if (!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
					stop();
				}
				if (Gdx.input.isKeyPressed(Keys.UP)) {
					jump();
				}
				if (Gdx.input.isKeyPressed(Keys.DOWN)) {
					push();
				}
				if (Gdx.input.isKeyPressed(Keys.ENTER)) {
					drop();
				}
			} else {
				if (Gdx.input.isKeyPressed(Keys.A)) {
					walkLeft();
				}
				if (Gdx.input.isKeyPressed(Keys.D)) {
					walkRight();
				}
				if (Gdx.input.isKeyPressed(Keys.W)) {
					jump();
				}
				if (Gdx.input.isKeyPressed(Keys.S)) {
					push();
				}
				if (Gdx.input.isKeyPressed(Keys.Q)) {
					drop();
				}
			}
		} else {
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				walkLeft();
			}
			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				walkRight();
			}
			if (Gdx.input.isKeyPressed(Keys.UP)) {
				jump();
			}
			if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				push();
			}
			if (Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)
					|| Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				drop();
			}
		}
	}

	public void enterPlatform(long platformId) {
		insidePlatform.add(platformId);
	}

	public void goOutPlatform(long platformId) {
		insidePlatform.remove(platformId);
	}

	private void walkLeft() {
		playerBody.setLinearVelocity(-10f, playerBody.getLinearVelocity().y);
	}

	private void walkRight() {
		playerBody.setLinearVelocity(10f, playerBody.getLinearVelocity().y);
	}

	private void stop() {
		playerBody.setLinearVelocity(0f, playerBody.getLinearVelocity().y);
	}

	private void jump() {
		if (touchPlatorm) {
			playerBody.setLinearVelocity(playerBody.getLinearVelocity().x, Constante.PLAYER_JUMP_VELOCITY);
		}
	}

	private void drop() {

	}

	private void push() {

	}
}
