package com.mygdx.service.dto.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

	private int id;
	private TextDTO name;
	private int rarity;
	private int value;
	private int unlock;
}
