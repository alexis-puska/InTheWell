package com.mygdx.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Teleporter {
	private boolean enable;
	private int x;
	private int y;
	private int length;
	private boolean vertical;
	private int toId;
}
