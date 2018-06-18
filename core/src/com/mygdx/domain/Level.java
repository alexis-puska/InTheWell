package com.mygdx.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.domain.event.Event;
import com.mygdx.enumeration.EventNotificationType;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.SpriteService;
import com.mygdx.service.timer.LevelObjectTimerTask;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Level {

	private World world;
	private InTheWellGame game;

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

	private Timer timeObjetEffet;
	private LevelObjectTimerTask objectPointTask;
	private Timer timeObjetPoint;
	private LevelObjectTimerTask objectEffetTask;
	private Boolean[][] grid;

	public void init(World world, InTheWellGame game) {
		this.game = game;
		this.world = world;
		initGrid();

		timeObjetEffet = new Timer();
		timeObjetPoint = new Timer();
		objectPointTask = new LevelObjectTimerTask(false, this, 0);
		objectEffetTask = new LevelObjectTimerTask(false, this, 1);
		timeObjetEffet.schedule(objectEffetTask, 10000);
		timeObjetPoint.schedule(objectPointTask, 5000);

		createWall();

		for (Decor d : decor) {
			d.init(game);
		}
		for (LevelName na : name) {
			na.init(game);
		}
		for (Door d : door) {
			d.init(world, game);
		}
		for (Lock l : lock) {
			l.init(world, game);
		}
		for (Pick p : pick) {
			p.init(world, game);
		}
		for (Platform pl : platform) {
			pl.init(world, game, verticalPlateform, horizontalPlateform, showPlatform, this);
		}
		for (Rayon r : rayon) {
			r.init(world, game);
		}
		for (Teleporter t : teleporter) {
			t.init(world, game, teleporter);
		}
		for (Vortex v : vortex) {
			v.init(world, game);
		}
		for (Ennemie e : ennemies) {
			e.init(world, game, this);
		}
		for (Item i : items) {
			i.init(world, game);
		}
		for (Event ev : event) {
			ev.init(world, game, this);
		}
		
		this.notifyEvent(EventNotificationType.ENTER_LEVEL);
	}

	private void createWall() {
		Platform wallLeft = new Platform();
		wallLeft.setId(-1);
		wallLeft.setX(-1);
		wallLeft.setY(-1);
		wallLeft.setVertical(true);
		wallLeft.setDisplayed(false);
		wallLeft.setLength(30);
		wallLeft.setEnable(true);
		Platform wallRight = new Platform();
		wallRight.setId(-1);
		wallRight.setX(20);
		wallRight.setY(-1);
		wallRight.setVertical(true);
		wallRight.setDisplayed(false);
		wallRight.setLength(30);
		wallRight.setEnable(true);
		platform.add(wallLeft);
		platform.add(wallRight);
	}

	public void dispose() {
		for (Event ev : event) {
			ev.dispose();
		}
		for (Door d : door) {
			d.dispose();
		}
		for (Lock l : lock) {
			l.dispose();
		}
		for (Pick p : pick) {
			p.dispose();
		}
		for (Platform pl : platform) {
			pl.dispose();
		}
		for (Rayon r : rayon) {
			r.dispose();
		}
		for (Teleporter t : teleporter) {
			t.dispose();
		}
		for (Vortex v : vortex) {
			v.dispose();
		}
		for (Ennemie e : ennemies) {
			e.dispose();
		}
		for (Item i : items) {
			i.dispose();
		}
		if (!objectPointTask.isRunned()) {
			timeObjetPoint.cancel();
		}
		if (!objectEffetTask.isRunned()) {
			timeObjetEffet.cancel();
		}
	}

	/**********************************************************************************
	 * Update all element of level Requiered because change properties of body box2d
	 * element when a collision be throw cause crash of box2d library. need to play
	 * with enable/disable attribute and update the state after (create body/
	 * dispose body)
	 **********************************************************************************/
	public void update() {
		for (Door d : door) {
			d.update();
		}
		for (Lock l : lock) {
			l.update();
		}
		for (Pick p : pick) {
			p.update();
		}
		for (Platform pl : platform) {
			pl.update();
		}
		for (Rayon r : rayon) {
			r.update();
		}
		for (Teleporter t : teleporter) {
			t.update();
		}
		for (Vortex v : vortex) {
			v.update();
		}
		for (Ennemie e : ennemies) {
			e.update();
			e.think();
		}
		for (Item i : items) {
			i.update();
		}
		for (Event e : event) {
			e.update();
		}
		if (this.allEnnemiesOutOrDead()) {
			this.notifyEvent(EventNotificationType.NO_MORE_ENNEMIE);
		}
		initGrid();
	}

	public void drawOnPlayerLayer() {
		for (Ennemie e : ennemies) {
			e.drawIt();
		}
		for (LevelName na : name) {
			na.drawIt();
		}
		for (Item i : items) {
			i.drawIt();
		}
	}

	public void drawOnFrontLayer() {
		for (Decor d : decor) {
			if (!d.isBack()) {
				d.drawIt();
			}
		}
	}

	public void drawOnPlatformLayer() {
		for (Platform pl : platform) {
			pl.drawShadow();
		}
		for (Platform pl : platform) {
			pl.drawIt();
		}
		for (Door d : door) {
			d.drawIt();
		}
		for (Lock l : lock) {
			l.drawIt();
		}
		for (Pick p : pick) {
			p.drawIt();
		}
		for (Rayon r : rayon) {
			r.drawIt();
		}
		for (Teleporter t : teleporter) {
			t.drawIt();
		}
		for (Vortex v : vortex) {
			v.drawIt();
		}
	}

	public void drawBackground() {
		TextureRegion textureRegionBackground = SpriteService.getInstance().getTexture("level_background",
				this.getBackground());
		int idx = 0;
		int idy = 0;
		while (idy < Constante.SCREEN_SIZE_Y) {
			while (idx < Constante.SCREEN_SIZE_X) {
				game.getBatch().draw(textureRegionBackground, idx, idy);
				idx += textureRegionBackground.getRegionWidth();
			}
			idy += textureRegionBackground.getRegionHeight();
			idx = 0;
		}
		for (Decor d : decor) {
			if (d.isBack()) {
				d.drawIt();
			}
		}
	}

	public boolean allEnnemiesOutOrDead() {
		for (Ennemie e : ennemies) {
			if (!e.isDead()) {
				return false;
			}
		}
		return true;
	}

	public void addObjectPoint() {
		if (this.startPointObjets != null) {
			Item item = new Item();
			item.setItemId(game.getAccountService().getPointItemId());
			item.setEnable(true);
			item.setX(startPointObjets.getX());
			item.setY(startPointObjets.getY());
			item.init(world, game);
			item.setTimeout(600);
			item.setDraw(true);
			items.add(item);
		}
	}

	public void addObjectEffet() {
		if (this.startEffectObjets != null) {
			Item item = new Item();
			item.setItemId(game.getAccountService().getEffectItemId());
			item.setEnable(true);
			item.setX(startEffectObjets.getX());
			item.setY(startEffectObjets.getY());
			item.init(world, game);
			item.setTimeout(600);
			item.setDraw(true);
			items.add(item);
		}
	}

	public void notifyEvent(EventNotificationType type) {
		for (Event e : event) {
			e.enable(type);
		}
	}

	private void initGrid() {
		grid = new Boolean[20][25];
		for (Boolean[] row : grid) {
			Arrays.fill(row, Boolean.FALSE);
		}
		for (Platform pl : platform) {
			if (pl.getId() != -1 && pl.isEnable()) {
				if (pl.isVertical()) {
					for (int y = pl.getY(); y < pl.getY() + pl.getLength(); y++) {
						grid[pl.getX()][y] = true;
					}
				} else {
					for (int x = pl.getX(); x < pl.getX() + pl.getLength(); x++) {
						grid[x][pl.getY()] = true;
					}
				}
			}
		}
	}

	public void updateGrid(Boolean val, boolean vertical, int x, int y, int length) {
		if (vertical) {
			for (int i = y; i < y + length; i++) {
				grid[x][i] = val;
			}
		} else {
			for (int i = x; i < x + length; i++) {
				grid[i][y] = val;
			}
		}
	}
}
