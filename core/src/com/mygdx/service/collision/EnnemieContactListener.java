package com.mygdx.service.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.constante.Constante;
import com.mygdx.domain.Pick;
import com.mygdx.domain.Platform;
import com.mygdx.domain.Player;
import com.mygdx.domain.common.Ennemie;

public class EnnemieContactListener {

	public void beginContact(Contact contact, Fixture ennemieFixture, Fixture other) {
		Ennemie ennemie = (Ennemie) ennemieFixture.getBody().getUserData();
		if (other != null) {
			if (other.getBody().getUserData() != null) {
				if (other.getBody().getUserData().getClass() == Platform.class) {
					if (contact.getWorldManifold().getPoints().length == 2) {
						Vector2 p0 = contact.getWorldManifold().getPoints()[0];
						Vector2 p1 = contact.getWorldManifold().getPoints()[1];
						if (p0.x == p1.x) {
							if (p0.x < ennemieFixture.getBody().getPosition().x) {
								ennemie.setTouchLeft(true);
								ennemie.setTouchRight(false);
							} else {
								ennemie.setTouchRight(true);
								ennemie.setTouchLeft(false);
							}
						} else if (p0.y == p1.y) {
							if (Math.abs(p0.x - p1.x) < (Constante.ENNEMIE_BOX_WIDTH * 2f)
									&& ((p0.x + p1.x) / 2f) < ennemieFixture.getBody().getPosition().x && ennemie.isWalkLeft()) {
								ennemie.setOnPlatformBorderLeft(true);
							} else {
								ennemie.setOnPlatformBorderRight(true);
							}
						} else {
							ennemie.setOnPlatformBorderLeft(false);
							ennemie.setOnPlatformBorderRight(false);
						}
					}
				}
			} else if (Ennemie.class.isAssignableFrom(other.getBody().getUserData().getClass())) {
				Ennemie o = (Ennemie) other.getBody().getUserData();
				ennemie.touchEnnemie(o);
			} else if (other.getBody().getUserData().getClass() == Pick.class) {
				ennemie.kill();
			}
		}
	}

	public void endContact(Contact contact, Fixture ennemieFixture, Fixture other) {
		Ennemie ennemie = (Ennemie) ennemieFixture.getBody().getUserData();
		if (other != null) {
			if (other.getBody().getUserData() != null) {
				if (other.getBody().getUserData().getClass() == Platform.class) {
					if (contact.getWorldManifold().getPoints().length == 2) {
						Vector2 p0 = contact.getWorldManifold().getPoints()[0];
						Vector2 p1 = contact.getWorldManifold().getPoints()[1];
						if (p0.y == p1.y) {
							if (p0.x < ennemieFixture.getBody().getPosition().x) {
								ennemie.setTouchLeft(false);
							} else if (p0.x > ennemieFixture.getBody().getPosition().x) {
								ennemie.setTouchRight(false);
							}
						}
					}
				}
			}
		}
	}

	public void preSolve(Contact contact, Manifold oldManifold, Fixture ennemieFixture, Fixture other) {
		if (other != null) {
			if (other.getBody().getUserData() != null) {
				if (other.getBody().getUserData().getClass() == Platform.class) {
					if (contact.getWorldManifold().getPoints().length == 2) {
						Vector2 p0 = contact.getWorldManifold().getPoints()[0];
						Vector2 p1 = contact.getWorldManifold().getPoints()[1];
						Ennemie ennemie = (Ennemie) ennemieFixture.getBody().getUserData();
						if (p0.y == p1.y) {
							if (Math.abs(p0.x - p1.x) < Constante.ENNEMIE_BOX_WIDTH * 2f) {
								if (ennemie.getId() == 1) {
									Gdx.app.log("ENNEMIE1", "BORD");
								}
								float abs0 = Math.abs(p0.x - ennemieFixture.getBody().getPosition().x);
								float abs1 = Math.abs(p1.x - ennemieFixture.getBody().getPosition().x);
								if (p0.x < ennemieFixture.getBody().getPosition().x
										&& abs0 < Constante.ENNEMIE_BOX_WIDTH) {
									ennemie.setOnPlatformBorderLeft(true);
								} else if (p1.x < ennemieFixture.getBody().getPosition().x
										&& abs1 < Constante.ENNEMIE_BOX_WIDTH) {
									ennemie.setOnPlatformBorderLeft(true);
								}

								if (p0.x > ennemieFixture.getBody().getPosition().x
										&& abs0 < Constante.ENNEMIE_BOX_WIDTH) {
									ennemie.setOnPlatformBorderRight(true);
								} else if (p1.x > ennemieFixture.getBody().getPosition().x
										&& abs1 < Constante.ENNEMIE_BOX_WIDTH) {
									ennemie.setOnPlatformBorderRight(true);
								}

							} else {
								if (ennemie.getId() == 1) {
									Gdx.app.log("ENNEMIE1", "WALK");
								}
								ennemie.setOnPlatformBorderLeft(false);
								ennemie.setOnPlatformBorderRight(false);
							}
						}
					}
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

	public void postSolve(Contact contact, ContactImpulse impulse, Fixture ennemieFixture, Fixture other) {
	}
}
