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
import com.mygdx.service.notification.ItemPickedNotification;
import com.mygdx.service.notification.MessageNotification;
import com.mygdx.service.notification.NeedItemNotification;
import com.mygdx.service.notification.Notification;

public class NotificationService {

	private static final String NOTIFICATION_SERVICE = "Notification Service";

	private InTheWellGame game;
	private GlyphLayout layout;
	private BitmapFont fontGrey;
	private BitmapFont fontWhite;
	private BitmapFont smallFontWhite;
	private BitmapFont itemFont;

	private List<Notification> notification;

	public NotificationService(InTheWellGame game) {
		this.notification = new ArrayList<>();
		this.game = game;
		this.layout = new GlyphLayout();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_impact.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 12;
		parameter.borderWidth = 1.8f;
		parameter.borderColor = new Color(0, 0, 0, 255);
		parameter.color = new Color(255, 255, 255, 255);
		fontWhite = generator.generateFont(parameter);

		parameter.size = 12;
		parameter.borderWidth = 1.8f;
		parameter.borderColor = new Color(0, 0, 0, 255);
		parameter.color = new Color(0.8f, 0.8f, 0.8f, 1f);
		fontGrey = generator.generateFont(parameter);

		parameter.size = 10;
		parameter.borderWidth = 1f;
		parameter.borderColor = new Color(0, 0, 0, 255);
		parameter.color = new Color(255, 255, 255, 255);
		smallFontWhite = generator.generateFont(parameter);

		generator.dispose();

		generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font_verdana_10pt.ttf"));
		parameter.size = 10;
		parameter.borderWidth = 0.1f;
		parameter.borderColor = new Color(255, 255, 255, 255);
		parameter.color = new Color(255, 255, 255, 255);
		itemFont = generator.generateFont(parameter);
		generator.dispose();
	}

	public void addCountryNotification(String country, int timeout) {
		this.notification.add(new CountryNotification(game, country, timeout));
		this.notification.add(new NeedItemNotification(game, country, timeout));
	}

	public void addMessageNotification(String message, int timeout) {
		this.notification.add(new MessageNotification(game, message, timeout));
	}

	public void addNeedItemNotification(String itemName, int timeout) {
		this.notification.add(new NeedItemNotification(game, itemName, timeout));
	}

	public void addItemPickedNotification(int itemId, String itemName, int timeout) {
		this.notification.add(new ItemPickedNotification(game, itemId, itemName, timeout));
	}

	public void update() {
		List<Notification> del = new ArrayList<>();
		boolean delete = false;
		for (Notification notification : notification) {
			if (notification.getClass() == CountryNotification.class) {
				delete = notification.draw(layout, fontWhite, smallFontWhite);
			} else if (notification.getClass() == ItemPickedNotification.class) {
				delete = notification.draw(layout, itemFont, smallFontWhite);
			} else if (notification.getClass() == MessageNotification.class) {
				delete = notification.draw(layout, itemFont, smallFontWhite);
			} else if (notification.getClass() == NeedItemNotification.class) {
				delete = notification.draw(layout, fontGrey, fontWhite);
			}
			if (delete) {
				Gdx.app.log(NOTIFICATION_SERVICE, "endNotification");
				del.add(notification);
			}
		}
		notification.removeAll(del);
	}

	public void dispose() {
		Gdx.app.log(NOTIFICATION_SERVICE, "dispose");
		notification.clear();
	}
}
