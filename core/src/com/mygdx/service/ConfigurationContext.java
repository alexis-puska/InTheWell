package com.mygdx.service;

import com.mygdx.enumeration.LocaleEnum;

public class ConfigurationContext {

	private static LocaleEnum locale = LocaleEnum.FRENCH;
	private static boolean showFps = true;

	public static LocaleEnum getLocale() {
		return locale;
	}

	public static void setLocale(LocaleEnum locale) {
		ConfigurationContext.locale = locale;
	}

	public static boolean isShowFps() {
		return showFps;
	}

	public static void setShowFps(boolean showFps) {
		ConfigurationContext.showFps = showFps;
	}

}
