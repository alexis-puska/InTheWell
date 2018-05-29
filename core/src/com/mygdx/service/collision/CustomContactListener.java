package com.mygdx.service.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.domain.Door;
import com.mygdx.domain.Item;
import com.mygdx.domain.Lock;
import com.mygdx.domain.Pick;
import com.mygdx.domain.Platform;
import com.mygdx.domain.Player;
import com.mygdx.domain.Rayon;
import com.mygdx.domain.Teleporter;
import com.mygdx.domain.Vortex;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.domain.event.Event;
import com.mygdx.enumeration.EventNotificationType;

public class CustomContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
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
				if (other.getBody().getUserData() != null) {
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
					} else if (Ennemie.class.isAssignableFrom(other.getBody().getUserData().getClass())) {
						player.kill();
						Gdx.app.log("CUSTOM CONTACT LISTENER", "KILL PLAYER !");
					} else if (other.getBody().getUserData().getClass() == Door.class) {
						Door door = (Door) other.getBody().getUserData();
						player.unlockDoor(door);
					} else if (other.getBody().getUserData().getClass() == Event.class) {
						Event event = (Event) other.getBody().getUserData();
						if(event.isNear()) {
							event.enable(EventNotificationType.NEAR);
						}
					} else if (other.getBody().getUserData().getClass() == Item.class) {
						Item item = (Item) other.getBody().getUserData();
						player.pickItem(item);
					} else if (other.getBody().getUserData().getClass() == Lock.class) {
						Lock lock = (Lock) other.getBody().getUserData();
						player.unlockLock(lock);
					} else if (other.getBody().getUserData().getClass() == Pick.class) {
						player.kill();
					} else if (other.getBody().getUserData().getClass() == Rayon.class) {
						Rayon rayon = (Rayon) other.getBody().getUserData();
						player.changeBombeType(rayon.getType());
					} else if (other.getBody().getUserData().getClass() == Teleporter.class) {
						Teleporter teleporter = (Teleporter) other.getBody().getUserData();
						Vector2[] points = contact.getWorldManifold().getPoints();
						player.teleporte(teleporter, points);
					} else if (other.getBody().getUserData().getClass() == Vortex.class) {
					}
				}
			}
		} else {
			int ennemieInvolve = ennemiesInvolved(contact);
			if (ennemieInvolve != -1) {
				Ennemie ennemie = null;
				if (ennemieInvolve == 0) {
					ennemie = (Ennemie) contact.getFixtureA().getBody().getUserData();
					other = contact.getFixtureB();
				} else if (ennemieInvolve == 1) {
					ennemie = (Ennemie) contact.getFixtureB().getBody().getUserData();
					other = contact.getFixtureA();
				}
				if (other != null) {
					if (other.getBody().getUserData() != null) {
						if (other.getBody().getUserData().getClass() == Platform.class) {

						} else if (Ennemie.class.isAssignableFrom(other.getBody().getUserData().getClass())) {
							Ennemie o = (Ennemie) other.getBody().getUserData();
							ennemie.touchEnnemie(o);
						} else if (other.getBody().getUserData().getClass() == Pick.class) {
							ennemie.kill();
						}
					}
				}
			}

		}
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
				if (other.getBody().getUserData() != null) {
					if (other.getBody().getUserData().getClass() == Platform.class) {
						player.leavePlatorm();
						Platform p = (Platform) other.getBody().getUserData();
						player.goOutPlatform(p.getId());
					} else if (other.getBody().getUserData().getClass() == Teleporter.class) {
						Teleporter teleporter = (Teleporter) other.getBody().getUserData();
						player.teleporteOut(teleporter);
					} else if (other.getBody().getUserData().getClass() == Item.class) {
						Gdx.app.log("END ITEM", "END ITEM");
					}
					
					
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
				if (other.getBody().getUserData() != null) {
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
					} else if (Ennemie.class.isAssignableFrom(other.getBody().getUserData().getClass())) {
						contact.setEnabled(false);
					} else if (other.getBody().getUserData().getClass() == Door.class) {
						contact.setEnabled(false);
					} else if (other.getBody().getUserData().getClass() == Event.class) {
						contact.setEnabled(false);
					} else if (other.getBody().getUserData().getClass() == Item.class) {
						contact.setEnabled(false);
					} else if (other.getBody().getUserData().getClass() == Lock.class) {
						contact.setEnabled(false);
					} else if (other.getBody().getUserData().getClass() == Pick.class) {
						contact.setEnabled(false);
					} else if (other.getBody().getUserData().getClass() == Rayon.class) {
						contact.setEnabled(false);
					} else if (other.getBody().getUserData().getClass() == Teleporter.class) {
						contact.setEnabled(false);
					} else if (other.getBody().getUserData().getClass() == Vortex.class) {
						contact.setEnabled(false);
					}
				}
			}
		} else {
			int ennemieInvolve = ennemiesInvolved(contact);
			if (ennemieInvolve != -1) {
				if (ennemieInvolve == 0) {
					other = contact.getFixtureB();
				} else if (ennemieInvolve == 1) {
					other = contact.getFixtureA();
				}
				if (other != null) {
					if (other.getBody().getUserData() != null) {
						if (other.getBody().getUserData().getClass() == Platform.class) {

						} else if (Ennemie.class.isAssignableFrom(other.getBody().getUserData().getClass())) {
							contact.setEnabled(false);
						} else if (other.getBody().getUserData().getClass() == Pick.class) {
							contact.setEnabled(false);
						} else if (other.getBody().getUserData().getClass() == Player.class) {
							contact.setEnabled(false);
						}
					}
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