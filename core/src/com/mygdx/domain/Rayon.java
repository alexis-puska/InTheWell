package com.mygdx.domain;

import com.mygdx.domain.common.BodyAble;
import com.mygdx.enumeration.RayonTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rayon extends BodyAble {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private int length;
	private RayonTypeEnum type;
	private boolean vertical;

	@Override
	public void enable() {

	}

	@Override
	public void disable() {

	}

	@Override
	public void drawIt() {

	}
	
	@Override
	public void createBody() {
		
	}
}
