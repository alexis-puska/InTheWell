package com.mygdx.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Door {
	private boolean enable;
	private int x;
	private int y;
	private int type;
	private boolean locked;
	private int toLevel;
	private int requieredKey;
	private int lockId;
}
