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
		return platform;
	}

	public List<Platform> toPlatforms(List<PlatformDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Platform> list = new ArrayList<>();
		for (PlatformDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
