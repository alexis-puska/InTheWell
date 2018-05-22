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
		door.setEnable(dto.isEnable());
		door.setId(dto.getId());
		door.setLocked(dto.isLocked());
		door.setLockId(dto.getLockId());
		door.setKey(dto.getKey());
		door.setToLevel(dto.getToLevel());
		door.setType(dto.getType());
		door.setX(dto.getX());
		door.setY(dto.getY());
		return door;
	}

	public List<Door> toDoors(List<DoorDTO> dtos) {
		List<Door> list = new ArrayList<>();
		if (dtos == null) {
			return list;
		}
		for (DoorDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
