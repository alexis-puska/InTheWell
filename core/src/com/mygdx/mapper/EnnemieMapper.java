package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.ennemie.AbricotNain;
import com.mygdx.domain.ennemie.Annanas;
import com.mygdx.domain.ennemie.Banane;
import com.mygdx.domain.ennemie.Blob;
import com.mygdx.domain.ennemie.Cerise;
import com.mygdx.domain.ennemie.Citron;
import com.mygdx.domain.ennemie.Ennemie;
import com.mygdx.domain.ennemie.Fraise;
import com.mygdx.domain.ennemie.Framboise;
import com.mygdx.domain.ennemie.Kiwi;
import com.mygdx.domain.ennemie.Litchi;
import com.mygdx.domain.ennemie.Orange;
import com.mygdx.domain.ennemie.Pasteque;
import com.mygdx.domain.ennemie.Poire;
import com.mygdx.domain.ennemie.Pomme;
import com.mygdx.domain.ennemie.Prune;
import com.mygdx.domain.ennemie.Scie;
import com.mygdx.service.dto.level.EnnemieDTO;

public class EnnemieMapper {

	public Ennemie toEntity(EnnemieDTO dto) {
		if (dto == null) {
			return null;
		}
		Ennemie ennemie = null;
		switch (dto.getType()) {
		case ABRICOT:
			ennemie = new Cerise();
			break;
		case ABRICOT_NAIN:
			ennemie = new AbricotNain();
			break;
		case ANNANAS:
			ennemie = new Annanas();
			break;
		case BANANE:
			ennemie = new Banane();
			break;
		case BLOB:
			ennemie = new Blob();
			break;
		case CERISE:
			ennemie = new Cerise();
			break;
		case CITRON:
			ennemie = new Citron();
			break;
		case FRAISE:
			ennemie = new Fraise();
			break;
		case FRAMBOISE:
			ennemie = new Framboise();
			break;
		case KIWI:
			ennemie = new Kiwi();
			break;
		case LITCHI:
			ennemie = new Litchi();
			break;
		case ORANGE:
			ennemie = new Orange();
			break;
		case PASTEQUE:
			ennemie = new Pasteque();
			break;
		case POIRE:
			ennemie = new Poire();
			break;
		case POMME:
			ennemie = new Pomme();
			break;
		case PRUNE:
			ennemie = new Prune();
			break;
		case SCIE:
			ennemie = new Scie();
			break;
		}
		ennemie.setId(dto.getId());
		ennemie.setEnable(dto.isEnable());
		ennemie.setX(dto.getX());
		ennemie.setY(dto.getY());
		ennemie.setType(dto.getType());
		return ennemie;
	}

	public List<Ennemie> toEnnemies(List<EnnemieDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Ennemie> list = new ArrayList<>();
		for (EnnemieDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
