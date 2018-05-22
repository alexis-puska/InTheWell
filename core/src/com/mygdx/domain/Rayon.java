package com.mygdx.domain;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.enumeration.RayonTypeEnum;
import com.mygdx.game.InTheWellGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rayon implements Drawable {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private int length;
	private RayonTypeEnum type;
	private boolean vertical;

	private World world;
	private InTheWellGame game;

	public void init(World world, InTheWellGame game) {
		this.world = world;
		this.game = game;
	}

	@Override
	public void drawIt() {

	}

	public void dispose() {

	}
}
