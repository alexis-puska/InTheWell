package com.mygdx.enumeration;

public enum EnnemieStateEnum {
	//@formatter:off
	ALIFE(""),
	ANGRY("_angry"),
	KOCKOUT(""),
	ICED(""),
	DEAD("_dead"), 
	WALK("_walk");
	//@formatter:on

	private String stateName;

	private EnnemieStateEnum(String stateName) {
		this.stateName = stateName;
	}

	public String getStateName() {
		return stateName;
	}
}
