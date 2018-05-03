package com.mygdx.service.dto.level;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LockDTO extends IdentifiableDTO implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private boolean enable;
	private int x;
	private int y;
	private int requieredKeyId;

	public LockDTO(int id, boolean enable, int x, int y, int requieredKeyId) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.requieredKeyId = requieredKeyId;
	}

}
