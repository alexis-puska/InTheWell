package com.mygdx.mapper.event;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.event.EnableElement;
import com.mygdx.service.dto.level.event.EnableElementDTO;

public class EnableElementMapper {

	public EnableElement toEntity(EnableElementDTO dto) {
		if (dto == null) {
			return null;
		}
		EnableElement enableElement = new EnableElement();
		enableElement.setElementType(dto.getElementType());
		enableElement.setId(dto.getId());
		enableElement.setNewState(dto.isNewState());
		return enableElement;
	}

	public List<EnableElement> toEnableElements(List<EnableElementDTO> dtos) {
		List<EnableElement> list = new ArrayList<>();
		if (dtos == null) {
			return list;
		}
		for (EnableElementDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
