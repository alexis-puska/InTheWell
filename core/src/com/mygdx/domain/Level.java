package com.mygdx.domain;

import java.util.List;

import com.mygdx.domain.ennemie.Ennemie;
import com.mygdx.domain.event.Event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Level {

	private int id;
	private int next;
	private boolean showPlatform;
	private int background;
	private int verticalPlateform;
	private int horizontalPlateform;
	private List<LevelName> name;
	private List<Decor> decor;
	private List<Event> event;
	private List<Door> door;
	private List<Lock> lock;
	private List<Pick> pick;
	private List<Platform> platform;
	private List<Rayon> rayon;
	private List<Teleporter> teleporter;
	private List<Vortex> vortex;
	private List<Ennemie> ennemies;
	private Position startPlayers;
	private Position startEffectObjets;
	private Position startPointObjets;
	private List<Item> items;

}
