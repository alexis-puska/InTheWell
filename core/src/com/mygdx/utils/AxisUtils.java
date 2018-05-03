package com.mygdx.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.constante.Constante;

public class AxisUtils {

	public static int invert(int y, Texture texture) {
		return (Constante.SCREEN_SIZE_Y - y - texture.getHeight());
	}

	public static int invert(int y, Sprite sprite) {
		return (Constante.SCREEN_SIZE_Y - y - (int) sprite.getHeight());
	}

	public static int invertText(int y) {
		return Constante.SCREEN_SIZE_Y - y;
	}
}
