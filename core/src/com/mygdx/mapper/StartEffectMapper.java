package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.StartEffectObjets;
import com.mygdx.service.dto.level.StartEffectObjetsDTO;

public class StartEffectMapper {

	public StartEffectObjets toEntity(StartEffectObjetsDTO dto) {
		if (dto == null) {
			return null;
		}
		StartEffectObjets startEffect = new StartEffectObjets();
		return startEffect;
	}

	public List<StartEffectObjets> toStartEffects(List<StartEffectObjetsDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<StartEffectObjets> list = new ArrayList<>();
		for (StartEffectObjetsDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
