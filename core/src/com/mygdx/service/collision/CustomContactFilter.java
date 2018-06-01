package com.mygdx.service.collision;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.domain.Platform;
import com.mygdx.domain.Player;
import com.mygdx.domain.common.Ennemie;

public class CustomContactFilter implements ContactFilter {

	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

		Filter filterA = fixtureA.getFilterData();
		Filter filterB = fixtureB.getFilterData();

		if (filterA.groupIndex == filterB.groupIndex && filterA.groupIndex != 0) {
			return filterA.groupIndex > 0;
		}

		boolean collide = (filterA.maskBits & filterB.categoryBits) != 0
				&& (filterA.categoryBits & filterB.maskBits) != 0;

		/**********************************************************************************
		 * Surcharge le comportement des masques de collision pour le type platforme,
		 * nécessite de traité pour les ennemis ou les joueurs de désactivé les
		 * platformes par le dessous. Si le haut du "body" du joueur ou de l'ennemie est
		 * inférieurs au bas de la platforme lors du tests de collisions. Cela indique
		 * que le joueurs vient par en dessous et donc il faille désactiver la platforme
		 **********************************************************************************/
		float yPlatform = 0f;
		float topObject = 0f;
		boolean isInside = false;
		if (filterA.categoryBits == CollisionConstante.CATEGORY_PLATFORM) {
			if (filterB.categoryBits == CollisionConstante.CATEGORY_PLAYER) {
				yPlatform = ((Platform) fixtureA.getBody().getUserData()).getY();
				topObject = fixtureB.getBody().getPosition().y;
				isInside = ((Player) fixtureB.getBody().getUserData()).isInsidePlatform();
			} else if (filterB.categoryBits == CollisionConstante.CATEGORY_ICED_ENNEMIE
					|| filterB.categoryBits == CollisionConstante.CATEGORY_ENNEMIE) {
				yPlatform = ((Platform) fixtureA.getBody().getUserData()).getY();
				topObject = fixtureB.getBody().getPosition().y;
				isInside = ((Ennemie) fixtureB.getBody().getUserData()).isInsidePlatform();
			}
			return collideOtherSideBottom(topObject, yPlatform, isInside);
		} else if (filterB.categoryBits == CollisionConstante.CATEGORY_PLATFORM) {
			if (filterA.categoryBits == CollisionConstante.CATEGORY_PLAYER) {
				yPlatform = ((Platform) fixtureB.getBody().getUserData()).getY();
				topObject = fixtureA.getBody().getPosition().y;
				isInside = ((Player) fixtureA.getBody().getUserData()).isInsidePlatform();
			} else if (filterA.categoryBits == CollisionConstante.CATEGORY_ICED_ENNEMIE
					|| filterA.categoryBits == CollisionConstante.CATEGORY_ENNEMIE) {
				yPlatform = ((Platform) fixtureB.getBody().getUserData()).getY();
				topObject = fixtureA.getBody().getPosition().y;
				isInside = ((Ennemie) fixtureA.getBody().getUserData()).isInsidePlatform();
			}
			return collideOtherSideBottom(topObject, yPlatform, isInside);
		}
		return collide;
	}

	private boolean collideOtherSideBottom(float topObject, float yPlatform, boolean inside) {
		if (yPlatform > topObject || inside) {
			return false;
		}
		return true;
	}
}
