package com.mygdx.service.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.domain.Player;
import com.mygdx.domain.common.Ennemie;

public class CustomContactListener implements ContactListener {

	private PlayerContactListener playerContactListener;
	private EnnemieContactListener ennemieContactListener;

	public CustomContactListener() {
		playerContactListener = new PlayerContactListener();
		ennemieContactListener = new EnnemieContactListener();
	}

	@Override
	public void beginContact(Contact contact) {
		int playerInvolve = playerInvolved(contact);
		int ennemieInvolve = ennemiesInvolved(contact);
		if (playerInvolve != -1) {
			if (playerInvolve == 0) {
				playerContactListener.beginContact(contact, contact.getFixtureA(), contact.getFixtureB());
			} else if (playerInvolve == 1) {
				playerContactListener.beginContact(contact, contact.getFixtureB(), contact.getFixtureA());
			}
		} else if (ennemieInvolve != -1) {

			if (ennemieInvolve == 0) {
				ennemieContactListener.beginContact(contact, contact.getFixtureA(), contact.getFixtureB());
			} else if (ennemieInvolve == 1) {
				ennemieContactListener.beginContact(contact, contact.getFixtureB(), contact.getFixtureA());
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		int playerInvolve = playerInvolved(contact);
		int ennemieInvolve = ennemiesInvolved(contact);
		if (playerInvolve != -1) {
			if (playerInvolve == 0) {
				playerContactListener.endContact(contact, contact.getFixtureA(), contact.getFixtureB());
			} else if (playerInvolve == 1) {
				playerContactListener.endContact(contact, contact.getFixtureB(), contact.getFixtureA());
			}
		} else if (ennemieInvolve != -1) {
			if (ennemieInvolve == 0) {
				ennemieContactListener.endContact(contact, contact.getFixtureA(), contact.getFixtureB());
			} else if (ennemieInvolve == 1) {
				ennemieContactListener.endContact(contact, contact.getFixtureB(), contact.getFixtureA());
			}
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		int playerInvolve = playerInvolved(contact);
		int ennemieInvolve = ennemiesInvolved(contact);
		if (playerInvolve != -1) {
			if (playerInvolve == 0) {
				playerContactListener.preSolve(contact, oldManifold, contact.getFixtureA(), contact.getFixtureB());
			} else if (playerInvolve == 1) {
				playerContactListener.preSolve(contact, oldManifold, contact.getFixtureB(), contact.getFixtureA());
			}
		} else if (ennemieInvolve != -1) {
			if (ennemieInvolve == 0) {
				ennemieContactListener.preSolve(contact, oldManifold, contact.getFixtureA(), contact.getFixtureB());
			} else if (ennemieInvolve == 1) {
				ennemieContactListener.preSolve(contact, oldManifold, contact.getFixtureB(), contact.getFixtureA());
			}
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

	/**
	 * @formatter:off
	 * @param contact the contact
	 * @return nombre of player involved : 
	 * 		-1 not with player
	 * 		0  player is fixture A
	 *      1  player is fixture B, 
	 * @formatter:on
	 */
	private int playerInvolved(Contact contact) {
		int result = -1;
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
	 * @formatter:off
	 * @param contact the contact
	 * @return nombre of player involved : 
	 * 		-1 not with player
	 * 		0  ennemie is fixture A
	 *      1  ennemie is fixture B
	 * @formatter:on
	 */
	private int ennemiesInvolved(Contact contact) {
		int result = -1;
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		if (a.getBody() != null && a.getBody().getUserData() != null
				&& Ennemie.class.isAssignableFrom(a.getBody().getUserData().getClass())) {
			result += 1;
		}
		if (b.getBody() != null && b.getBody().getUserData() != null
				&& Ennemie.class.isAssignableFrom(b.getBody().getUserData().getClass())) {
			result += 2;
		}
		return result;
	}
}
