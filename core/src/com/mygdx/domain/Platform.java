package com.mygdx.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Platform {

	long id;
	float min;
	float max;
	boolean vertical;

	public Platform(long id, boolean vertical, float min, float max) {
		this.id = id;
		this.vertical = vertical;
		this.min = min;
		this.max = max;
	}
}
