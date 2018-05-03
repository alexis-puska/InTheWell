package com.mygdx.service.dto.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Teleporter extends Identifiable implements Serializable {

	private static final long serialVersionUID = 8337468415269378236L;
	private boolean enable;
	private int x;
	private int y;
	private int length;
	private boolean vertical;
	private int toId;

	public Teleporter(int id, boolean enable, int x, int y, int length, boolean vertical, int toId) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.length = length;
		this.vertical = vertical;
		this.toId = toId;
	}

}
