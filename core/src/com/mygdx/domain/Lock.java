package com.mygdx.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Lock {
	private boolean enable;
	private int x;
	private int y;
	private int requieredKeyId;
}
