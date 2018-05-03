package com.mygdx.service.dto.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RayonDTO extends IdentifiableDTO implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private boolean enable;
	private int x;
	private int y;
	private int length;
	private int type;
	private boolean vertical;

	public RayonDTO(int id, boolean enable, int x, int y, int length, int type, boolean vertical) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.length = length;
		this.type = type;
		this.vertical = vertical;
	}

}
