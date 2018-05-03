package com.mygdx.service.dto.level;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeDTO implements Serializable {
	private static final long serialVersionUID = -5410889236338101940L;
	private int id;
	private List<LevelDTO> level;
}
