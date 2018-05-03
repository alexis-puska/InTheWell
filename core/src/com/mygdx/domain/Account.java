package com.mygdx.domain;

import com.mygdx.constante.Constante;

import lombok.Getter;

@Getter
public class Account {

	private int id;
	private long nbGame;
	private long score;
	private long level;
	private Long[] fridge;

	/**
	 * create a new empty account
	 * 
	 * @param id
	 *            id of account
	 */
	public Account(int id) {
		this.id = id;
		this.nbGame = 0;
		this.score = 0;
		this.level = 0;
		this.fridge = new Long[Constante.NB_ITEM_FRIDGE];
	}

	/**
	 * Init account at loading save
	 * 
	 * @param id
	 *            id of save account
	 * @param content
	 *            content inside file
	 */
	public Account(int id, String content) {
		this.id = id;
		this.fridge = new Long[Constante.NB_ITEM_FRIDGE];
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			fridge[i] = Long.parseLong(content.substring((i * 8), (i * 8) + 8), 16);
		}
		nbGame = Long.parseLong(content.substring((354 * 8), (354 * 8) + 8), 16);
		score = Long.parseLong(content.substring((355 * 8), (355 * 8) + 8), 16);
		level = Long.parseLong(content.substring((356 * 8), (356 * 8) + 8), 16);
	}

	/**
	 * increment one object for this accoune
	 * 
	 * @param index
	 *            id of object to increment
	 */
	public void addItemInFridge(int index) {
		this.fridge[index]++;
	}

	/**
	 * At end of a game, update score and max level reached
	 * 
	 * @param score
	 *            the score of game
	 * @param level
	 *            the last level reached
	 */
	public void endGame(long score, long level) {
		if (this.score < score) {
			this.score = score;
		}
		if (this.level < level) {
			this.level = level;
		}
	}

	/**
	 * return the String reprentation to write in save file
	 * 
	 * @return content to write
	 */
	public String getContent() {
		String content = "";
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			content += String.format("%08x", fridge[i]);
		}
		content += String.format("%08x", nbGame);
		content += String.format("%08x", score);
		content += String.format("%08x", level);
		return content;
	}
}
