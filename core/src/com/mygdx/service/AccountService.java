package com.mygdx.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.constante.Constante;
import com.mygdx.enumeration.GameKeyEnum;
import com.mygdx.enumeration.GameModeEnum;
import com.mygdx.enumeration.GameOptionEnum;
import com.mygdx.enumeration.GameSoccerMapEnum;
import com.mygdx.service.dto.database.DatabaseDTO;
import com.mygdx.service.dto.database.FamilyDTO;
import com.mygdx.service.dto.database.ItemDTO;
import com.mygdx.service.dto.database.QuestDTO;
import com.mygdx.service.dto.database.RequiredDTO;

public class AccountService {

	private static final String LOG_NAME = "AccountService";

	private final ObjectMapper objectMapper;

	/***********
	 * ACCOUNT
	 **********/
	private int accountId;
	private long nbGame;
	private long score;
	private long level;
	private long[] fridge;
	private long[] gameFridge;
	private int gameScore;

	private List<GameOptionEnum> availableOption;
	private List<GameModeEnum> availableMode;
	private List<GameKeyEnum> availableKey;
	private int life;
	private int light;

	/***********
	 * PARAMETER
	 ***********/
	private GameModeEnum gameModeSelected;
	private List<GameOptionEnum> gameOptionSelected;
	private GameSoccerMapEnum gameSoccerMapSelected;

	/***********
	 * DATABASE
	 ***********/

	private DatabaseDTO database;
	private Map<Integer, ItemDTO> mapItemPoint;

	private Map<Integer, QuestDTO> questStarted;
	private Map<Integer, QuestDTO> questCompleted;

	private List<Integer> availableItemEffect0;
	private List<Integer> availableItemEffect1;
	private List<Integer> availableItemEffect2;
	private List<Integer> availableItemEffect3;
	private List<Integer> availableItemEffect4;
	private List<Integer> availableItemEffect5;
	private List<Integer> availableItemEffect6;

	private List<Integer> availableItemPoint0;
	private List<Integer> availableItemPoint1;
	private List<Integer> availableItemPoint2;
	private List<Integer> availableItemPoint3;
	private List<Integer> availableItemPoint4;
	private List<Integer> availableItemPoint5;
	private List<Integer> availableItemPoint6;
	private List<Integer> availableItemPoint7;

