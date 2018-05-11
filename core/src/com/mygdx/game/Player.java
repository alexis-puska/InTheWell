package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

	Body playerBody;
	private boolean igor;
	private boolean multi;

	private boolean walk;
	private boolean left;
	private boolean insidePlatform;
	private boolean touchPlatorm;

	public Player(World world, boolean igor, boolean multi) {
		BodyDef bodyDef = new BodyDef();
		this.igor = igor;
		this.multi = multi;

		walk = false;
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
		fixtureDef.friction = 0f;
		fixtureDef.restitution = 0f;
		playerBody.createFixture(fixtureDef);
		bodyBox.dispose();
	}

	public void update() {
		// if (insidePlatform) {
		// Gdx.app.log("Player", "Player inside platform");
		// }
		// if (playerBody.getLinearVelocity().y == 0 && walk) {
		// if (left) {
		// playerBody.setLinearVelocity(playerBody.getLinearVelocity().x, 18.5f);
		// } else {
		// playerBody.setLinearVelocity(playerBody.getLinearVelocity().x, 18.5f);
		// }
		// }

		treatInput();

		if (playerBody.getLinearVelocity().y < -13) {
			playerBody.setLinearVelocity(playerBody.getLinearVelocity().x, -13);
		}
	}

	public void touchPlatorm(long idFrame) {
		Gdx.app.log("touch platform : ", idFrame+"");
		touchPlatorm = true;
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
			playerBody.setLinearVelocity(playerBody.getLinearVelocity().x, 23f);
		}
	}

	private void drop() {

	}

	private void push() {

	}
}
