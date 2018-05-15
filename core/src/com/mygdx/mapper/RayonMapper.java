package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Rayon;
import com.mygdx.service.dto.level.RayonDTO;

public class RayonMapper {

	public Rayon toEntity(RayonDTO dto) {
		if (dto == null) {
			return null;
		}
		Rayon rayon = new Rayon();
		return rayon;
	}

	public List<Rayon> toRayons(List<RayonDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Rayon> list = new ArrayList<>();
		for (RayonDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
