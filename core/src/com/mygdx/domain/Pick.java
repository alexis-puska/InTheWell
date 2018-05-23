package com.mygdx.domain;

import com.mygdx.domain.common.BodyAble;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pick extends BodyAble {
	private int id;
	private int x;
	private int y;
	private boolean enable;
	private int direction;

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
