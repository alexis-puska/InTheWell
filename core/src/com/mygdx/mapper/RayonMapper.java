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
		rayon.setEnable(dto.isEnable());
		rayon.setId(dto.getId());
		rayon.setLength(dto.getLength());
		rayon.setType(dto.getType());
		rayon.setVertical(dto.isVertical());
		rayon.setX(dto.getX());
		rayon.setY(dto.getY());
		return rayon;
	}

	public List<Rayon> toRayons(List<RayonDTO> dtos) {
		List<Rayon> list = new ArrayList<>();
		if (dtos == null) {
			return list;
		}
		for (RayonDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
