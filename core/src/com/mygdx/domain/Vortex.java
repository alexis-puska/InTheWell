package com.mygdx.domain;

import com.badlogic.gdx.physics.box2d.World;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vortex {
	private int id;
	private int x;
	private int y;
	private double zoomX;
	private double zoomY;
	private boolean enable;
	private int destination;
	public void init(World world) {
		// TODO Auto-generated method stub
		
	}
}
