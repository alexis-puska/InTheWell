package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.domain.Platform;

public class CustomContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {

	}

	@Override
	public void endContact(Contact contact) {

		int playerInvolve = playerInvolved(contact);
		Player player = null;
		float playerVelocity = 0f;
		Class<?> other = null;

		/***************************
		 * --- Player each other ---
		 ***************************/
		if (playerInvolve == 3) {
			// Gdx.app.log("CustomContactListener", "player each other !");
			contact.setEnabled(false);
			return;
		}

		/*********************************
		 * --- Player platform bottom ---
		 *********************************/
		if (playerInvolve == 1) {
			player = (Player) contact.getFixtureA().getBody().getUserData();
			playerVelocity = contact.getFixtureA().getBody().getLinearVelocity().y;
			other = getClassOfFixture(contact.getFixtureB());
		} else if (playerInvolve == 2) {
			player = (Player) contact.getFixtureB().getBody().getUserData();
			playerVelocity = contact.getFixtureB().getBody().getLinearVelocity().y;
			other = getClassOfFixture(contact.getFixtureA());
		}
		if (other != null) {
			if (other == Platform.class) {
				Gdx.app.log("player leave plaforme", "leave");
				player.leavePlatorm();
			}
		}

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

		int playerInvolve = playerInvolved(contact);
		Player player = null;
		float playerVelocity = 0f;
		Class<?> other = null;

		/***************************
		 * --- Player each other ---
		 ***************************/
		if (playerInvolve == 3) {
			// Gdx.app.log("CustomContactListener", "player each other !");
			contact.setEnabled(false);
			return;
		}

		/*********************************
		 * --- Player platform bottom ---
		 *********************************/
		if (playerInvolve == 1) {
			player = (Player) contact.getFixtureA().getBody().getUserData();
			playerVelocity = contact.getFixtureA().getBody().getLinearVelocity().y;
			other = getClassOfFixture(contact.getFixtureB());
		} else if (playerInvolve == 2) {
			player = (Player) contact.getFixtureB().getBody().getUserData();
			playerVelocity = contact.getFixtureB().getBody().getLinearVelocity().y;
			other = getClassOfFixture(contact.getFixtureA());
		}
		if (other != null) {
			if (other == Platform.class) {
				if (playerVelocity == 0f) {
					long idFrame = Gdx.graphics.getFrameId();
					player.touchPlatorm(idFrame);
				}
			}
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		/*********************************
		 * --- Player platform bottom ---
		 *********************************/

	}

	/**
	 * @formatter:off
	 * @param contact the contact
	 * @return nombre of player involved : 
	 * 		0, not with player
	 * 		1 player is fixture A
	 *      2 player is fixture B, 
	 * 		3 is for the two fixture
	 * @formatter:on
	 */
	private int playerInvolved(Contact contact) {
		int result = 0;
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		if (a.getBody() != null && a.getBody().getUserData() != null
				&& a.getBody().getUserData().getClass() == Player.class) {
			result += 1;
		}
		if (b.getBody() != null && b.getBody().getUserData() != null
				&& b.getBody().getUserData().getClass() == Player.class) {
			result += 2;
		}
		return result;
	}

	/**
	 * @param fixtureB
	 *            the fixture
	 * @return the class of fixture
	 */
	private Class<?> getClassOfFixture(Fixture fixtureB) {
		if (fixtureB.getBody() != null && fixtureB.getBody().getUserData() != null) {
			return fixtureB.getBody().getUserData().getClass();
		}
		return null;
	}
}
