package com.mygdx.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.constante.Constante;
import com.mygdx.service.dto.database.DatabaseDTO;

public class AccountService {

	private final ObjectMapper objectMapper;

	private int accountId;
	private long nbGame;
	private long score;
	private long level;
	private Long[] fridge;
	private Long[] gameFridge;

	/**
	 * create a new empty account
	 * 
	 * @param id
	 *            id of account
	 */
	public AccountService() {
		Gdx.app.log("AccountService", "Init");
		this.objectMapper = new ObjectMapper();
		this.accountId = -1;
		this.nbGame = 0;
		this.score = 0;
		this.level = 0;
		this.fridge = new Long[Constante.NB_ITEM_FRIDGE];
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			this.fridge[i] = 0l;
		}
		loadDatabase();
	}

	/**
	 * return the String reprentation to write in save file
	 * 
	 * @return content to write
	 */
	private String getContent() {
		String content = "";
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			content += String.format("%08x", fridge[i]);
		}
		content += String.format("%08x", nbGame);
		content += String.format("%08x", score);
		content += String.format("%08x", level);
		return content;
	}

	/**
	 * return the String reprentation of a new empty account
	 * 
	 * @return content to write
	 */
	private String createEmptyAccountContent() {
		String content = "";
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			content += String.format("%08x", 0);
		}
		content += String.format("%08x", 0);
		content += String.format("%08x", 0);
		content += String.format("%08x", 0);
		return content;
	}

	/**
	 * Load a saved account
	 * 
	 * @param accountId
	 *            account Id of player
	 */
	public void loadAccount(int accountId) {
		Path path = Paths.get(Constante.SAVE_PATH);
		if (!path.toFile().exists()) {
			initSaveFile();
		}
		String content = readFile();
		int offset = accountId * Constante.NB_ITEM_PER_SAVE * 8;

		// init value
		this.accountId = accountId;
		this.fridge = new Long[Constante.NB_ITEM_FRIDGE];
		this.gameFridge = new Long[Constante.NB_ITEM_FRIDGE];
		for (int i = offset; i < (accountId * Constante.NB_ITEM_FRIDGE); i++) {
			fridge[i] = Long.parseLong(content.substring((i * 8), (i * 8) + 8), 16);
		}
		nbGame = Long.parseLong(content.substring(offset + (354 * 8), offset + (354 * 8) + 8), 16);
		score = Long.parseLong(content.substring(offset + (355 * 8), offset + (355 * 8) + 8), 16);
		level = Long.parseLong(content.substring(offset + (356 * 8), offset + (356 * 8) + 8), 16);
	}

	/**
	 * Read the save file
	 * 
	 * @return content of the save file
	 * 
	 */
	private String readFile() {
		String content = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Constante.SAVE_PATH));
			content = reader.readLine();
			reader.close();
		} catch (FileNotFoundException e) {
			Gdx.app.error("SaveService", "loadAccount FileNotFoundException ! ", e);
		} catch (IOException e) {
			Gdx.app.error("SaveService", "loadAccount IOException !", e);
		}
		return content;
	}

	/**
	 * Save progression player into the save file
	 * 
	 * @param account
	 *            account of player
	 */
	public void saveAccount() {
		try {
			String accounts[] = new String[4];
			String content = readFile();
			for (int i = 0; i < Constante.NB_SAVE_PER_FILE; i++) {
				if (i != this.accountId) {
					int start = i * Constante.NB_ITEM_PER_SAVE;
					int stop = start + Constante.NB_ITEM_PER_SAVE;
					accounts[i] = content.substring(start, stop);
				}
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(Constante.SAVE_PATH));
			for (int i = 0; i < Constante.NB_SAVE_PER_FILE; i++) {
				if (i != accountId) {
					writer.write(accounts[i]);
				} else {
					writer.write(getContent());
				}
			}
			writer.close();
		} catch (IOException e) {
			Gdx.app.error("SaveService", "saveAccount IOException !", e);
		}
	}

	/**
	 * Create a saving file if not exist one. Function must be called at the game
	 * launch
	 */
	private void initSaveFile() {
		Path path = Paths.get(Constante.SAVE_PATH);
		if (!path.toFile().exists()) {
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(Constante.SAVE_PATH));
				for (int i = 0; i < Constante.NB_SAVE_PER_FILE; i++) {
					writer.write(createEmptyAccountContent());
				}
				writer.close();
			} catch (IOException e) {
				Gdx.app.error("SaveService", "Init save file IOException !", e);
			}
		}
	}

	/*************************************************
	 * ---------------- DATABASE PART ----------------
	 *************************************************/
	private void loadDatabase() {
		FileHandle databaseJsonFile = Gdx.files.internal("json/database.json");
		DatabaseDTO database = null;
		try {
			database = objectMapper.readValue(databaseJsonFile.read(), DatabaseDTO.class);
		} catch (JsonParseException e) {
			Gdx.app.error("LevelService", "JsonParseException : ", e);
		} catch (JsonMappingException e) {
			Gdx.app.error("LevelService", "JsonMappingException : ", e);
		} catch (IOException e) {
			Gdx.app.error("LevelService", "IOException : ", e);
		}
	}

	/*************************************************
	 * ---------------- GAME LIFEPART ----------------
	 *************************************************
	 * increment one object for this accoune
	 * 
	 * @param index
	 *            id of object to increment
	 */
	public void addItemInGameFridge(int index) {
		this.gameFridge[index]++;
	}

	/**
	 * @formatter:off
	 * At end of a game: 
	 * - update score 
	 * - update max level reached 
	 * - transfert item from the game fridge to the fridge 
	 * - save 
	 * - reset game fringe for an other game
	 * 
	 * @param score the score of game
	 * @param level the last level reached
	 * @formatter:on
	 */
	public void endGame(long score, long level) {
		if (this.score < score) {
			this.score = score;
		}
		if (this.level < level) {
			this.level = level;
		}
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			this.fridge[i] += this.gameFridge[i];
		}
		this.saveAccount();
		this.gameFridge = new Long[Constante.NB_ITEM_FRIDGE];
	}
}
