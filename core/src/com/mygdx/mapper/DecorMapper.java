package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Decor;
import com.mygdx.service.dto.level.DecorDTO;

public class DecorMapper {

	public Decor toEntity(DecorDTO dto) {
		if (dto == null) {
			return null;
		}
		Decor decor = new Decor();
		decor.setId(dto.getId());
		decor.setBack(dto.isBack());
		decor.setEnable(dto.isEnable());
		decor.setIndexAnim(dto.getIndexAnim());
		decor.setX(dto.getX());
		decor.setY(dto.getY());
		return decor;
	}

	public List<Decor> toDecors(List<DecorDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Decor> list = new ArrayList<>();
		for (DecorDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
