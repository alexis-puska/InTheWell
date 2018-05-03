package com.mygdx.service.dto.level;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LevelNameDTO extends IdentifiableDTO {

	private String lang;
	private String value;

	public LevelNameDTO(int id, String lang, String value) {
		super(id);
		this.lang = lang;
		this.value = value;
	}

}
