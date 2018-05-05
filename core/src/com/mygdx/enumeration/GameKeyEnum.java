package com.mygdx.enumeration;

public enum GameKeyEnum {
	GORDON(1),
	PASSE_PARTOUT(1),
	RIGOR_DANGEROUS(1),
	MELUZZINE(1),
	BOURRU(1),
	FURTOK_GLACIAL(1),
	CLE_ROUILLEE(1),
	PASSE_PARTOUT_BOIS_JOLI(1),
	MONDE_ARDU(1),
	PIQUANTE(1),
	PASSE_TUBERULOZ(1),
	CUAHCEMAR(1),
	PASS_PYRAMIDE(1);
			
	private int itemId;
	
	private GameKeyEnum(int itemId) {
		this.itemId = itemId;
	}
	
	public int getItemId() {
		return itemId;
	}
}
