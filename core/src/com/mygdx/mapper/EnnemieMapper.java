package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.ennemie.Ennemie;
import com.mygdx.service.dto.level.EnnemieDTO;

public class EnnemieMapper {

	public Ennemie toEntity(EnnemieDTO dto) {
		if (dto == null) {
			return null;
		}
		Ennemie ennemie = new Ennemie();
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
