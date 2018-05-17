package com.mygdx.domain;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.CollisionConstante;
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

	private List<Body> borderWall;

	public void init(World world) {
		borderWall = new ArrayList<>();
		borderWall.add(createBorderWall(world, -1));
		borderWall.add(createBorderWall(world, 20));
		for (Event ev : event) {
			ev.init(world);
		}
		for (Door d : door) {
			d.init(world);
		}
		for (Lock l : lock) {
			l.init(world);
		}
		for (Pick p : pick) {
			p.init(world);
		}
		for (Platform pl : platform) {
			pl.init(world);
		}
		for (Rayon r : rayon) {
			r.init(world);
		}
		for (Teleporter t : teleporter) {
			t.init(world);
		}
		for (Vortex v : vortex) {
			v.init(world);
		}
		for (Ennemie e : ennemies) {
			e.init(world);
		}
		for (Item i : items) {
			i.init(world);
		}
	}

	public void dispose(World world) {
		for (Event ev : event) {
			ev.init(world);
		}
		for (Door d : door) {
			d.init(world);
		}
		for (Lock l : lock) {
			l.init(world);
		}
		for (Pick p : pick) {
			p.init(world);
		}
		for (Platform pl : platform) {
			pl.dispose(world);
		}
		for (Rayon r : rayon) {
			r.init(world);
		}
		for (Teleporter t : teleporter) {
			t.init(world);
		}
		for (Vortex v : vortex) {
			v.init(world);
		}
		for (Ennemie e : ennemies) {
			e.init(world);
		}
		for (Item i : items) {
			i.init(world);
		}
		for (Body b : borderWall) {
			world.destroyBody(b);
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

}
