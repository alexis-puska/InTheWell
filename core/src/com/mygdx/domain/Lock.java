package com.mygdx.domain;

import com.mygdx.domain.common.BodyAble;
import com.mygdx.enumeration.GameKeyEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lock extends BodyAble {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private GameKeyEnum key;

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
