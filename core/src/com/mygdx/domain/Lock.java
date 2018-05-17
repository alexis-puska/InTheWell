package com.mygdx.domain;

import com.mygdx.enumeration.GameKeyEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lock {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private GameKeyEnum key;
}
