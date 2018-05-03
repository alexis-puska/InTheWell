package com.mygdx.service.dto.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlatformDTO extends IdentifiableDTO implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;

	private boolean enable;
	private int x;
	private int y;
	private boolean vertical;
	private boolean displayed;
	private int length;

	public PlatformDTO(int id, boolean enable, int x, int y, boolean vertical, boolean displayed, int length) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.vertical = vertical;
		this.displayed = displayed;
		this.length = length;
	}

}
