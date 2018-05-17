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
		vortex.setDestination(dto.getDestination());
		vortex.setEnable(dto.isEnable());
		vortex.setId(dto.getId());
		vortex.setX(dto.getX());
		vortex.setY(dto.getY());
		vortex.setZoomX(dto.getZoomX());
		vortex.setZoomY(dto.getZoomY());
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
