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
	private FileHandle spriteJsonFile;
	private final ObjectMapper objectMapper;

	/************************
	 * --- Texture ---
	 ************************/
	private Texture spriteAnimation;
	private Texture spriteArrow;
	private Texture spriteBackgroundEffect;
	private Texture spriteBigCrystal;
	private Texture spriteBombe;
	private Texture spriteEnnemies;
	private Texture spriteFlag;
	private Texture spriteLevel;
	private Texture spriteLight;
	private Texture spriteMap;
	private Texture spriteMenu;
	private Texture spriteMessage;
	private Texture spriteObjets;
	private Texture spritePlayer;
	private Texture spriteRayonTeleporter;
	private Texture spriteShadow;

	public static SpriteService getInstance() {
		return INSTANCE;
	}

	public SpriteService() {
		Gdx.app.log("SpriteService", "Init");

		spriteAnimation = new Texture(Gdx.files.internal("sprite/sprite_animation.png"));
		spriteArrow = new Texture(Gdx.files.internal("sprite/sprite_arrow.png"));
		spriteBackgroundEffect = new Texture(Gdx.files.internal("sprite/sprite_background_effect.png"));
		spriteBigCrystal = new Texture(Gdx.files.internal("sprite/sprite_big_crystal.png"));
		spriteBombe = new Texture(Gdx.files.internal("sprite/sprite_bombe.png"));
		spriteEnnemies = new Texture(Gdx.files.internal("sprite/sprite_ennemies.png"));
		spriteFlag = new Texture(Gdx.files.internal("sprite/sprite_flag.png"));
		spriteLevel = new Texture(Gdx.files.internal("sprite/sprite_level.png"));
		spriteLight = new Texture(Gdx.files.internal("sprite/sprite_light.png"));
		spriteMap = new Texture(Gdx.files.internal("sprite/sprite_map.png"));
		spriteMenu = new Texture(Gdx.files.internal("sprite/sprite_menu.png"));
		spriteMessage = new Texture(Gdx.files.internal("sprite/sprite_message.png"));
		spriteObjets = new Texture(Gdx.files.internal("sprite/sprite_objets.png"));
		spritePlayer = new Texture(Gdx.files.internal("sprite/sprite_player.png"));
		spriteRayonTeleporter = new Texture(Gdx.files.internal("sprite/sprite_rayon_teleporter.png"));
		spriteShadow = new Texture(Gdx.files.internal("sprite/sprite_shadow.png"));

		spriteJsonFile = Gdx.files.internal("json/sprite.json");
		sprites = new HashMap<>();
		this.objectMapper = new ObjectMapper();

		int nbSprite = 0;
		SpriteFileContent spriteFileContent = null;

		try {
			spriteFileContent = objectMapper.readValue(spriteJsonFile.read(), SpriteFileContent.class);
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

				if (sprites.containsKey(animation)) {
					sprites.put(animation, mergeTextureRegion(sprites.get(animation), regions));
				} else {
					sprites.put(animation, regions);
				}
			}
		}
		Gdx.app.log("SpriteService", "Nb sprites loaded : " + nbSprite);
	}

	private TextureRegion[] mergeTextureRegion(TextureRegion[] textureRegions, TextureRegion[] regions) {
		int size = textureRegions.length + regions.length;
		TextureRegion[] merged = new TextureRegion[size];
		int idx = 0;
		for (int i = 0; i < textureRegions.length; i++) {
			merged[idx] = textureRegions[i];
			idx++;
		}
		for (int i = 0; i < regions.length; i++) {
			merged[idx] = regions[i];
			idx++;
		}
		return merged;
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
		default:
			return spriteAnimation;
		}
	}

}
