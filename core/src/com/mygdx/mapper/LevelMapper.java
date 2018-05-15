package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Level;
import com.mygdx.service.dto.level.LevelDTO;

public class LevelMapper {

	public Level toEntity(LevelDTO dto) {
		if (dto == null) {
			return null;
		}
		Level level = new Level();
		return level;
	}

	public List<Level> toLevels(List<LevelDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Level> list = new ArrayList<>();
		for (LevelDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
