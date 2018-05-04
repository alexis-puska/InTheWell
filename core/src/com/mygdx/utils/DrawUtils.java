package com.mygdx.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.constante.Constante;
import com.mygdx.service.SpriteService;

public class DrawUtils {

	public static int invert(int y, Texture texture) {
		return (Constante.SCREEN_SIZE_Y - y - texture.getHeight());
	}

	public static int invert(int y, TextureRegion textureRegion) {
		return (Constante.SCREEN_SIZE_Y - y - textureRegion.getRegionHeight());
	}

	public static int invert(int y, Sprite sprite) {
		return (Constante.SCREEN_SIZE_Y - y - (int) sprite.getHeight());
	}

	public static int invertText(int y) {
		return Constante.SCREEN_SIZE_Y - y;
	}

	public static void fillBackground(SpriteBatch batch, String name) {
		TextureRegion textureRegionBackground = SpriteService.getInstance().getTexture(name, 0);
		int idx = 0;
		int idy = 0;
		while (idy < Constante.SCREEN_SIZE_Y) {
			while (idx < Constante.SCREEN_SIZE_X) {
				batch.draw(textureRegionBackground, idx, DrawUtils.invert(idy, textureRegionBackground));
				idx += textureRegionBackground.getRegionWidth();
			}
			idy += textureRegionBackground.getRegionHeight();
			idx = 0;
		}
	}
}
