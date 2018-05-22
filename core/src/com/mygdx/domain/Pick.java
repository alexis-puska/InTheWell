package com.mygdx.domain;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.InTheWellGame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pick implements Drawable{
	private int id;
	private int x;
	private int y;
	private boolean enable;
	private int direction;

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
