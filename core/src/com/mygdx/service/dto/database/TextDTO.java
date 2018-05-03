package com.mygdx.service.dto.database;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TextDTO implements Serializable {

	private static final long serialVersionUID = -6487941132256363742L;

	private String fr;
	private String en;
	private String es;
}
