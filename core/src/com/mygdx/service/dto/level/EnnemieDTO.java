package com.mygdx.service.dto.level;

import java.io.Serializable;

import com.mygdx.enumeration.EnnemieTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnnemieDTO extends IdentifiableDTO implements Serializable {

	private static final long serialVersionUID = -3148349064427411770L;
	private boolean enable;
	private int x;
	private int y;
	private EnnemieTypeEnum type;

	public EnnemieDTO(int id, boolean enable, int x, int y, EnnemieTypeEnum type) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.type = type;
	}

}
