package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Teleporter;
import com.mygdx.service.dto.level.TeleporterDTO;

public class TeleporterMapper {

	public Teleporter toEntity(TeleporterDTO dto) {
		if (dto == null) {
			return null;
		}
		Teleporter teleporter = new Teleporter();
		teleporter.setEnable(dto.isEnable());
		teleporter.setId(dto.getId());
		teleporter.setLength(dto.getLength());
		teleporter.setVertical(dto.isVertical());
		teleporter.setX(dto.getX());
		teleporter.setY(dto.getY());
		String[] destinations = dto.getDestinations().split(",");
		List<Integer> destinationsId = new ArrayList<>();
		for (int i = 0; i < destinations.length; i++) {
			destinationsId.add(Integer.valueOf(destinations[i]));
		}
		teleporter.setDestinations(destinationsId);
		if (!destinationsId.isEmpty()) {
			teleporter.setToId(destinationsId.get(0));
		} else {
			teleporter.setToId(0);
		}
		return teleporter;
	}

	public List<Teleporter> toTeleporters(List<TeleporterDTO> dtos) {
		List<Teleporter> list = new ArrayList<>();
		if (dtos == null) {
			return list;
		}
		for (TeleporterDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
