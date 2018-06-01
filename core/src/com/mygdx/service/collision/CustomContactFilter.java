package com.mygdx.service.collision;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.constante.Constante;
import com.mygdx.domain.Platform;
import com.mygdx.domain.Player;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.domain.ennemie.Blob;
import com.mygdx.domain.ennemie.Scie;

public class CustomContactFilter implements ContactFilter {

	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

		int n = playerInvolvedWithPlatform(fixtureA, fixtureB);
		Body pBody = null;
		Player pla = null;
		Platform pl = null;
		if (n == 0) {
			pBody = fixtureA.getBody();
			pl = (Platform) fixtureB.getBody().getUserData();
			pla = (Player) fixtureA.getBody().getUserData();
			float y1 = pBody.getPosition().y - Constante.PLAYER_BOX_HEIGHT;
			float y2 = pBody.getPosition().y + Constante.PLAYER_BOX_HEIGHT;
			return collideOtherSideBottom(y1, y2, pBody, pl, pla.isInsidePlatform());
		} else if (n == 1) {
			pBody = fixtureB.getBody();
			pl = (Platform) fixtureA.getBody().getUserData();
			pla = (Player) fixtureB.getBody().getUserData();
			float y1 = pBody.getPosition().y - Constante.PLAYER_BOX_HEIGHT;
			float y2 = pBody.getPosition().y + Constante.PLAYER_BOX_HEIGHT;
			return collideOtherSideBottom(y1, y2, pBody, pl, pla.isInsidePlatform());
		}

		n = ennemieInvolvedWithPlatform(fixtureA, fixtureB);
		pBody = null;
		Ennemie e = null;
		pl = null;
		if (n == 0) {
			pBody = fixtureA.getBody();
			pl = (Platform) fixtureB.getBody().getUserData();
			e = (Ennemie) fixtureA.getBody().getUserData();
			float y1 = pBody.getPosition().y - Constante.PLAYER_BOX_HEIGHT;
			float y2 = pBody.getPosition().y + Constante.PLAYER_BOX_HEIGHT;

			return collideOtherSideBottom(y1, y2, pBody, pl, e.isInsidePlatform());
		} else if (n == 1) {
			pBody = fixtureB.getBody();
			pl = (Platform) fixtureA.getBody().getUserData();
			e = (Ennemie) fixtureB.getBody().getUserData();
			float y1 = pBody.getPosition().y - Constante.PLAYER_BOX_HEIGHT;
			float y2 = pBody.getPosition().y + Constante.PLAYER_BOX_HEIGHT;
			return collideOtherSideBottom(y1, y2, pBody, pl, e.isInsidePlatform());
		} else if (n == 2) {
			return false;
		}

		return true;

	}

	private int ennemieInvolvedWithPlatform(Fixture fixtureA, Fixture fixtureB) {
		if (Ennemie.class.isAssignableFrom(fixtureA.getBody().getUserData().getClass())
				&& fixtureA.getBody().getUserData().getClass() != Blob.class
				&& fixtureA.getBody().getUserData().getClass() != Scie.class
				&& fixtureA.getBody().getUserData().getClass() != Package.class
				&& fixtureB.getBody().getUserData().getClass() == Platform.class) {
			return 0;
		}
		if (Ennemie.class.isAssignableFrom(fixtureB.getBody().getUserData().getClass())
				&& fixtureB.getBody().getUserData().getClass() != Blob.class
				&& fixtureB.getBody().getUserData().getClass() != Scie.class
				&& fixtureB.getBody().getUserData().getClass() != Package.class
				&& fixtureA.getBody().getUserData().getClass() == Platform.class) {
			return 1;
		}
		if (Ennemie.class.isAssignableFrom(fixtureB.getBody().getUserData().getClass())
				&& Ennemie.class.isAssignableFrom(fixtureA.getBody().getUserData().getClass())) {
			return 2;
		}
		return -1;

	}

	private int playerInvolvedWithPlatform(Fixture fixtureA, Fixture fixtureB) {
		if (fixtureA.getBody().getUserData().getClass() == Player.class
				&& fixtureB.getBody().getUserData().getClass() == Platform.class) {
			return 0;
		}
		if (fixtureB.getBody().getUserData().getClass() == Player.class
				&& fixtureA.getBody().getUserData().getClass() == Platform.class) {
			return 1;
		}
		return -1;
	}

	private boolean collideOtherSideBottom(float y1, float y2, Body p, Platform pl, boolean inside) {
		float y3 = pl.getY();
		if (y3 > y1 || y3 > y2 || inside) {
			// Gdx.app.log("collide OFF", "y1 : " + y1 + ", y2 : " + y2 + ", y3 : " + y3 + "
			// " + pl.getY());
			return false;
		}
		// Gdx.app.log("collide LAISSER ON", "y1 : " + y1 + ", y2 : " + y2 + ", y3 : " +
		// y3 + " " + pl.getY());
		return true;
	}

}
