package com.mygdx.service;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.domain.Level;
import com.mygdx.enumeration.GameModeEnum;
import com.mygdx.mapper.LevelMapper;
import com.mygdx.service.dto.level.LevelDTO;
import com.mygdx.service.dto.level.LevelFileDTO;

public class LevelService {

	private static final String LEVEL_SERVICE = "Level Service";
	
	private static LevelService INSTANCE;
	private final ObjectMapper objectMapper;
	private final LevelMapper levelMapper;
	private LevelFileDTO levelFile;

	public LevelService() {
		/*******************************
		 * --- LEVEL ---
		 *******************************/
		this.objectMapper = new ObjectMapper();
		levelFile = new LevelFileDTO();
		levelMapper = new LevelMapper();
		FileHandle levelJsonFile = Gdx.files.internal("json/level.json");
		try {
			levelFile = objectMapper.readValue(levelJsonFile.read(), LevelFileDTO.class);
		} catch (JsonParseException e) {
			Gdx.app.error(LEVEL_SERVICE, "JsonParseException : ", e);
		} catch (JsonMappingException e) {
			Gdx.app.error(LEVEL_SERVICE, "JsonMappingException : ", e);
		} catch (IOException e) {
			Gdx.app.error(LEVEL_SERVICE, "IOException : ", e);
		}
		Gdx.app.log(LEVEL_SERVICE, "Nb level loaded : " + levelFile.getType().get(0).getLevel().size());
	}

	public static LevelService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LevelService();
		}
		return INSTANCE;
	}

	public Level getLevel(GameModeEnum mode, int id) {
		LevelDTO dto = null;
		switch (mode) {
		case TUTORIAL:
			dto = levelFile.getType().get(1).getLevel().get(id);
			break;
		case SOCCERFEST:
			dto = levelFile.getType().get(2).getLevel().get(id);
			break;
		case TIME_ATTACK:
			dto = levelFile.getType().get(3).getLevel().get(id);
			break;
		case MULTI_COOPERATIF:
		case SOLO:
		default:
			dto = levelFile.getType().get(0).getLevel().get(id);
			break;
		}
		return levelMapper.toEntity(dto);
	}

}
