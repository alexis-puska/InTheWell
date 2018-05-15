package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Pick;
import com.mygdx.service.dto.level.PickDTO;

public class PickMapper {

	public Pick toEntity(PickDTO dto) {
		if (dto == null) {
			return null;
		}
		Pick pick = new Pick();
		return pick;
	}

	public List<Pick> toPicks(List<PickDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Pick> list = new ArrayList<>();
		for (PickDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
