package com.mygdx.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Decor {
	private int id;
	private int x;
	private int y;
	private boolean enable;
	private boolean back;
	private int indexAnim;
}
