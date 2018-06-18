package com.mygdx.service.collision;

import com.badlogic.gdx.math.Vector2;
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
import com.mygdx.domain.TeleporterArea;
import com.mygdx.domain.Vortex;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.domain.event.Event;
import com.mygdx.enumeration.EventNotificationType;

public class PlayerContactListener {

	public void beginContact(Contact contact, Fixture playerFixture, Fixture other) {
		Player player = (Player) playerFixture.getBody().getUserData();
		if (other != null) {
			if (other.getBody().getUserData() != null) {
				if (other.getBody().getUserData().getClass() == Platform.class) {
					defineContactPlayerToPlatform(contact, playerFixture, true);
				} else if (Ennemie.class.isAssignableFrom(other.getBody().getUserData().getClass())) {
					player.kill();
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
				} else if (other.getBody().getUserData().getClass() == TeleporterArea.class) {
					TeleporterArea area = (TeleporterArea)other.getBody().getUserData();
					area.playerNearest(true);
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
					defineContactPlayerToPlatform(contact, playerFixture, false);
				} else if (other.getBody().getUserData().getClass() == Teleporter.class) {
					Teleporter teleporter = (Teleporter) other.getBody().getUserData();
					player.teleporteOut(teleporter);
				}  else if (other.getBody().getUserData().getClass() == TeleporterArea.class) {
					TeleporterArea area = (TeleporterArea)other.getBody().getUserData();
					area.playerNearest(false);
				}
			}
		}
	}

	public void preSolve(Contact contact, Manifold oldManifold, Fixture playerFixture, Fixture other) {
		if (other != null) {
			if (other.getBody().getUserData() != null) {
				if (other.getBody().getUserData().getClass() == Platform.class) {
					definePresolveContactPlayerToPlatform(contact, playerFixture);
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
				} else if (other.getBody().getUserData().getClass() == TeleporterArea.class) {
					contact.setEnabled(false);
				} else if (other.getBody().getUserData().getClass() == Vortex.class) {
					contact.setEnabled(false);
				}
			}
		}
	}

	public void postSolve(Contact contact, ContactImpulse impulse, Fixture playerFixture, Fixture other) {
	}

	private void definePresolveContactPlayerToPlatform(Contact contact, Fixture playerFixture) {
		Player player = (Player) playerFixture.getBody().getUserData();
		if (contact.getWorldManifold().getPoints().length == 2) {
			Vector2[] points = contact.getWorldManifold().getPoints();
			float y0 = points[0].y;
			float y1 = points[1].y;
			if (y0 == y1) {
				if (playerFixture.getBody().getLinearVelocity().y == 0) {
					player.setTouchPlatormTop(true);
				}
			}
		}
	}

	private void defineContactPlayerToPlatform(Contact contact, Fixture playerFixture, boolean begin) {
		Player player = (Player) playerFixture.getBody().getUserData();
		if (contact.getWorldManifold().getPoints().length == 2) {
			Vector2[] points = contact.getWorldManifold().getPoints();
			float y0 = points[0].y;
			float y1 = points[1].y;
			if (y0 == y1) {
				player.setTouchPlatormTop(begin);
			}
		}
	}
}
