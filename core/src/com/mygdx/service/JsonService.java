package com.mygdx.service;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.service.dto.level.LevelFile;
import com.mygdx.service.dto.level.Type;

public class JsonService {

	private static JsonService INSTANCE;

	private final ObjectMapper objectMapper;

	public JsonService() {
		
		/*******************************
		 * --- LEVEL ---
		 *******************************/
		this.objectMapper = new ObjectMapper();
		FileHandle jsonLevel = Gdx.files.internal("json/json_level_parser.json");
		LevelFile levelFile = null;
		try {
			levelFile = objectMapper.readValue(jsonLevel.file(), LevelFile.class);
		} catch (JsonParseException e) {
			Gdx.app.error("LevelService", "JsonParseException : ", e);
		} catch (JsonMappingException e) {
			Gdx.app.error("LevelService", "JsonMappingException : ", e);
		} catch (IOException e) {
			Gdx.app.error("LevelService", "IOException : ", e);
		}
		Gdx.app.log("LevelService", "Nb level type : " + levelFile.getType().size());
		for (Type type : levelFile.getType()) {
			Gdx.app.log("LevelService", "Nb level in this type : " + type.getLevel().size());
		}
	}

	public static JsonService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JsonService();
		}
		return INSTANCE;
	}

}
