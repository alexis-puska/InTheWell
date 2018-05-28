package com.mygdx.enumeration;

public enum RayonTypeEnum {
	BLACK(0, BombeTypeEnum.BLACK),
	BLUE(2, BombeTypeEnum.BLUE),
	GREEN(4, BombeTypeEnum.GREEN),
	RED(6, BombeTypeEnum.RED),
	WHITE(8, BombeTypeEnum.WHITE),
	PURPLE(10, BombeTypeEnum.NONE),
	DARK_PURPLE(12, null),
	ORANGE_YELLOW(14, null),
	DOUBLE_ORANGE(16, null);

	private int index;
	private BombeTypeEnum bombeType;
	
	RayonTypeEnum(int index, BombeTypeEnum bombeType) {
		this.index = index;
		this.bombeType = bombeType;
	}
	
	public int getIndex() {
		return index;
	}
	
	public BombeTypeEnum getBombeType() {
		return bombeType;
	}
}
