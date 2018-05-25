package com.mygdx.domain.common;

import com.mygdx.enumeration.EnnemieTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Ennemie extends BodyAble {
	private int id;
	protected boolean enable;
	protected int x;
	protected int y;
	protected EnnemieTypeEnum type;

	public boolean isDead() {
		return false;
	}
}
