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
import com.mygdx.constante.Constante;
import com.mygdx.domain.Account;

public class SaveService {

	private static SaveService INSTANCE = new SaveService();

	public static SaveService getInstance() {
		return INSTANCE;
	}

	public SaveService() {
		initSaveFile();
	}

	/**
	 * Load a saved account
	 * 
	 * @param accountId
	 *            account Id of player
	 */
	public Account loadAccount(int accountId) {
		Account account = new Account(accountId);
		Path path = Paths.get(Constante.SAVE_PATH);
		if (!path.toFile().exists()) {
			initSaveFile();
		}
		String content = readFile();
		int start = accountId * Constante.NB_ITEM_PER_SAVE * 8;
		int stop = start + (Constante.NB_ITEM_PER_SAVE * 8);
		account = new Account(accountId, content.substring(start, stop));
		return account;
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
	public void saveAccount(Account account) {
		Account[] accounts = new Account[4];
		accounts[account.getId()] = account;
		try {
			String content = readFile();
			for (int i = 0; i < Constante.NB_SAVE_PER_FILE; i++) {
				if (i != account.getId()) {
					int start = i * Constante.NB_ITEM_PER_SAVE;
					int stop = start + Constante.NB_ITEM_PER_SAVE;
					accounts[i] = new Account(i, content.substring(start, stop));
				}
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(Constante.SAVE_PATH));
			for (int i = 0; i < Constante.NB_SAVE_PER_FILE; i++) {
				if (i != account.getId()) {
					writer.write(accounts[i].getContent());
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
					writer.write(new Account(i).getContent());
				}
				writer.close();
			} catch (IOException e) {
				Gdx.app.error("SaveService", "Init save file IOException !", e);
			}
		}
	}
}
