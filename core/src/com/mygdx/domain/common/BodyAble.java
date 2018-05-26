package com.mygdx.domain.common;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.InTheWellGame;

public abstract class BodyAble extends Drawable {

	protected World world;
	protected Body body;

	public void init(World world, InTheWellGame game) {
		this.init(game);
		this.world = world;
		createBody();
	}

	public void dispose() {
		if (body != null) {
			this.world.destroyBody(body);
			body = null;
		}

	}

	public abstract void createBody();

	@Override
	public abstract void enable();

	@Override
	public abstract void disable();

	@Override
	public abstract void drawIt();
}
