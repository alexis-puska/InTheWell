package com.mygdx.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Platform {

	private long id;
	private boolean enable;
	private int x;
	private int y;
	private boolean vertical;
	private boolean displayed;
	private int length;
	
	//player collision
	float min;
	float max;
	
	
	public Platform(long id, boolean vertical, float min, float max) {
		this.id = id;
		this.vertical = vertical;
		this.min = min;
		this.max = max;
	}
}
