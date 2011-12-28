package com.yesnote.mr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.yesnote.mr.Object.Meteor;
import com.yesnote.mr.Object.Missile;
import com.yesnote.mr.Object.UFO;

public class World {
	public interface WorldListener {
		public void hit();
	}

	private static final String TAG = "World";
	public static final float WORLD_WIDTH = Starter.screenWidth;
	public static final float WORLD_HEIGHT = Starter.screenHeight;

	public final WorldListener listener;
	public final Random rand;

	public final UFO ufo;
	public final List<Meteor> meteors;
	public final List<Missile> missiles;

	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;

	public int state;

	public World(WorldListener listener) {
		this.ufo = new UFO(208, 363, 0, 0);
		this.meteors = new ArrayList<Meteor>();
		this.missiles = new ArrayList<Missile>();
		rand = new Random();
		this.listener = listener;
		generateMeteor();
		generateMissile();
		this.state = WORLD_STATE_RUNNING;
		Log.d(TAG, "World is running");

	}

	public void generateMeteor() {
		// Needs modify for a smooth hard curve
		float randomWidth = rand.nextFloat()
				* (World.WORLD_WIDTH - Meteor.METEOR_WIDTH);
		float x = randomWidth;
		float y = WORLD_HEIGHT;
		float degree = 0;
		int type = rand.nextInt() % 6;
		Meteor meteor = new Meteor(x, y, degree, type);
		meteor.velocity.set(0, Meteor.METEOR_VELOCITY);
		meteors.add(meteor);

	}

	public void generateMissile() {
		// Vector2 temp = new Vector2(ufo.position.x, ufo.position.y);

		float x = rand.nextFloat() * World.WORLD_WIDTH;
		float y = 0;
		float degree = 0;
		int type = rand.nextInt() % 6;
		Missile missile = new Missile(x, y, degree, type);
		float vY = Missile.MISSILE_VELOCITY + rand.nextFloat()
				* Missile.MISSILE_VELOCITY / 2;
		missile.velocity.set(0, vY);
		missile.state = Missile.MISSILE_STATE_GO;
		missiles.add(missile);

	}

	public void update(float deltaTime, float accelX, float accelY) {
		updateUFO(deltaTime, accelX, accelY);
		updateMeteor(deltaTime);
		updateMissile(deltaTime);
		if (ufo.state != UFO.ROCKET_STATE_HIT) {
			checkCollisions();
		}
		checkGameOver();
	}

	private void updateUFO(float deltaTime, float accelX, float accelY) {
		UfoController ufoControler = new UfoController();
		ufoControler.control(ufo, accelX, accelY);
		ufo.update(deltaTime);
	}

	private void updateMissile(float deltaTime) {
		int len = missiles.size();
		for (int i = 0; i < len; i++) {
			Missile missile = missiles.get(i);
			Vector2 temp = ufo.position;
			if (missile.position.x > temp.x + (UFO.ROCKET_WIDTH / 2)) {
				missile.velocity.x = (float) (-Missile.MISSILE_VELOCITY * 0.8);
			} else if (missile.position.x < temp.x + (UFO.ROCKET_WIDTH / 2)) {
				missile.velocity.x = (float) (Missile.MISSILE_VELOCITY * 0.8);
			} else {
				missile.velocity.x = 0;
			}
			/*
			 * Vector2 direction = new Vector2(0, 0); direction.x = (float)
			 * Math.sin(6.28 * missile.degree / 360); direction.y = (float)
			 * Math.cos(6.28 * missile.degree / 360);
			 * missile.velocity.set(direction.mul(Missile.MISSILE_VELOCITY));
			 */
			missile.update(deltaTime);
			if (missile.state == Missile.MISSILE_STATE_HIT
					|| missile.position.y > WORLD_HEIGHT) {
				missiles.remove(i);
				len = missiles.size();
			}
		}
		int x = (int) ufo.stateTime;
		int y = 1 + (x / 30);
		if (missiles.size() < y) {
			generateMissile();
		}
	}

	private void updateMeteor(float deltaTime) {
		int len = meteors.size();
		for (int i = 0; i < len; i++) {
			Meteor meteor = meteors.get(i);
			meteor.update(deltaTime);
			if (meteor.position.y < -Meteor.METEOR_HEIGHT) {
				meteors.remove(i);
				len = meteors.size();
			}
		}
		int x = (int) ufo.stateTime;
		int y = 1 + (x / 15);
		if (meteors.size() < y) {
			generateMeteor();
		}
	}

	private void checkCollisions() {
		// Check whether UFO hit meteor or missile
		checkUFOHitShit();
		// Check whether Missile hit the meteor
		checkMissileHitMeteor();
	}

	private void checkMissileHitMeteor() {
		int missileLen = missiles.size();
		for (int i = 0; i < missileLen; i++) {
			int meteorLen = meteors.size();
			for (int j = 0; j < meteorLen; j++) {
				Missile missile = missiles.get(i);
				Meteor meteor = meteors.get(j);
				if (missile.position.y <= meteor.position.y) {
					if (BoundryTester.RectangleBoundryTest(missile.bounds,
							meteor.bounds)) {
						missile.state = Missile.MISSILE_STATE_HIT;
						meteors.remove(j);
						meteorLen = meteors.size();
					}
				}
			}
			missileLen = missiles.size();
		}
	}

	private void checkUFOHitShit() {
		int meteorLen = meteors.size();
		for (int i = 0; i < meteorLen; i++) {
			Meteor meteor = meteors.get(i);
			if (ufo.position.y >= meteor.position.y) {
				if (BoundryTester.RectangleBoundryTest(ufo.bounds,
						meteor.bounds)) {
					ufo.hitShit();
					break;
				}
			}
		}
		int missileLen = missiles.size();
		for (int i = 0; i < missileLen; i++) {
			Missile missile = missiles.get(i);
			if (missile.position.y >= ufo.position.y) {
				if (BoundryTester.RectangleBoundryTest(missile.bounds,
						ufo.bounds)) {
					ufo.hitShit();
					break;
				}
			}
		}
	}

	private void checkGameOver() {
		// TODO Auto-generated method stub
	}
}
