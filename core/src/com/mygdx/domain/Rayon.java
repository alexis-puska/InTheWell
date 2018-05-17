package com.mygdx.domain;

import com.mygdx.enumeration.RayonTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rayon {
	private int id;
	private boolean enable;
	private int x;
	private int y;
	private int length;
	private RayonTypeEnum type;
	private boolean vertical;
}
