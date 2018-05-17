package com.mygdx.domain.ennemie;

import com.mygdx.enumeration.EnnemieTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ennemie {
	protected boolean enable;
	protected int x;
	protected int y;
	protected EnnemieTypeEnum type;

}
