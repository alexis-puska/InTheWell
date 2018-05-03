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
	private static LocaleEnum currentLocale;
	private static I18NBundle message;

	/**
	 * Init the service
	 */
	private static void init() {
		if (!init) {
			baseFileHandle = Gdx.files.internal("i18n/message");
			currentLocale = ConfigurationContext.getLocale();
			message = I18NBundle.createBundle(baseFileHandle, new Locale(currentLocale.getCode()));
			init = true;
		}
	}

	/**
	 * Update Locale if the context change
	 */
	private static void checkLocaleChange() {
		if (ConfigurationContext.getLocale() != currentLocale) {
			currentLocale = ConfigurationContext.getLocale();
			message = I18NBundle.createBundle(baseFileHandle, new Locale(currentLocale.getCode()));
		}
	}

	/**
	 * @param key
	 *            searched translation key
	 * @return the translation
	 */
	public static String getMessage(String key) {
		init();
		checkLocaleChange();
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
		checkLocaleChange();
		return message.format(key, args);
	}
}
