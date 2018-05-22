package com.mygdx.domain;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.InTheWellGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Teleporter implements Drawable{
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private int length;
	private boolean vertical;
	private int toId;
	

	private World world;
	private InTheWellGame game;

	public void init(World world, InTheWellGame game) {
		this.world = world;
		this.game = game;
	}

	public void dispose() {
		
	}

	@Override
	public void drawIt() {
		
	}
}
