package com.mygdx.domain;

import com.mygdx.domain.common.Drawable;
import com.mygdx.service.Context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelName extends Drawable {
	private int id;
	private String lang;
	private String value;

	private boolean runned;

	@Override
	public void enable() {
		
	}

	@Override
	public void disable() {
		
	}

	@Override
	public void drawIt() {
		if(!runned) {
			if (lang.equals(Context.getLocale().getCode()) && !value.equals("")) {
				game.getNotificationService().addCountryNotification(value, 200);
			}
			runned = true;		
		}
	}
}
