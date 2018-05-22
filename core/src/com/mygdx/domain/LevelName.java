package com.mygdx.domain;

import com.mygdx.game.InTheWellGame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelName implements Drawable {
	private int id;
	private String lang;
	private String value;

	private InTheWellGame game;

	public void init(InTheWellGame game) {
		this.game = game;
	}

	public void dispose() {
		
	}

	@Override
	public void drawIt() {
		// TODO Auto-generated method stub

	}
}
