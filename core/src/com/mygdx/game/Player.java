package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player implements ContactListener, InputProcessor {

	Body playerBody;

	public Player(World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(10, 10);
		playerBody = world.createBody(bodyDef);
		playerBody.setFixedRotation(true);

		PolygonShape bodyBox = new PolygonShape();
		bodyBox.setAsBox(0.2f, 0.4f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = bodyBox;
		fixtureDef.density = 2f;
		fixtureDef.friction = 0f;
		fixtureDef.restitution = 0f;
		playerBody.createFixture(fixtureDef);

		bodyBox.dispose();
	}

	@Override
	public void beginContact(Contact contact) {
		Gdx.app.log("Player", "beginContact");
	}

	@Override
	public void endContact(Contact contact) {
		Gdx.app.log("Player", "endContact");
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		Gdx.app.log("Player", "presolve");
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		Gdx.app.log("Player", "postsolve");

	}

	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode == Keys.LEFT) {
			playerBody.setLinearVelocity(-10f, playerBody.getLinearVelocity().y);
		}
		
		if(keycode == Keys.RIGHT) {
			playerBody.setLinearVelocity(10f, playerBody.getLinearVelocity().y);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.LEFT || keycode == Keys.RIGHT) {
			playerBody.setLinearVelocity(0, playerBody.getLinearVelocity().y);
		}
		Gdx.app.log("Player", "keyUp : " + keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
