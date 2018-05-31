package com.mygdx.service;

import com.mygdx.enumeration.LocaleEnum;

public class Context {

	private static LocaleEnum locale = LocaleEnum.FRENCH;
	private static boolean playSound = true;
	private static boolean showFps = false;
	private static boolean pause = false;
	private static boolean showMap = false;
	private static float darknessValue;
	

	private Context() {
		darknessValue = 0;
	}

	public static LocaleEnum getLocale() {
		return locale;
	}

	public static void setLocale(LocaleEnum locale) {
		Context.locale = locale;
	}
	
	public static boolean isPlaySound() {
		return playSound;
	}

	public static void setPlaySound(boolean playSound) {
		Context.playSound = playSound;
	}

	public static boolean isShowFps() {
		return showFps;
	}

	public static void setShowFps(boolean showFps) {
		Context.showFps = showFps;
	}

	public static boolean isPause() {
		return pause;
	}

	public static void setPause(boolean pause) {
		Context.pause = pause;
	}

	public static boolean isShowMap() {
		return showMap;
	}

	public static void setShowMap(boolean showMap) {
		Context.showMap = showMap;
	}

	public static float getDarknessValue() {
		return darknessValue;
	}

	public static void setDarknessValue(float darknessValue) {
		Context.darknessValue = darknessValue;
	}
	
}
