package com.mygdx.domain.ennemie;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.enumeration.EnnemieTypeEnum;
import com.mygdx.game.InTheWellGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ennemie {
	private int id;
	protected boolean enable;
	protected int x;
	protected int y;
	protected EnnemieTypeEnum type;

	private InTheWellGame game;
	private World world;

	public void init(World world, InTheWellGame game) {
		this.game = game;
		this.world = world;
	}

	public void dispose() {

	}
}
