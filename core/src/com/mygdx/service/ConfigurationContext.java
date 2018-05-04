package com.mygdx.service;

import com.mygdx.constante.Constante;
import com.mygdx.enumeration.LocaleEnum;

public class ConfigurationContext {

	private static LocaleEnum locale = LocaleEnum.FRENCH;
	private static boolean showFps = true;
	private static int indexAccount;

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

	public static int getIndexAccount() {
		return indexAccount;
	}

	public static void setIndexAccount(int indexAccount) {
		ConfigurationContext.indexAccount = indexAccount;
	}

	public static void decIndexAccount() {
		if (indexAccount > 0) {
			indexAccount--;
		}
	}

	public static void incIndexAccount() {
		if (indexAccount < Constante.NB_SAVE_PER_FILE - 1) {
			indexAccount++;
		}
	}

}
