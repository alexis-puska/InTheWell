package com.mygdx.domain.event;

import java.util.List;
import java.util.Timer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.domain.Decor;
import com.mygdx.domain.Door;
import com.mygdx.domain.Item;
import com.mygdx.domain.Level;
import com.mygdx.domain.Lock;
import com.mygdx.domain.Pick;
import com.mygdx.domain.Platform;
import com.mygdx.domain.Rayon;
import com.mygdx.domain.Teleporter;
import com.mygdx.domain.Vortex;
import com.mygdx.domain.common.BodyAble;
import com.mygdx.domain.common.Ennemie;
import com.mygdx.enumeration.EventNotificationType;
import com.mygdx.enumeration.MusicEnum;
import com.mygdx.enumeration.SoundEnum;
import com.mygdx.game.InTheWellGame;
import com.mygdx.service.Context;
import com.mygdx.service.SoundService;
import com.mygdx.service.timer.EventTimerTask;

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
	private double x;
	private double y;
	private double dx;
	private double dy;
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
	private MusicEnum song;
	private SoundEnum sound;
	private int darknessValue;
	private int iceValue;

	private Level level;
	private EventTimerTask timerTask;
	private Timer timer;

	public void init(World world, InTheWellGame game, Level level) {
		this.level = level;
		super.init(world, game);
		if (time) {
			timerTask = new EventTimerTask(this);
			timer = new Timer();
			float val = (1000f / 40f) * timeout;
			timer.schedule(timerTask, (int) val);
		}
	}

	@Override
	public void dispose() {
		if (time && !timerTask.isRunned()) {
			timer.cancel();
		}
		if (body != null) {
			this.world.destroyBody(body);
			body = null;
		}
	}

	@Override
	public void createBody() {
		if (near || explosion) {
			BodyDef groundBodyDef = new BodyDef();
			PolygonShape groundBox = new PolygonShape();
			float xb = (float)x + 0.5f;
			float yb = (float)y + 0.5f;
			groundBodyDef.position.set(new Vector2(xb, yb));
			groundBox.setAsBox(0.5f + ((float)dx / 2f), 0.5f + ((float)dy / 2f));
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

	public void enable(EventNotificationType type) {
		switch (type) {
		case BIRTH_PLAYER:
			if (onBirth) {
				enable = true;
			}
			break;
		case DEATH_PLAYER:
			if (onDeath) {
				enable = true;
			}
			break;
		case ENTER_LEVEL:
			if (onLevelEnter) {
				enable = true;
			}
			break;
		case EXPLOSION:
			if (explosion) {
				enable = true;
			}
			break;
		case NEAR:
			if (near) {
				enable = true;
			}
			break;
		case NO_MORE_ENNEMIE:
			if (noMoreEnnemie) {
				enable = true;
			}
			break;
		case TIMEOUT:
			enable = true;
			break;
		default:
			break;
		}

	}

	@Override
	public void update() {
		if (itemId >= 0 && game.getAccountService().getFridgeQuantity(itemId) > 0) {
			trigger();
		} else if (itemId < 0) {
			trigger();
		}
		if (!onlyOnce && triggered) {
			triggered = false;
			enable = false;
		}
	}

	private void trigger() {
		if ((enable && !onlyOnce) || (enable && onlyOnce && !triggered)) {
			for (EnableElement e : this.enableElement) {
				switch (e.getElementType()) {
				case DECOR:
					for (Decor decor : level.getDecor()) {
						if (decor.getId() == e.getId()) {
							decor.setEnable(e.isNewState());
						}
					}
					break;
				case DOOR:
					for (Door door : level.getDoor()) {
						if (door.getId() == e.getId()) {
							door.setEnable(e.isNewState());
						}
					}
					break;
				case ENNEMIE:
					for (Ennemie en : level.getEnnemies()) {
						if (en.getId() == e.getId()) {
							en.setEnable(e.isNewState());
						}
					}
					break;
				case ITEM:
					for (Item i : level.getItems()) {
						if (i.getId() == e.getId()) {
							i.setEnable(e.isNewState());
						}
					}
					break;
				case LOCK:
					for (Lock l : level.getLock()) {
						if (l.getId() == e.getId()) {
							l.setEnable(e.isNewState());
						}
					}
					break;
				case PICK:
					for (Pick pi : level.getPick()) {
						if (pi.getId() == e.getId()) {
							pi.setEnable(e.isNewState());
						}
					}
					break;
				case PLATFORM:
					for (Platform p : level.getPlatform()) {
						if (p.getId() == e.getId()) {
							p.setEnable(e.isNewState());
						}
					}
					break;
				case RAYON:
					for (Rayon r : level.getRayon()) {
						if (r.getId() == e.getId()) {
							r.setEnable(e.isNewState());
						}
					}
					break;
				case TELEPORTER:
					for (Teleporter t : level.getTeleporter()) {
						if (t.getId() == e.getId()) {
							t.setEnable(e.isNewState());
						}
					}
					break;
				case VORTEX:
					for (Vortex v : level.getVortex()) {
						if (v.getId() == e.getId()) {
							v.setEnable(e.isNewState());
						}
					}
					break;
				default:
					break;
				}
			}
			if (song != null) {
				SoundService.getInstance().playMusic(song);
			}
			if (sound != null) {
				SoundService.getInstance().playSound(sound);
			}
			for (Message m : message) {
				switch (Context.getLocale()) {
				case ENGLISH:
					game.getNotificationService().addMessageNotification(m.getEn(), m.getTimeout());
					break;
				case SPANISH:
					game.getNotificationService().addMessageNotification(m.getEs(), m.getTimeout());
					break;
				default:
				case FRENCH:
					game.getNotificationService().addMessageNotification(m.getFr(), m.getTimeout());
					break;
				}
			}
			if (darknessValue != -1) {
				float value = (1f / 255f) * (float) this.darknessValue;
				Context.setDarknessValue(value);
			}
			if (iceValue != -1) {
				for (Platform platform : level.getPlatform()) {
					platform.setFrixion(iceValue);
				}
			}
			triggered = true;
		}
	}
}
