package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Item;
import com.mygdx.service.dto.level.ItemDTO;

public class ItemMapper {

	public Item toEntity(ItemDTO dto) {
		if (dto == null) {
			return null;
		}
		Item item = new Item();
		return item;
	}

	public List<Item> toItems(List<ItemDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Item> list = new ArrayList<>();
		for (ItemDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
