package com.mygdx.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.service.dto.sprite.Sprite;
import com.mygdx.service.dto.sprite.SpriteFile;
import com.mygdx.service.dto.sprite.SpriteFileContent;

/**
 * Read sprite file and build structure
 * 
 * @author alexispuskarczyk
 */
public class SpriteService {

	private static SpriteService INSTANCE = new SpriteService();
	private Map<String, TextureRegion[]> sprites;

	/************************
	 * --- JSON ---
	 ************************/
	private FileHandle spriteJsonFile = Gdx.files.internal("json/sprite.json");
	private final ObjectMapper objectMapper;

	/************************
	 * --- Texture ---
	 ************************/
	private Texture spriteAnimation = new Texture(Gdx.files.internal("sprite/sprite_animation.png"));
	private Texture spriteArrow = new Texture(Gdx.files.internal("sprite/sprite_arrow.png"));
	private Texture spriteBackgroundEffect = new Texture(Gdx.files.internal("sprite/sprite_background_effect.png"));
	private Texture spriteBigCrystal = new Texture(Gdx.files.internal("sprite/sprite_big_crystal.png"));
	private Texture spriteBombe = new Texture(Gdx.files.internal("sprite/sprite_bombe.png"));
	private Texture spriteEnnemies = new Texture(Gdx.files.internal("sprite/sprite_ennemies.png"));
	private Texture spriteFlag = new Texture(Gdx.files.internal("sprite/sprite_flag.png"));
	private Texture spriteLevel = new Texture(Gdx.files.internal("sprite/sprite_level.png"));
	private Texture spriteLight = new Texture(Gdx.files.internal("sprite/sprite_light.png"));
	private Texture spriteMap = new Texture(Gdx.files.internal("sprite/sprite_map.png"));
	private Texture spriteMenu = new Texture(Gdx.files.internal("sprite/sprite_menu.png"));
	private Texture spriteMessage = new Texture(Gdx.files.internal("sprite/sprite_message.png"));
	private Texture spriteObjets = new Texture(Gdx.files.internal("sprite/sprite_objets.png"));
	private Texture spritePlayer = new Texture(Gdx.files.internal("sprite/sprite_player.png"));
	private Texture spriteRayonTeleporter = new Texture(Gdx.files.internal("sprite/sprite_rayon_teleporter.png"));
	private Texture spriteShadow = new Texture(Gdx.files.internal("sprite/sprite_shadow.png"));

	public static SpriteService getInstance() {
		return INSTANCE;
	}

	public SpriteService() {
		int nbSprite = 0;
		sprites = new HashMap<>();
		SpriteFileContent spriteFileContent = null;
		this.objectMapper = new ObjectMapper();
		try {
			spriteFileContent = objectMapper.readValue(spriteJsonFile.file(), SpriteFileContent.class);
		} catch (JsonParseException e) {
			Gdx.app.error("LevelService", "JsonParseException : ", e);
		} catch (JsonMappingException e) {
			Gdx.app.error("LevelService", "JsonMappingException : ", e);
		} catch (IOException e) {
			Gdx.app.error("LevelService", "IOException : ", e);
		}
		List<SpriteFile> spriteFileList = spriteFileContent.getSpriteFile();
		for (SpriteFile spriteFile : spriteFileList) {
			Texture texture = textureFromName(spriteFile.getFile());
			List<Sprite> area = spriteFile.getArea();
			for (Sprite sprite : area) {
				int idx = 0;
				String animation = sprite.getAnimation();
				TextureRegion[] regions = new TextureRegion[sprite.getN()];
				Gdx.app.log("SpriteService", "Loaded animation : " + animation + ", n :" + sprite.getN());
				for (int l = 0; l < sprite.getNy(); l++) {
					for (int k = 0; k < sprite.getNx(); k++) {
						regions[idx] = new TextureRegion(texture, sprite.getX() + (k * sprite.getSx()),
								sprite.getY() + (l * sprite.getSy()), sprite.getSx(), sprite.getSy());
						idx++;
						nbSprite++;
						if (idx >= sprite.getN()) {
							break;
						}

					}
				}
				sprites.put(animation, regions);
			}
		}
		Gdx.app.log("SpriteService", "Nb sprites loaded : " + nbSprite);
	}

	public TextureRegion getTexture(String name, int idx) {
		TextureRegion t[] = sprites.get(name);
		return t[idx];
	}

	public TextureRegion[] getTexture(String name) {
		return sprites.get(name);
	}

	private Texture textureFromName(String name) {
		switch (name) {
		case "sprite_animation":
			return spriteAnimation;
		case "sprite_arrow":
			return spriteArrow;
		case "sprite_background_effect":
			return spriteBackgroundEffect;
		case "sprite_big_crystal":
			return spriteBigCrystal;
		case "sprite_bombe":
			return spriteBombe;
		case "sprite_ennemies":
			return spriteEnnemies;
		case "sprite_level":
			return spriteLevel;
		case "sprite_light":
			return spriteLight;
		case "sprite_map":
			return spriteMap;
		case "sprite_objets":
			return spriteObjets;
		case "sprite_player":
			return spritePlayer;
		case "sprite_rayon_teleporter":
			return spriteRayonTeleporter;
		case "image_menu":
			return spriteMenu;
		case "sprite_flag":
			return spriteFlag;
		case "sprite_shadow":
			return spriteShadow;
		case "sprite_message":
			return spriteMessage;
		}
		return spriteAnimation;
	}

	@Override
	public void finalize() {
		spriteAnimation.dispose();
		spriteArrow.dispose();
		spriteBackgroundEffect.dispose();
		spriteBigCrystal.dispose();
		spriteBombe.dispose();
		spriteEnnemies.dispose();
		spriteFlag.dispose();
		spriteLevel.dispose();
		spriteLight.dispose();
		spriteMap.dispose();
		spriteMenu.dispose();
		spriteMessage.dispose();
		spriteObjets.dispose();
		spritePlayer.dispose();
		spriteRayonTeleporter.dispose();
		spriteShadow.dispose();
	}
}
