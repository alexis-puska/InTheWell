package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Position;
import com.mygdx.service.dto.level.PositionDTO;

public class PositionMapper {

	public Position toEntity(PositionDTO dto) {
		if (dto == null) {
			return null;
		}
		Position position = new Position();
		position.setX(dto.getX());
		position.setY(dto.getY());
		return position;
	}

	public List<Position> toEntitys(List<PositionDTO> dtos) {
		List<Position> list = new ArrayList<>();
		if (dtos == null) {
			return list;
		}
		for (PositionDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
