package com.mygdx.domain;

import com.badlogic.gdx.physics.box2d.World;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Teleporter {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private int length;
	private boolean vertical;
	private int toId;
	public void init(World world) {
		// TODO Auto-generated method stub
		
	}
}
