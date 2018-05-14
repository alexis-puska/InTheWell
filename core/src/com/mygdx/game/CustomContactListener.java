package com.mygdx.game;

import java.util.concurrent.locks.Lock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.domain.Door;
import com.mygdx.domain.Ennemie;
import com.mygdx.domain.Item;
import com.mygdx.domain.Pick;
import com.mygdx.domain.Platform;
import com.mygdx.domain.Player;
import com.mygdx.domain.Rayon;
import com.mygdx.domain.Teleporter;
import com.mygdx.domain.Vortex;

public class CustomContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {

	}

	@Override
	public void endContact(Contact contact) {

		int playerInvolve = playerInvolved(contact);
		Player player = null;
		Fixture other = null;

		/*********************************
		 * --- Player platform bottom ---
		 *********************************/
		if (playerInvolve != -1) {
			if (playerInvolve == 0) {
				player = (Player) contact.getFixtureA().getBody().getUserData();
				// float playerVelocity = contact.getFixtureA().getBody().getLinearVelocity().y;
				other = contact.getFixtureB();
			} else if (playerInvolve == 1) {
				player = (Player) contact.getFixtureB().getBody().getUserData();
				// float playerVelocity = contact.getFixtureB().getBody().getLinearVelocity().y;
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
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

		int playerInvolve = playerInvolved(contact);
		Player player = null;
		Body playerBody = null;
		float playerVelocity = 0f;
		Fixture other = null;

		/*********************************
		 * --- Player platform bottom ---
		 *********************************/
		if (playerInvolve != -1) {
			if (playerInvolve == 0) {
				player = (Player) contact.getFixtureA().getBody().getUserData();
				playerBody = contact.getFixtureA().getBody();
				playerVelocity = contact.getFixtureA().getBody().getLinearVelocity().y;
				other = contact.getFixtureB();
			} else if (playerInvolve == 1) {
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
				} else if (other.getBody().getUserData().getClass() == Ennemie.class) {
					Gdx.app.log("player touch ennemie", "kill player");
					contact.setEnabled(false);
				} else if (other.getBody().getUserData().getClass() == Door.class) {
				} else if (other.getBody().getUserData().getClass() == Item.class) {
				} else if (other.getBody().getUserData().getClass() == Lock.class) {
				} else if (other.getBody().getUserData().getClass() == Pick.class) {
				} else if (other.getBody().getUserData().getClass() == Rayon.class) {
				} else if (other.getBody().getUserData().getClass() == Teleporter.class) {
				} else if (other.getBody().getUserData().getClass() == Vortex.class) {
				}
			}
			return;
		} else {
			int ennemieInvolve = ennemiesInvolved(contact);
			Ennemie ennemie = null;
			Body ennemieBody = null;
			if (ennemieInvolve != -1) {
				if (ennemieInvolve == 0) {
					ennemie = (Ennemie) contact.getFixtureA().getBody().getUserData();
					ennemieBody = contact.getFixtureA().getBody();
					other = contact.getFixtureB();
				} else if (ennemieInvolve == 1) {
					ennemie = (Ennemie) contact.getFixtureB().getBody().getUserData();
					ennemieBody = contact.getFixtureB().getBody();
					other = contact.getFixtureA();
				}
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
		Gdx.app.log("player involved", "" + result);
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
				&& a.getBody().getUserData().getClass() == Ennemie.class) {
			result += 1;
		}
		if (b.getBody() != null && b.getBody().getUserData() != null
				&& b.getBody().getUserData().getClass() == Ennemie.class) {
			result += 2;
		}
		Gdx.app.log("ennemie involved", "" + result);
		return result;
	}
}
