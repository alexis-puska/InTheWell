package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Door;
import com.mygdx.service.dto.level.DoorDTO;

public class DoorMapper {

	public Door toEntity(DoorDTO dto) {
		if (dto == null) {
			return null;
		}
		Door door = new Door();
		return door;
	}

	public List<Door> toDoors(List<DoorDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Door> list = new ArrayList<>();
		for (DoorDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
