package com.mygdx.domain;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {

	private Body playerBody;
	private boolean igor;
	private boolean multi;

	
	private boolean jumpPressed;

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
		Gdx.app.log("InsidePlatformSize", insidePlatform + " blocks");
		if (playerBody.getLinearVelocity().y < Constante.PLAYER_NORMAL_FALL_VELOCITY) {
			playerBody.setLinearVelocity(playerBody.getLinearVelocity().x, Constante.PLAYER_NORMAL_FALL_VELOCITY);
		}
		if (touchPlatorm && jumpPressed) {
			playerBody.setLinearVelocity(playerBody.getLinearVelocity().x, Constante.PLAYER_JUMP_VELOCITY);
		}

		if (playerBody.getPosition().y < -10) {
			Vector2 pos = new Vector2(playerBody.getPosition().x, 35.0f);
			playerBody.setTransform(pos, playerBody.getAngle());
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
		playerBody.setLinearVelocity(-10f, playerBody.getLinearVelocity().y);
	}

	public void walkRight() {
		playerBody.setLinearVelocity(10f, playerBody.getLinearVelocity().y);
	}

	public void stop() {
		playerBody.setLinearVelocity(0f, playerBody.getLinearVelocity().y);
	}

	public void jump() {
		jumpPressed = true;
		if (touchPlatorm) {
			playerBody.setLinearVelocity(playerBody.getLinearVelocity().x, Constante.PLAYER_JUMP_VELOCITY);
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
		return (int) (playerBody.getPosition().x * 20.0f);
	}

	public int getY() {
		return (int) (playerBody.getPosition().y * 20.0f);
	}
}
