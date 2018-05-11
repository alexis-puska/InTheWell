package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.domain.Ennemie;
import com.mygdx.domain.Platform;
import com.mygdx.domain.Player;

public class CustomContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {

	}

	@Override
	public void endContact(Contact contact) {

		int playerInvolve = playerInvolved(contact);
		Player player = null;
		float playerVelocity = 0f;
		Fixture other = null;

		/***************************
		 * --- Player each other ---
		 ***************************/
		// if (playerInvolve == 3) {
		// // Gdx.app.log("CustomContactListener", "player each other !");
		// contact.setEnabled(false);
		// return;
		// }

		/*********************************
		 * --- Player platform bottom ---
		 *********************************/
		if (playerInvolve == 1) {
			player = (Player) contact.getFixtureA().getBody().getUserData();
			playerVelocity = contact.getFixtureA().getBody().getLinearVelocity().y;
			other = contact.getFixtureB();
		} else if (playerInvolve == 2) {
			player = (Player) contact.getFixtureB().getBody().getUserData();
			playerVelocity = contact.getFixtureB().getBody().getLinearVelocity().y;
			other = contact.getFixtureA();
		}
		if (other != null) {
			if (other.getBody().getUserData().getClass() == Platform.class) {
				Gdx.app.log("player leave plaforme", "leave");
				player.leavePlatorm();
				Platform p = (Platform) other.getBody().getUserData();
				player.goOutPlatform(p.getId());
			}

		}

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

		int playerInvolve = playerInvolved(contact);
		Player player = null;
		Body playerBody = null;
		float playerVelocity = 0f;
		Fixture other = null;

		/***************************
		 * --- Player each other ---
		 ***************************/
		// if (playerInvolve == 3) {
		// // Gdx.app.log("CustomContactListener", "player each other !");
		// contact.setEnabled(false);
		// return;
		// }

		/*********************************
		 * --- Player platform bottom ---
		 *********************************/
		if (playerInvolve == 1) {
			player = (Player) contact.getFixtureA().getBody().getUserData();
			playerBody = contact.getFixtureA().getBody();
			playerVelocity = contact.getFixtureA().getBody().getLinearVelocity().y;
			other = contact.getFixtureB();
		} else if (playerInvolve == 2) {
			player = (Player) contact.getFixtureB().getBody().getUserData();
			playerBody = contact.getFixtureB().getBody();
			playerVelocity = contact.getFixtureB().getBody().getLinearVelocity().y;
			other = contact.getFixtureA();
		}
		if (other != null) {
			if (other.getBody().getUserData().getClass() == Platform.class) {
				if (playerVelocity == 0f) {
					long idFrame = Gdx.graphics.getFrameId();
					player.touchPlatorm(idFrame);
					Platform p = (Platform) other.getBody().getUserData();
					player.enterPlatform(p.getId());
				}
				if (playerVelocity > 0) {
					Platform p = (Platform) other.getBody().getUserData();
					if (p.getMin() < playerBody.getPosition().x && playerBody.getPosition().x < p.getMax()
							&& playerVelocity > 0 && !player.isTouchPlatorm()) {
						contact.setEnabled(false);
					}

				}
			}
			if (other.getBody().getUserData().getClass() == Ennemie.class) {
				Gdx.app.log("player touch ennemie", "kill player");
				contact.setEnabled(false);
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
		Gdx.app.log("player involved", "" + result);
		return result;
	}
}
