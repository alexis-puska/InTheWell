package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Platform;
import com.mygdx.service.dto.level.PlatformDTO;

public class PlatformMapper {

	public Platform toEntity(PlatformDTO dto) {
		if (dto == null) {
			return null;
		}
		Platform platform = new Platform();
		platform.setDisplayed(dto.isDisplayed());
		platform.setEnable(dto.isEnable());
		platform.setId(dto.getId());
		platform.setLength(dto.getLength());
		platform.setVertical(dto.isVertical());
		platform.setX(dto.getX());
		platform.setY(dto.getY());
		return platform;
	}

	public List<Platform> toPlatforms(List<PlatformDTO> dtos) {
		List<Platform> list = new ArrayList<>();
		if (dtos == null) {
			return list;
		}
		for (PlatformDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
