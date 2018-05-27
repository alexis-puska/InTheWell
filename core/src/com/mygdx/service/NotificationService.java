package com.mygdx.service;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.notification.CountryNotification;

public class NotificationService {

	private static final String NOTIFICATION_SERVICE = "Notification Service";

	private InTheWellGame game;
	private GlyphLayout layout;
	private BitmapFont fontWhite;
	private BitmapFont smallFontWhite;

	private List<CountryNotification> country;

	public NotificationService(InTheWellGame game) {
		this.country = new ArrayList<>();
		this.game = game;
		this.layout = new GlyphLayout();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_impact.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 14;
		parameter.borderWidth = 2f;
		parameter.borderColor = new Color(0, 0, 0, 255);
		parameter.color = new Color(255, 255, 255, 255);
		fontWhite = generator.generateFont(parameter);

		parameter.size = 10;
		parameter.borderWidth = 1f;
		parameter.borderColor = new Color(0, 0, 0, 255);
		parameter.color = new Color(255, 255, 255, 255);
		smallFontWhite = generator.generateFont(parameter);
		generator.dispose();
	}

	public void addCountryNotification(String country, int timeout) {
		this.country.add(new CountryNotification(game, country, timeout));
	}

	public void addMessageNotification(String message, int timeout) {

	}

	public void addItemPickedNotification(String itemName, int timeout) {

	}

	public void update() {
		List<CountryNotification> del = new ArrayList<>();
		for (CountryNotification country : country) {
			if (country.draw(layout, fontWhite, smallFontWhite)) {
				Gdx.app.log(NOTIFICATION_SERVICE, "endNotification");
				del.add(country);
			}
		}
		country.removeAll(del);
	}
	
	public void dispose() {
		Gdx.app.log(NOTIFICATION_SERVICE, "dispose");
		country.clear();
	}
}
