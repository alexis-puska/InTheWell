package com.mygdx.enumeration;

public enum EnnemieTypeEnum {
	CERISE("cerise"),
	ORANGE("orange"),
	POMME("pomme"),
	BANANE("banane"),
	LITCHI("litchi"),
	FRAISE("fraise"),
	FRAMBOISE("framboise"),
	CITRON("citron"),
	ABRICOT("abricot"),
	ABRICOT_NAIN("nainbricot"),
	ANNANAS("annanas"),
	KIWI("kiwi"),
	PASTEQUE("pasteque"),
	PRUNE("bombinos"),
	SCIE("scie"),
	POIRE("poire"),
	BLOB("blob");
	
	private String name;

	private EnnemieTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
