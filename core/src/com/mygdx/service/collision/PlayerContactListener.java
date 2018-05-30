package com.mygdx.service.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
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

public class PlayerContactListener {

	public void beginContact(Contact contact, Fixture playerFixture, Fixture other) {
		Player player = (Player) playerFixture.getBody().getUserData();
		Body playerBody = playerFixture.getBody();
		if (other != null) {
			if (other.getBody().getUserData() != null) {
				if (other.getBody().getUserData().getClass() == Platform.class) {
					if (playerBody.getLinearVelocity().y == 0f) {
						long idFrame = Gdx.graphics.getFrameId();
						player.touchPlatorm(idFrame);
						Platform p = (Platform) other.getBody().getUserData();
						player.enterPlatform(p.getId());
					} else if (playerBody.getLinearVelocity().y > 0) {
						Platform p = (Platform) other.getBody().getUserData();
						if (p.getMin() < playerBody.getPosition().x && playerBody.getPosition().x < p.getMax()
								&& playerBody.getLinearVelocity().y > 0 && !player.isTouchPlatorm()) {
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
					if (event.isNear()) {
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
	}

	public void endContact(Contact contact, Fixture playerFixture, Fixture other) {
		Player player = (Player) playerFixture.getBody().getUserData();
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
					Gdx.app.log("END ITEM", "END ITEM : " + contact.getWorldManifold().getPoints().length);
				}
			}
		}
	}

	public void preSolve(Contact contact, Manifold oldManifold, Fixture playerFixture, Fixture other) {
		Player player = (Player) playerFixture.getBody().getUserData();
		Body playerBody = playerFixture.getBody();
		if (other != null) {
			if (other.getBody().getUserData() != null) {
				if (other.getBody().getUserData().getClass() == Platform.class) {
					if (playerBody.getLinearVelocity().y == 0f) {
						long idFrame = Gdx.graphics.getFrameId();
						player.touchPlatorm(idFrame);
						Platform p = (Platform) other.getBody().getUserData();
						player.enterPlatform(p.getId());
					}
					if (playerBody.getLinearVelocity().y > 0) {
						Platform p = (Platform) other.getBody().getUserData();
						if (p.getMin() < playerBody.getPosition().x && playerBody.getPosition().x < p.getMax()
								&& playerBody.getLinearVelocity().y > 0 && !player.isTouchPlatorm()) {
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
	}

	public void postSolve(Contact contact, ContactImpulse impulse, Fixture playerFixture, Fixture other) {
	}
}
