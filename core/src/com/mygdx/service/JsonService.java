package com.mygdx.service;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.service.dto.database.DatabaseDTO;
import com.mygdx.service.dto.level.LevelFileDTO;
import com.mygdx.service.dto.sprite.SpriteFileContent;

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
			levelFile = objectMapper.readValue(levelJsonFile.file(), LevelFileDTO.class);
		} catch (JsonParseException e) {
			Gdx.app.error("LevelService", "JsonParseException : ", e);
		} catch (JsonMappingException e) {
			Gdx.app.error("LevelService", "JsonMappingException : ", e);
		} catch (IOException e) {
			Gdx.app.error("LevelService", "IOException : ", e);
		}

		/*******************************
		 * --- DATABASE ---
		 *******************************/
		FileHandle databaseJsonFile = Gdx.files.internal("json/database.json");
		DatabaseDTO database = null;
		try {
			database = objectMapper.readValue(databaseJsonFile.file(), DatabaseDTO.class);
		} catch (JsonParseException e) {
			Gdx.app.error("LevelService", "JsonParseException : ", e);
		} catch (JsonMappingException e) {
			Gdx.app.error("LevelService", "JsonMappingException : ", e);
		} catch (IOException e) {
			Gdx.app.error("LevelService", "IOException : ", e);
		}

		/*******************************
		 * --- SPRITE ---
		 *******************************/
		FileHandle spriteJsonFile = Gdx.files.internal("json/sprite.json");
		SpriteFileContent spriteFile = null;
		try {
			spriteFile = objectMapper.readValue(spriteJsonFile.file(), SpriteFileContent.class);
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
