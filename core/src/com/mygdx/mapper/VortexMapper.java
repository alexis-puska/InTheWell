package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Vortex;
import com.mygdx.service.dto.level.VortexDTO;

public class VortexMapper {

	public Vortex toEntity(VortexDTO dto) {
		if (dto == null) {
			return null;
		}
		Vortex vortex = new Vortex();
		return vortex;
	}

	public List<Vortex> toVortexs(List<VortexDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Vortex> list = new ArrayList<>();
		for (VortexDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
