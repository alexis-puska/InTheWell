package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.StartPlayer;
import com.mygdx.service.dto.level.StartPlayerDTO;

public class StartPlayerMapper {

	public StartPlayer toEntity(StartPlayerDTO dto) {
		if (dto == null) {
			return null;
		}
		StartPlayer startPlayer = new StartPlayer();
		return startPlayer;
	}

	public List<StartPlayer> toStartPlayers(List<StartPlayerDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<StartPlayer> list = new ArrayList<>();
		for (StartPlayerDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
