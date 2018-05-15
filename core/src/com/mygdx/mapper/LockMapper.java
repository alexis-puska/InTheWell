package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Lock;
import com.mygdx.service.dto.level.LockDTO;

public class LockMapper {

	public Lock toEntity(LockDTO dto) {
		if (dto == null) {
			return null;
		}
		Lock lock = new Lock();
		return lock;
	}

	public List<Lock> toLocks(List<LockDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Lock> list = new ArrayList<>();
		for (LockDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
