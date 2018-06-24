package com.mygdx.service.dto.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeleporterDTO extends IdentifiableDTO implements Serializable {

	private static final long serialVersionUID = 8337468415269378236L;
	private boolean enable;
	private int x;
	private int y;
	private int length;
	private boolean vertical;
	private boolean invX;
	private boolean invY;
	private String destinations;

	public TeleporterDTO(int id, boolean enable, int x, int y, int length, boolean vertical, String destinations) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.length = length;
		this.vertical = vertical;
		this.destinations = destinations;
	}

}
