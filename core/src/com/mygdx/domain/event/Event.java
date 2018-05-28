package com.mygdx.domain.event;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.domain.Level;
import com.mygdx.domain.Platform;
import com.mygdx.domain.Player;
import com.mygdx.domain.common.BodyAble;
import com.mygdx.game.InTheWellGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event extends BodyAble {

	private int id;
	private boolean onlyOnce;
	private boolean triggered;

	// declanchement par proximité
	private boolean near;
	private int x;
	private int y;
	private int d;
	private int itemId;

	// declanchement par decompte
	private boolean time;
	private int timeout;

	// condition declanchement
	private boolean explosion;
	private boolean noMoreEnnemie; // plus d'ennemie dans le niveau
	private boolean onBirth; // spawn joueur
	private boolean onDeath; // mort du joueur
	private boolean onLevelEnter;

	// option
	private boolean mirror;
	private boolean nightmare;
	private boolean timeAttackeOption;
	private boolean multiOption;
	private boolean ninja;

	/***************************************
	 * ACTION
	 ***************************************/
	private List<EnableElement> enableElement;
	private List<Message> message;
	private String song;
	private String sound;
	private int darknessValue;
	private int iceValue;

	private Level level;

	public void init(World world, InTheWellGame game, Level level) {
		this.level = level;
		super.init(world, game);
	}

	@Override
	public void createBody() {
		if (near || explosion) {
			BodyDef groundBodyDef = new BodyDef();
			PolygonShape groundBox = new PolygonShape();
			float xb = x + 0.5f;
			float yb = y + 0.5f;
			groundBodyDef.position.set(new Vector2(xb, yb));
			groundBox.setAsBox(0.5f + (d / 2f), 0.5f + (d / 2f));
			groundBodyDef.position.set(new Vector2(xb, yb));
			body = world.createBody(groundBodyDef);
			Fixture fixture = body.createFixture(groundBox, 0.0f);
			body.setUserData(this);
			groundBox.dispose();
			Filter filter = new Filter();
			filter.categoryBits = CollisionConstante.CATEGORY_EVENT;
			fixture.setFilterData(filter);
			fixture.setFriction(0.1f);
		}
	}

	@Override
	public void drawIt() {
		// Unused for event. Event is invisible
	}

	public void setLevel(final Level level) {
		this.level = level;
	}

	@Override
	public void enable() {
		// enable event is only with explosion or player.
	}

	public void enable(Player player) {
		if (near) {
			enable = true;
		}
	}

	@Override
	public void update() {
		if (enable) {
			for (EnableElement e : this.enableElement) {
				switch (e.getElementType()) {
				case DECOR:
					break;
				case DOOR:
					break;
				case ENNEMIE:
					break;
				case ITEM:
					break;
				case LOCK:
					break;
				case PICK:
					break;
				case PLATFORM:
					for (Platform p : level.getPlatform()) {
						if (p.getId() == e.getId()) {
							p.setEnable(e.isNewState());
						}
					}
					break;
				case RAYON:
					break;
				case TELEPORTER:
					break;
				case VORTEX:
					break;
				default:
					break;

				}
			}
		}
	}

}
