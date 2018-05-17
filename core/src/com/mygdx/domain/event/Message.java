package com.mygdx.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
	int timeout;
	String en;
	String fr;
	String es;
}
