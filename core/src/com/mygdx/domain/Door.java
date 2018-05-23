package com.mygdx.domain;

import com.mygdx.domain.common.BodyAble;
import com.mygdx.enumeration.GameKeyEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Door extends BodyAble {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private int type;
	private boolean locked;
	private int toLevel;
	private GameKeyEnum key;
	private int lockId;

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
