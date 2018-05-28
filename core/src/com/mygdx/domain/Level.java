package com.mygdx.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.constante.Constante;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.domain.event.Event;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.SpriteService;

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

	private List<Body> borderWall;
	private Timer timeObjetEffet;
	private CustomTimerTask objectPointTask;
	private Timer timeObjetPoint;
	private CustomTimerTask objectEffetTask;

	public void init(World world, InTheWellGame game) {
		this.game = game;
		this.world = world;
		borderWall = new ArrayList<>();
		borderWall.add(createBorderWall(world, -1));
		borderWall.add(createBorderWall(world, 20));
		timeObjetEffet = new Timer();
		timeObjetPoint = new Timer();
		objectPointTask = new CustomTimerTask(false, this, 0);
		objectEffetTask = new CustomTimerTask(false, this, 1);
		timeObjetEffet.schedule(objectPointTask, 5000);
		timeObjetPoint.schedule(objectEffetTask, 10000);

		for (Decor d : decor) {
			d.init(game);
		}
		for (Event ev : event) {
			ev.init(world);
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
			pl.init(world, game, verticalPlateform, horizontalPlateform, showPlatform);
		}
		for (Rayon r : rayon) {
			r.init(world, game);
		}
		for (Teleporter t : teleporter) {
			t.init(world, game);
		}
		for (Vortex v : vortex) {
			v.init(world, game);
		}
		for (Ennemie e : ennemies) {
			e.init(world, game);
		}
		for (Item i : items) {
			i.init(world, game);
		}
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
		// destroy lateral walls
		for (Body b : borderWall) {
			this.world.destroyBody(b);
		}
	}

	private Body createBorderWall(World world, int x) {
		BodyDef bodyDef = new BodyDef();
		PolygonShape polygoneShape = new PolygonShape();
		bodyDef.position.set(new Vector2(x + 0.5f, 12));
		polygoneShape.setAsBox(0.5f, 24);
		Body body = world.createBody(bodyDef);
		Fixture fixture = body.createFixture(polygoneShape, 0.0f);
		polygoneShape.dispose();
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_PLATFORM;
		fixture.setFilterData(filter);
		fixture.setFriction(0.1f);
		return body;
	}

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
		}
		for (Item i : items) {
			i.update();
		}
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

}
