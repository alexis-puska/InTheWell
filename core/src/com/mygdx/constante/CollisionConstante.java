package com.mygdx.constante;

public class CollisionConstante {
	//@formatter:off
	
	public static final short CATEGORY_PLAYER	 		= 0b0000000000000001;
	public static final short CATEGORY_ENNEMIE	 		= 0b0000000000000010;
	public static final short CATEGORY_ICED_ENNEMIE		= 0b0000000000000100;
	public static final short CATEGORY_PLATFORM	 		= 0b0000000000001000;
	public static final short CATEGORY_DOOR		 		= 0b0000000000010000;
	public static final short CATEGORY_LOCK		 		= 0b0000000000100000;
	public static final short CATEGORY_VORTEX	 		= 0b0000000001000000;
	public static final short CATEGORY_TELEPORTER 		= 0b0000000010000000;
	public static final short CATEGORY_PICK		 		= 0b0000000100000000;
	public static final short CATEGORY_RAYON		 	= 0b0000001000000000;
	public static final short CATEGORY_ITEM				= 0b0000010000000000;
	public static final short CATEGORY_BOMBE			= 0b0000100000000000;
	public static final short CATEGORY_EXPLOSION		= 0b0001000000000000;
	public static final short CATEGORY_EVENT			= 0b0010000000000000;
	
	
	/**
	 * GROUPS , If a category is on a mask group bits, this create a collision (contact), if not, no collision create
	 */
	public static final short GROUP_PLAYER = CATEGORY_PLATFORM | CATEGORY_ENNEMIE    | CATEGORY_DOOR | CATEGORY_LOCK
											| CATEGORY_VORTEX | CATEGORY_TELEPORTER | CATEGORY_PICK | CATEGORY_RAYON
											| CATEGORY_ITEM   | CATEGORY_EVENT;
	public static final short GROUP_ENNEMIE = CATEGORY_PLAYER | CATEGORY_PICK | CATEGORY_PLATFORM | CATEGORY_BOMBE | CATEGORY_EXPLOSION;
	//@formatter:on

	private CollisionConstante() {
		// empty private constructor
	}
}
