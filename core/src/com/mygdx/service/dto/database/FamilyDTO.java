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
public class FamilyDTO implements Serializable {

	private static final long serialVersionUID = -6487941132256363742L;

	private long id;
	private TextDTO name;
	private List<Long> items;
}
