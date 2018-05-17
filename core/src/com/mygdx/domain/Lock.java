package com.mygdx.domain;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.enumeration.GameKeyEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lock {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private GameKeyEnum key;
	public void init(World world) {
		// TODO Auto-generated method stub
		
	}
}
