package com.mygdx.domain;

import com.mygdx.enumeration.GameKeyEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Door {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private int type;
	private boolean locked;
	private int toLevel;
	private GameKeyEnum key;
	private int lockId;
}
