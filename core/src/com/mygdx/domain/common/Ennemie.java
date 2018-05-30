package com.mygdx.domain.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.constante.CollisionConstante;
import com.mygdx.constante.Constante;
import com.mygdx.domain.Level;
import com.mygdx.enumeration.EnnemieStateEnum;
import com.mygdx.enumeration.EnnemieTypeEnum;
import com.mygdx.game.InTheWellGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Ennemie extends BodyAble {
	protected int x;
	protected int y;
	protected EnnemieTypeEnum type;

	protected EnnemieStateEnum state;
	protected Level level;
	private boolean touchBorderRight;
	private boolean touchBorderLeft;
	private boolean touchRight;
	private boolean touchLeft;
	private boolean onPlatformBorderRight;
	private boolean onPlatformBorderLeft;
	private boolean canJumpRight;
	private boolean canJumpLeft;
	private boolean canStepRight;
	private boolean canStepLeft;
	private boolean canLargeStepRight;
	private boolean canLargeStepLeft;
	private boolean canJump;
	private boolean canLargeJump;
	private boolean canGoDown;
	private boolean canGoDownRight;
	private boolean canGoDownLeft;

	public void init(World world, InTheWellGame game, final Level level) {
		this.init(game);
		this.world = world;
		this.level = level;
		createBody();
	}

	public boolean isDead() {
		return false;
	}

	public void kill() {

	}

	public void touchEnnemie(Ennemie o) {
		if (o.getState() == EnnemieStateEnum.ICED && o.getVelocity() > 5) {
			this.kill();
		}
	}

	private float getVelocity() {
		float vx = this.body.getLinearVelocity().x;
		float vy = this.body.getLinearVelocity().y;
		return (float) Math.sqrt((double) ((vx * vx) + (vy * vy)));
	}

	@Override
	public void createBody() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x + 0.5f, y + 0.5f);
		body = world.createBody(bodyDef);
		body.setFixedRotation(true);
		MassData data = new MassData();
		data.mass = 100f;
		body.setMassData(data);
		body.setUserData(this);
		PolygonShape bodyBox = new PolygonShape();
		bodyBox.setAsBox(Constante.ENNEMIE_BOX_WIDTH, Constante.ENNEMIE_BOX_HEIGHT);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = bodyBox;
		fixtureDef.density = 1;
		fixtureDef.restitution = 0f;
		Fixture fixture = body.createFixture(fixtureDef);
		Filter filter = new Filter();
		filter.categoryBits = CollisionConstante.CATEGORY_ENNEMIE;
		fixture.setFilterData(filter);
		bodyBox.dispose();
	}

	public abstract void think();

	protected void initView() {
		Gdx.app.log("Ennemie think !", "");

		Boolean[][] grid = this.level.getGrid();
		int x = (int) body.getPosition().x;
		int y = (int) body.getPosition().y;

		// ennemie touch right border of screen ?
		if (x + 1 < 20) {
			if (grid[x + 1][y]) {
				touchRight = true;
			} else {
				touchRight = false;
			}
			touchBorderRight = false;
			if (y - 1 > 0) {
				onPlatformBorderRight = !grid[x + 1][y - 1];
			} else {
				onPlatformBorderRight = false;
			}
		} else {
			touchBorderRight = true;
			onPlatformBorderRight = false;
		}
		// ennemie touch left border of screen ?
		if (x - 1 > 0) {
			if (grid[x - 1][y]) {
				touchLeft = true;
			} else {
				touchLeft = false;
			}
			touchBorderLeft = false;
			if (y - 1 > 0) {
				onPlatformBorderLeft = !grid[x - 1][y - 1];
			} else {
				onPlatformBorderLeft = false;
			}
		} else {
			touchBorderLeft = true;
			onPlatformBorderLeft = false;
		}

		// ennemie can jump to the right ?
		if (x + 3 < 20 && y - 1 > 0) {
			canJumpRight = !grid[x + 3][y] && grid[x + 3][y - 1] && !grid[x + 1][y - 1];
		} else {
			canJumpRight = false;
		}
		// ennemie can jump to the left ?
		if (x - 3 > 0 && y - 1 > 0) {
			canJumpLeft = !grid[x - 3][y] && grid[x - 3][y - 1] && !grid[x - 1][y - 1];
		} else {
			canJumpLeft = false;
		}

		// ennemie can jump on 1 case step on right ?
		if (x + 1 < 20 && y + 1 < 25) {
			canStepRight = grid[x + 1][y] && !grid[x + 1][y + 1];
		} else {
			canStepRight = false;
		}

		// ennemie can jump on 1 case step on left ?
		if (x - 1 > 0 && y + 1 < 25) {
			canStepLeft = grid[x - 1][y] && !grid[x - 1][y + 1];
		} else {
			canStepLeft = false;
		}

		// ennemie can jump on 1 case step on right ?
		if (x + 1 < 20 && y + 2 < 25) {
			canStepRight = grid[x + 1][y] && !grid[x + 1][y + 2];
		} else {
			canLargeStepRight = false;
		}

		// ennemie can jump on 1 case step on left ?
		if (x - 1 > 0 && y + 2 < 25) {
			canStepLeft = grid[x - 1][y] && !grid[x - 1][y + 2];
		} else {
			canLargeStepLeft = false;
		}

		// ennemie can jump 2 case top himself ?
		if (y + 2 < 25) {
			canJump = grid[x][y + 1] && !grid[x][y + 2];
		} else {
			canJump = false;
		}

		// ennemie can jump 3 case top himself ?
		if (y + 3 < 25) {
			canLargeJump = grid[x][y + 2] && !grid[x][y + 3];
		} else {
			canLargeJump = false;
		}

		// ennemie can go down ?
		if (y - 2 > 0) {
			canGoDown = false;
			for (int yy = y - 2; yy > 0; yy--) {
				if (grid[x][yy]) {
					canGoDown = true;
					break;
				}
			}
		} else {
			canGoDown = false;
		}

		// ennemie can go fall in right border ?

		if (x + 1 < 20 && y - 2 > 0) {
			canGoDownRight = false;
			for (int yy = y - 2; yy > 0; yy--) {
				if (grid[x + 1][yy]) {
					canGoDownRight = true;
					break;
				}
			}
		} else {
			canGoDownRight = false;
		}

		// ennemie can go fall in left border ?
		if (x - 1 > 0 && y - 2 > 0) {
			canGoDownLeft = false;
			for (int yy = y - 2; yy > 0; yy--) {
				if (grid[x - 1][yy]) {
					canGoDownLeft = true;
					break;
				}
			}
		} else {
			canGoDownLeft = false;
		}
	}
}
