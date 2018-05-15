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
		return teleporter;
	}

	public List<Teleporter> toTeleporters(List<TeleporterDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Teleporter> list = new ArrayList<>();
		for (TeleporterDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
