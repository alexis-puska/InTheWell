package com.mygdx.service;

import com.mygdx.enumeration.LocaleEnum;

public class Context {

	private static LocaleEnum locale = LocaleEnum.FRENCH;
	private static boolean showFps = true;

	public static LocaleEnum getLocale() {
		return locale;
	}

	public static void setLocale(LocaleEnum locale) {
		Context.locale = locale;
	}

	public static boolean isShowFps() {
		return showFps;
	}

	public static void setShowFps(boolean showFps) {
		Context.showFps = showFps;
	}
}
