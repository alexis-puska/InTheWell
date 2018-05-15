package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.StartPoint;
import com.mygdx.service.dto.level.StartPointObjetsDTO;

public class StartPointMapper {

	public StartPoint toEntity(StartPointObjetsDTO dto) {
		if (dto == null) {
			return null;
		}
		StartPoint startPointObjets = new StartPoint();
		return startPointObjets;
	}

	public List<StartPoint> toStartPointObjetss(List<StartPointObjetsDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<StartPoint> list = new ArrayList<>();
		for (StartPointObjetsDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
