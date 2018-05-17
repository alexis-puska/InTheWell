package com.mygdx.domain;

import com.badlogic.gdx.physics.box2d.World;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pick {
	private int id;
	private int x;
	private int y;
	private boolean enable;
	private int direction;
	public void init(World world) {
		// TODO Auto-generated method stub
		
	}
}
