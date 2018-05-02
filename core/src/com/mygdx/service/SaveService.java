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

public class SaveService {

	private static SaveService INSTANCE = new SaveService();

	private final static String SAVE_PATH = "InTheWellSave.sav";
	private final static int NB_VALUE_SAVE = 357;
	private final static int NB_SAVE_PER_FILE = 4;

	public static SaveService getInstance() {
		return INSTANCE;
	}

	public SaveService() {
		Path path = Paths.get(SAVE_PATH);
		if (!path.toFile().exists()) {
			Gdx.app.log("SaveService", "File don't exists !");
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(SAVE_PATH));
				for (int i = 0; i < NB_VALUE_SAVE * NB_SAVE_PER_FILE; i++) {
					writer.write(String.format("%08x", 0));
				}
				writer.close();
			} catch (IOException e) {
				Gdx.app.error("SaveService", "SaveService IOException !", e);
			}
		}
		loadAccount(1);
	}

	public void loadAccount(int accountId) {
		Path path = Paths.get(SAVE_PATH);
		if (path.toFile().exists()) {
			Gdx.app.log("SaveService", "File exists !");
			try {
				BufferedReader reader = new BufferedReader(new FileReader(SAVE_PATH));
				String content = reader.readLine();
				Gdx.app.log("reader", content);
				for (int save = 0; save < NB_SAVE_PER_FILE; save++) {
					for (int indexValue = 0; indexValue < NB_VALUE_SAVE; indexValue++) {
						Gdx.app.log("reader", "value " + indexValue + " : "
								+ Long.parseLong(content.substring((indexValue * 8), (indexValue * 8) + 8), 16));
					}
				}
				reader.close();
			} catch (FileNotFoundException e) {
				Gdx.app.error("SaveService", "loadAccount FileNotFoundException ! ", e);
			} catch (IOException e) {
				Gdx.app.error("SaveService", "loadAccount IOException !", e);
			}
		}
	}
}
