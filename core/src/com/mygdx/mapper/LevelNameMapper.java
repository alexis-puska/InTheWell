package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.LevelName;
import com.mygdx.service.dto.level.LevelNameDTO;

public class LevelNameMapper {

	public LevelName toEntity(LevelNameDTO dto) {
		if (dto == null) {
			return null;
		}
		LevelName levelName = new LevelName();
		levelName.setId(dto.getId());
		levelName.setLang(dto.getLang());
		levelName.setValue(dto.getValue());
		return levelName;
	}

	public List<LevelName> toLevelNames(List<LevelNameDTO> dtos) {
		List<LevelName> list = new ArrayList<>();
		if (dtos == null) {
			return list;
		}
		for (LevelNameDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
