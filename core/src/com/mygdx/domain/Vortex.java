package com.mygdx.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vortex {
	private int x;
	private int y;
	private double zoomX;
	private double zoomY;
	private boolean enable;
	private int destination;
}
