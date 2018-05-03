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

	private long id;
	private TextDTO name;
	private long rarity;
	private long value;
	private long unlock;
}
