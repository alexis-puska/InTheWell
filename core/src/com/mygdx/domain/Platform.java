package com.mygdx.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Platform {

	boolean vertical;

	public Platform(boolean vertical) {
		this.vertical = vertical;
	}
}
