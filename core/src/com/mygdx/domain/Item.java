package com.mygdx.domain;

import com.mygdx.domain.common.BodyAble;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item extends BodyAble {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private int itemId;

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
