package com.mygdx.service.dto.level;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VortexDTO extends IdentifiableDTO implements Serializable {

	private static final long serialVersionUID = 3701911571588077040L;
	private int x;
	private int y;
	private double zoomX;
	private double zoomY;
	private boolean enable;
	private int destination;

	public VortexDTO(int id, boolean enable, int x, int y, double zoomX, double zoomY, int destination) {
		super(id);
		this.enable = enable;
		this.x = x;
		this.y = y;
		this.zoomX = zoomX;
		this.zoomY = zoomY;
		this.destination = destination;
	}
}
