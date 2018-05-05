package com.mygdx.service;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.service.dto.level.LevelFileDTO;

public class JsonService {

	private static JsonService INSTANCE;

	private final ObjectMapper objectMapper;

	public JsonService() {

		/*******************************
		 * --- LEVEL ---
		 *******************************/
		this.objectMapper = new ObjectMapper();
		FileHandle levelJsonFile = Gdx.files.internal("json/level.json");
		LevelFileDTO levelFile = null;
		try {
			levelFile = objectMapper.readValue(levelJsonFile.read(), LevelFileDTO.class);
		} catch (JsonParseException e) {
			Gdx.app.error("LevelService", "JsonParseException : ", e);
		} catch (JsonMappingException e) {
			Gdx.app.error("LevelService", "JsonMappingException : ", e);
		} catch (IOException e) {
			Gdx.app.error("LevelService", "IOException : ", e);
		}

	}

	public static JsonService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JsonService();
		}
		return INSTANCE;
	}

}
