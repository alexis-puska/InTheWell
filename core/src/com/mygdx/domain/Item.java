package com.mygdx.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.BodyAble;
import com.mygdx.service.SpriteService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item extends BodyAble {
	private int x;
	private int y;
	private int itemId;

	private boolean draw;
	private int timeout;

	@Override
	public void drawIt() {
		if (enable) {
			if (draw) {
				TextureRegion tmp = SpriteService.getInstance().getTexture("objects", itemId);
				game.getBatch().draw(tmp,
						(body.getPosition().x * Constante.GRID_BLOC_SIZE) - (tmp.getRegionWidth() / 2.0f),
						(body.getPosition().y * Constante.GRID_BLOC_SIZE)
								- (Constante.ITEM_BOX_SIZE * Constante.GRID_BLOC_SIZE));
			}
		}
		// traitement des objets apparut dans le niveau, disparition
		if (timeout > 0) {
			timeout--;
			if (timeout <= 160) {
				if (timeout % 10 == 0 && draw) {
					draw = false;
				} else if (timeout % 10 == 0 && !draw) {
					draw = true;
				}
			}
		} else if (timeout == 0) {
			this.enable = false;
		}
	}

	@Override
	public void createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x + 0.5f, y + 0.5f);
		body = world.createBody(bodyDef);
		body.setFixedRotation(true);
		MassData data = new MassData();
		data.mass = 100f;
		body.setMassData(data);
		body.setUserData(this);
		PolygonShape bodyBox = new PolygonShape();
		bodyBox.setAsBox(Constante.ITEM_BOX_SIZE, Constante.ITEM_BOX_SIZE);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = bodyBox;
		fixtureDef.density = 1;
		fixtureDef.restitution = 0f;
		Fixture fixture = body.createFixture(fixtureDef);
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_ITEM;
		fixture.setFilterData(filter);
		bodyBox.dispose();
	}

}