	/**
	 * create a new empty account
	 * 
	 * @param id
	 *            id of account
	 */
	public AccountService() {
		Gdx.app.log(LOG_NAME, "Init");

		questStarted = new HashMap<>();
		questCompleted = new HashMap<>();
		availableOption = new ArrayList<>();
		availableMode = new ArrayList<>();
		availableKey = new ArrayList<>();
		availableItemEffect0 = new ArrayList<>();
		availableItemEffect1 = new ArrayList<>();
		availableItemEffect2 = new ArrayList<>();
		availableItemEffect3 = new ArrayList<>();
		availableItemEffect4 = new ArrayList<>();
		availableItemEffect5 = new ArrayList<>();
		availableItemEffect6 = new ArrayList<>();
		availableItemPoint0 = new ArrayList<>();
		availableItemPoint1 = new ArrayList<>();
		availableItemPoint2 = new ArrayList<>();
		availableItemPoint3 = new ArrayList<>();
		availableItemPoint4 = new ArrayList<>();
		availableItemPoint5 = new ArrayList<>();
		availableItemPoint6 = new ArrayList<>();
		availableItemPoint7 = new ArrayList<>();
		mapItemPoint = new HashMap<>();

		this.objectMapper = new ObjectMapper();
		this.accountId = -1;
		this.nbGame = 0;
		this.score = 0;
		this.level = 0;
		this.fridge = new long[Constante.NB_ITEM_FRIDGE];
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			this.fridge[i] = 0;
		}
		this.life = 2;
		this.light = 0;
		this.gameModeSelected = GameModeEnum.SOLO;
		this.gameOptionSelected = new ArrayList<>();
		this.gameSoccerMapSelected = GameSoccerMapEnum.GAZON_MAUDIT;
		loadDatabase();
	}

	/*************************************************
	 * ---------------- ACCOUNT PART ----------------
	 *************************************************
	 * return the String reprentation to write in save file
	 * 
	 * @return content to write
	 */
	private String getContent() {
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			content.append(String.format("%08x", fridge[i]));
		}
		content.append(String.format("%08x", nbGame));
		content.append(String.format("%08x", score));
		content.append(String.format("%08x", level));
		return content.toString();
	}

	/**
	 * Create a saving file if not exist one. Function must be called at the game
	 * launch
	 */
	private void initSaveFile() {
		Path path = Paths.get(Constante.SAVE_PATH);
		if (!path.toFile().exists()) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constante.SAVE_PATH))) {
				for (int i = 0; i < Constante.NB_SAVE_PER_FILE; i++) {
					writer.write(createEmptyAccountContent());
				}

			} catch (IOException e) {
				Gdx.app.error(LOG_NAME, "Init save file IOException !", e);
			}
		}
	}

	/**
	 * return the String reprentation of a new empty account
	 * 
	 * @return content to write
	 */
	private String createEmptyAccountContent() {
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			content.append(String.format("%08x", 0));
		}
		content.append(String.format("%08x", 0));
		content.append(String.format("%08x", 0));
		content.append(String.format("%08x", 0));
		return content.toString();
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
		if (content != null && !content.isEmpty()) {
			int offset = accountId * Constante.NB_ITEM_PER_SAVE * 8;

			// init value
			this.accountId = accountId;
			this.fridge = new long[Constante.NB_ITEM_FRIDGE];
			this.gameFridge = new long[Constante.NB_ITEM_FRIDGE];
			for (int i = offset; i < (accountId + 1 * Constante.NB_ITEM_FRIDGE); i++) {
				fridge[i] = Long.parseLong(content.substring((i * 8), (i * 8) + 8), 16);
			}
			nbGame = Long.parseLong(content.substring(offset + (354 * 8), offset + (354 * 8) + 8), 16);
			score = Long.parseLong(content.substring(offset + (355 * 8), offset + (355 * 8) + 8), 16);
			level = Long.parseLong(content.substring(offset + (356 * 8), offset + (356 * 8) + 8), 16);
			initAvailableItems();
		}
	}

	/**
	 * Read the save file
	 * 
	 * @return content of the save file
	 * 
	 */
	private String readFile() {
		String content = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(Constante.SAVE_PATH))) {
			content = reader.readLine();

		} catch (FileNotFoundException e) {
			Gdx.app.error(LOG_NAME, "loadAccount FileNotFoundException ! ", e);
		} catch (IOException e) {
			Gdx.app.error(LOG_NAME, "loadAccount IOException !", e);
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
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constante.SAVE_PATH))) {
			String[] accounts = new String[4];
			String content = readFile();
			if (content != null && !content.isEmpty()) {
				for (int i = 0; i < Constante.NB_SAVE_PER_FILE; i++) {
					if (i != this.accountId) {
						int start = i * Constante.NB_ITEM_PER_SAVE;
						int stop = start + Constante.NB_ITEM_PER_SAVE;
						accounts[i] = content.substring(start, stop);
					}
				}
				for (int i = 0; i < Constante.NB_SAVE_PER_FILE; i++) {
					if (i != accountId) {
						writer.write(accounts[i]);
					} else {
						writer.write(getContent());
					}
				}
			}
		} catch (IOException e) {
			Gdx.app.error(LOG_NAME, "saveAccount IOException !", e);
		}
	}

	/*************************************************
	 * ---------------- DATABASE PART ----------------
	 *************************************************/
	private void loadDatabase() {
		FileHandle databaseJsonFile = Gdx.files.internal("json/database.json");
		try {
			database = objectMapper.readValue(databaseJsonFile.read(), DatabaseDTO.class);
		} catch (JsonParseException e) {
			Gdx.app.error(LOG_NAME, "JsonParseException : ", e);
		} catch (JsonMappingException e) {
			Gdx.app.error(LOG_NAME, "JsonMappingException : ", e);
		} catch (IOException e) {
			Gdx.app.error(LOG_NAME, "IOException : ", e);
		}

		// build mapItemPoint
		for (ItemDTO dto : database.getItems()) {
			mapItemPoint.put(dto.getId(), dto);
		}
	}

	/**
	 * Initialise les listes d'objet débloqué et récupérable en jeu pour un compte
	 * chargé.
	 */
	private void initAvailableItems() {
		List<Integer> familyAvailable = new ArrayList<>();
		List<Integer> familyToRemove = new ArrayList<>();
		resetAll();

		familyAvailable.add(0);
		familyAvailable.add(7);
		familyAvailable.add(13);
		familyAvailable.add(20);
		familyAvailable.add(48);
		// fridge[304]=1;
		// fridge[308]=1;
		// fridge[310]=1;
		// fridge[312]=1;
		// fridge[315]=1;
		// diamant
		// fridge[126]=30;
		// fridge[118]=60;

		/********************
		 * validation quête
		 ********************/

		for (QuestDTO quest : database.getQuests()) {
			if (Constante.DEBUG) {
				Gdx.app.log(LOG_NAME, "validation quete : " + quest.getId());
			}

			boolean valide = true;
			boolean started = false;

			for (RequiredDTO required : quest.getRequire()) {
				if (fridge[required.getId()] > 0) {
					started = true;
				}
				if (fridge[required.getId()] < required.getVal()) {
					valide = false;
					break;
				}
			}
			if (valide) {

				for (Integer family : quest.getFamily()) {
					familyAvailable.add(family);
				}
				if (quest.getRemove() != -1) {
					familyToRemove.add(quest.getRemove());
				}
				questCompleted.put(quest.getId(), quest);
				unlockSomething(quest);
			}
			if (started && !valide) {
				questStarted.put(quest.getId(), quest);
			}

		}
		familyAvailable.removeAll(familyToRemove);

		/**************************
		 * fill available object with unlocked familly
		 **************************/

		for (int familyId : familyAvailable) {
			FamilyDTO family = database.getFamilys().get(familyId);
			for (int itemId : family.getItems()) {
				ItemDTO item = database.getItems().get(itemId);
				if (item.getValue() == -1) {
					switch (item.getRarity()) {
					case 0:
						availableItemEffect0.add(item.getId());
						break;
					case 1:
						availableItemEffect1.add(item.getId());
						break;
					case 2:
						availableItemEffect2.add(item.getId());
						break;
					case 3:
						availableItemEffect3.add(item.getId());
						break;
					case 4:
						availableItemEffect4.add(item.getId());
						break;
					case 5:
						availableItemEffect5.add(item.getId());
						break;
					case 6:
						availableItemEffect6.add(item.getId());
						break;
					default:
					}
				} else {
					switch (item.getRarity()) {
					case 0:
						availableItemPoint0.add(item.getId());
						break;
					case 1:
						availableItemPoint1.add(item.getId());
						break;
					case 2:
						availableItemPoint2.add(item.getId());
						break;
					case 3:
						availableItemPoint3.add(item.getId());
						break;
					case 4:
						availableItemPoint4.add(item.getId());
						break;
					case 5:
						availableItemPoint5.add(item.getId());
						break;
					case 6:
						availableItemPoint6.add(item.getId());
						break;
					case 7:
						availableItemPoint7.add(item.getId());
						break;
					default:
					}
				}
			}
		}

		if (Constante.DEBUG) {
			Gdx.app.log(LOG_NAME, "FAMILLE DISPONIBLE");
			for (Integer i : familyAvailable) {
				Gdx.app.log(LOG_NAME, database.getFamilys().get(i).getName().getFr());
			}

			Gdx.app.log(LOG_NAME, "QUEST STARTED DISPONIBLE");
			for (QuestDTO q : questStarted.values()) {
				Gdx.app.log(LOG_NAME, q.getTitre().getFr() + " " + q.getId());
			}

			Gdx.app.log(LOG_NAME, "QUEST COMPLETE DISPONIBLE");
			for (QuestDTO q : questCompleted.values()) {
				Gdx.app.log(LOG_NAME, q.getTitre().getFr() + " " + q.getId());
			}

			Gdx.app.log(LOG_NAME, "base item load");
			Gdx.app.log(LOG_NAME, "base available point 7");
			StringBuilder tmp = new StringBuilder();
			for (int i : availableItemPoint7) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available point 6");
			tmp = new StringBuilder();
			for (int i : availableItemPoint6) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available point 5");
			tmp = new StringBuilder();
			for (int i : availableItemPoint5) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available point 4");
			tmp = new StringBuilder();
			for (int i : availableItemPoint4) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available point 3");
			tmp = new StringBuilder();
			for (int i : availableItemPoint3) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available point 2");
			tmp = new StringBuilder();
			for (int i : availableItemPoint2) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available point 1");
			tmp = new StringBuilder();
			for (int i : availableItemPoint1) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available point 0");
			tmp = new StringBuilder();
			for (int i : availableItemPoint0) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available effect 6");
			tmp = new StringBuilder();
			for (int i : availableItemEffect6) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available effect 5");
			tmp = new StringBuilder();
			for (int i : availableItemEffect5) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available effect 4");
			tmp = new StringBuilder();
			for (int i : availableItemEffect4) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available effect 3");
			tmp = new StringBuilder();
			for (int i : availableItemEffect3) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available effect 2");
			tmp = new StringBuilder();
			for (int i : availableItemEffect2) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available effect 1");
			tmp = new StringBuilder();
			for (int i : availableItemEffect1) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
			Gdx.app.log(LOG_NAME, "base available effect 0");
			tmp = new StringBuilder();
			for (int i : availableItemEffect0) {
				tmp.append(i + " ");
			}
			Gdx.app.log(LOG_NAME, tmp.toString());
		}
	}

	/**
	 * Pour une quête déboqué et validé, débloque les option, mode, clé utilisable
	 * dans les menus ou en jeu
	 * 
	 * @param quest
	 *            une quete débloquée et validée
	 */
	private void unlockSomething(QuestDTO quest) {
		if (quest.getMode() != null) {
			this.availableMode.add(quest.getMode());
		}
		if (quest.getOption() != null) {
			this.availableOption.add(quest.getOption());
		}
		if (quest.getKey() != null) {
			this.availableKey.add(quest.getKey());
		}
		if (quest.isLife()) {
			this.life++;
		}
		if (quest.isLight()) {
			this.light++;
		}
	}

	public long getFridgeQuantity(int id) {
		return this.fridge[id];
	}

	public String getItemName(int id) {
		switch (Context.getLocale()) {
		case ENGLISH:
			return this.database.getItems().get(id).getName().getEn();
		case FRENCH:
			return this.database.getItems().get(id).getName().getFr();
		case SPANISH:
			return this.database.getItems().get(id).getName().getEs();
		default:
			return this.database.getItems().get(id).getName().getFr();
		}
	}

	public String getQuestName(int id) {
		switch (Context.getLocale()) {
		case ENGLISH:
			return this.database.getQuests().get(id).getTitre().getEn();
		case FRENCH:
			return this.database.getQuests().get(id).getTitre().getFr();
		case SPANISH:
			return this.database.getQuests().get(id).getTitre().getEs();
		default:
			return this.database.getQuests().get(id).getTitre().getFr();
		}
	}

	public String getQuestDescription(int id) {
		switch (Context.getLocale()) {
		case ENGLISH:
			return this.database.getQuests().get(id).getDescription().getEn();
		case FRENCH:
			return this.database.getQuests().get(id).getDescription().getFr();
		case SPANISH:
			return this.database.getQuests().get(id).getDescription().getEs();
		default:
			return this.database.getQuests().get(id).getDescription().getFr();
		}
	}

	public Map<Integer, QuestDTO> getQuestStarted() {
		return questStarted;
	}

	public Map<Integer, QuestDTO> getQuestCompleted() {
		return questCompleted;
	}

	public QuestDTO getQuestDTO(int index) {
		return database.getQuests().get(index);
	}

	/********************
@formatter:off
void ItemFileSystem::simulateGame()
{

	for (int i = 0; i < 103; i++)
	{
		int point = getEffectItemId();
		int effect = getPointItemId();

		fridge[point] = fridge[point] + 1;
		fridge[effect] = fridge[effect] + 1;
		std::cout << "level " << i << " - effect " << effect << " - point " << point << "\n";
	}
}
@formatter:on
*/

	private void resetAll() {
		questStarted.clear();
		questCompleted.clear();
		availableItemEffect0.clear();
		availableItemEffect1.clear();
		availableItemEffect2.clear();
		availableItemEffect3.clear();
		availableItemEffect4.clear();
		availableItemEffect5.clear();
		availableItemEffect6.clear();
		availableItemPoint0.clear();
		availableItemPoint1.clear();
		availableItemPoint2.clear();
		availableItemPoint3.clear();
		availableItemPoint4.clear();
		availableItemPoint5.clear();
		availableItemPoint6.clear();
		availableItemPoint7.clear();
	}

	/*************************************************
	 * ---------------- GAME LIFEPART ----------------
	 *************************************************
	 * increment one object for this account
	 * 
	 * @param index
	 *            id of object to increment
	 */
	public boolean addItemInGameFridge(int index) {
		boolean pointObject = false;
		int point = this.mapItemPoint.get(index).getValue();
		if (point > 0) {
			pointObject = true;
			gameScore += point;
		}
		this.gameFridge[index]++;
		Gdx.app.log(LOG_NAME, "score : " + gameScore);
		return pointObject;
	}

	/**
	 * 
	 */
	public void newGame() {
		this.gameScore = 0;
		this.gameFridge = new long[Constante.NB_ITEM_FRIDGE];
	}

	public long getGameScore() {
		return this.gameScore;
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
	public void endGame(int lastGameLevel) {
		if (this.score < gameScore) {
			this.score = gameScore;
		}
		if (this.level < lastGameLevel) {
			this.level = lastGameLevel;
		}
		for (int i = 0; i < Constante.NB_ITEM_FRIDGE; i++) {
			this.fridge[i] += this.gameFridge[i];
		}
		this.saveAccount();
		this.gameFridge = new long[Constante.NB_ITEM_FRIDGE];
	}

	/**
	 * Fonction tirant un nombre aléatoire entre 0 et max
	 * 
	 * @param max
	 *            nombre macimum pour le tirage
	 * @return nombre aléatoire
	 */
	private int random(int max) {
		return ThreadLocalRandom.current().nextInt(0, max);
	}

	/**
	 * Fonction de jeu Retourne l'id d'un objet à effet aléatoirement
	 * 
	 * @return Id de l'objet
	 */
	public int getEffectItemId() {
		int itemId = -1;
		int randCoef = 0;
		while (itemId == -1) {
			randCoef = random(Constante.PROBA_COEF7);
			if (randCoef <= Constante.PROBA_COEF1 && !availableItemEffect1.isEmpty()) {
				return availableItemEffect1.get(random(availableItemEffect1.size()));
			}
			if (randCoef <= Constante.PROBA_COEF2 && availableItemEffect2.isEmpty()) {
				return availableItemEffect2.get(random(availableItemEffect2.size()));
			}
			if (randCoef <= Constante.PROBA_COEF3 && !availableItemEffect3.isEmpty()) {
				return availableItemEffect3.get(random(availableItemEffect3.size()));
			}
			if (randCoef <= Constante.PROBA_COEF4 && !availableItemEffect4.isEmpty()) {
				return availableItemEffect4.get(random(availableItemEffect4.size()));
			}
			if (randCoef <= Constante.PROBA_COEF5 && !availableItemEffect5.isEmpty()) {
				return availableItemEffect5.get(random(availableItemEffect5.size()));
			}
			if (randCoef <= Constante.PROBA_COEF6 && !availableItemEffect6.isEmpty()) {
				return availableItemEffect6.get(random(availableItemEffect6.size()));
			}
		}
		return itemId;
	}

	/**
	 * Fonction de jeu Retourne l'id d'un objet à point aléatoirement
	 * 
	 * @return Id de l'objet
	 */
	public int getPointItemId() {
		int itemId = -1;
		int randCoef = 0;
		while (itemId == -1) {
			randCoef = random(Constante.PROBA_COEF7);
			if (randCoef <= Constante.PROBA_COEF1 && !availableItemPoint1.isEmpty()) {
				return availableItemPoint1.get(random(availableItemPoint1.size()));
			} else if (randCoef <= Constante.PROBA_COEF2 && !availableItemPoint2.isEmpty()) {
				return availableItemPoint2.get(random(availableItemPoint2.size()));
			} else if (randCoef <= Constante.PROBA_COEF3 && !availableItemPoint3.isEmpty()) {
				return availableItemPoint3.get(random(availableItemPoint3.size()));
			} else if (randCoef <= Constante.PROBA_COEF4 && !availableItemPoint4.isEmpty()) {
				return availableItemPoint4.get(random(availableItemPoint4.size()));
			} else if (randCoef <= Constante.PROBA_COEF5 && !availableItemPoint5.isEmpty()) {
				return availableItemPoint5.get(random(availableItemPoint5.size()));
			} else if (randCoef <= Constante.PROBA_COEF6 && !availableItemPoint6.isEmpty()) {
				return availableItemPoint6.get(random(availableItemPoint6.size()));
			} else if (randCoef <= Constante.PROBA_COEF7 && !availableItemPoint7.isEmpty()) {
				return availableItemPoint7.get(random(availableItemPoint7.size()));
			}
		}
		return itemId;
	}

	/**
	 * @return nombre de vie disponible pour une partie
	 */
	public int getLifeForGame() {
		return this.life;
	}

	/**
	 * @return nombre de lumière débloqué pour ce compte
	 */
	public int getLightForGame() {
		return this.light;
	}

	/**************************************************
	 * --- CONFIGURATION ---
	 **************************************************/
	public List<GameOptionEnum> getAvailableOption() {
		return availableOption;
	}

	public List<GameModeEnum> getAvailableMode() {
		return availableMode;
	}

	public GameModeEnum getGameModeSelected() {
		return gameModeSelected;
	}

	public void setGameModeSelected(GameModeEnum gameModeSelected) {
		this.gameModeSelected = gameModeSelected;
	}

	public List<GameOptionEnum> getGameOptionSelected() {
		return gameOptionSelected;
	}

	public void addGameOptionSelected(GameOptionEnum gameOptionSelected) {
		this.gameOptionSelected.add(gameOptionSelected);
	}

	public void removeGameOptionSelected(GameOptionEnum gameOptionSelected) {
		this.gameOptionSelected.remove(gameOptionSelected);
	}

	public void resetGameOptionSelected() {
		this.gameOptionSelected.clear();
	}

	public GameSoccerMapEnum getGameSoccerMapSelected() {
		return gameSoccerMapSelected;
	}

	public void setGameSoccerMapSelected(GameSoccerMapEnum gameSoccerMapSelected) {
		this.gameSoccerMapSelected = gameSoccerMapSelected;
	}

}
