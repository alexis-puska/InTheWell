package com.mygdx.domain;

import com.mygdx.domain.common.BodyAble;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vortex extends BodyAble {
	private int x;
	private int y;
	private double zoomX;
	private double zoomY;
	private int destination;

	@Override
	public void drawIt() {

	}

	@Override
	public void createBody() {

	}
}
