package com.mygdx.domain.event;

import com.mygdx.domain.common.Identifiable;
import com.mygdx.enumeration.EnabledElementEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnableElement extends Identifiable{
	private EnabledElementEnum elementType;
	private boolean newState;
}
