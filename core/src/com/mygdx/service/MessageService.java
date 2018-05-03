package com.mygdx.service;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;
import com.mygdx.enumeration.LocaleEnum;

/**
 * @author apuskarczyk return specific translation for a code.
 */
public class MessageService {
	private static boolean init = false;
	private static FileHandle baseFileHandle;
	private static Locale currentLocale;
	private static I18NBundle message;

	/**
	 * Init the service
	 */
	private static void init() {
		if (!init) {
			baseFileHandle = Gdx.files.internal("i18n/message");
			currentLocale = new Locale(LocaleEnum.FRENCH.getCode());
			message = I18NBundle.createBundle(baseFileHandle, currentLocale);
			init = true;
		}
	}

	/**
	 * change the language of the service
	 * 
	 * @param locale
	 *            the new Locale
	 */
	public static void changeLocale(LocaleEnum locale) {
		init();
		currentLocale = new Locale(locale.getCode());
		message = I18NBundle.createBundle(baseFileHandle, currentLocale);
	}

	/**
	 * @param key
	 *            searched translation key
	 * @return the translation
	 */
	public static String getMessage(String key) {
		init();
		return message.get(key);
	}

	/**
	 * @param key
	 *            searched translation key
	 * @param args
	 *            parameters
	 * @return the translation
	 */
	public static String getMessage(String key, Object... args) {
		init();
		return message.format(key, args);
	}
}
