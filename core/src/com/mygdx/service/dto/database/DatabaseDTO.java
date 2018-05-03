package com.mygdx.service.dto.database;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseDTO implements Serializable {

	private static final long serialVersionUID = 7150416421330808482L;

	private List<QuestDTO> quests;
	private List<ItemDTO> items;
	private List<FamilyDTO> familys;
}
