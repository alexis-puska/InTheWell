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
public class RequiredDTO implements Serializable {

	private static final long serialVersionUID = -3639705881705541803L;

	private long id;
	private long val;
}
