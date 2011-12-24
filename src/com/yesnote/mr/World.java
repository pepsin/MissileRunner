package com.yesnote.mr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.yesnote.mr.Object.Meteor;
import com.yesnote.mr.Object.Missile;
import com.yesnote.mr.Object.Rocket;

public class World {
	public interface WorldListener {
		public void hit();
	}

	private static final String TAG = "World";
	public static final float WORLD_WIDTH = Starter.screenWidth;
	public static final float WORLD_HEIGHT = Starter.screenHeight;

	public final WorldListener listener;
	public final Random rand;

	public final Rocket rocket;
	public final List<Meteor> meteors;
	public final List<Missile> missiles;

	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;

	public int state;

	public World(WorldListener listener) {
		this.rocket = new Rocket(208, 363, 0, 0);
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
		float randomWidth = rand.nextFloat() * (World.WORLD_WIDTH - Meteor.METEOR_WIDTH);
		float x = randomWidth;
		float y = WORLD_HEIGHT;
		float degree = 0;
		int type = rand.nextInt() % 6;
		Meteor meteor = new Meteor(x, y, degree, type);
		meteor.velocity.set(0, Meteor.METEOR_VELOCITY);
		meteors.add(meteor);

	}

	public void generateMissile() {
		// Vector2 temp = new Vector2(rocket.position.x, rocket.position.y);

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
		updateRocket(deltaTime, accelX, accelY);
		updateMeteor(deltaTime);
		updateMissile(deltaTime);
		if (rocket.state != Rocket.ROCKET_STATE_HIT) {
			checkCollisions();
		}
		checkGameOver();
	}

	private void updateRocket(float deltaTime, float accelX, float accelY) {
		if (rocket.state != Rocket.ROCKET_STATE_HIT) {
			double x; // Use this to change the x axis's velocity,in case the
						// velocity in the demand direction is not change
			if (accelX <= 5 && accelX > 1) {
				rocket.degree = 30;
			} else if (accelX > 5 && accelX <= 10) {
				rocket.degree = 45;
			} else if (accelX < -1 && accelX >= -5) {
				rocket.degree = -30;
			} else if (accelX < -5 && accelX >= -10) {
				rocket.degree = -45;
			} else {
				rocket.degree = 0;
			}
			x = Math.sin(6.28 * rocket.degree / 360); // Change the degree into
														// radian.
			rocket.velocity.x = -(float) (3 * x * Rocket.ROCKET_VELOCITY);

			if (accelY >= -5 && accelY < 5) {
				rocket.velocity.y = Rocket.ROCKET_VELOCITY;
			} else if (accelY >= 5 && accelY <= 10 || accelY < -5
					&& accelY > -10) {
				rocket.velocity.y = -Rocket.ROCKET_VELOCITY;
			} else {
				rocket.velocity.y = 0;
			}
		}
		rocket.update(deltaTime);
	}

	private void updateMissile(float deltaTime) {
		int len = missiles.size();
		for (int i = 0; i < len; i++) {
			Missile missile = missiles.get(i);
			Vector2 temp = rocket.position;
			if (missile.position.x > temp.x + (Rocket.ROCKET_WIDTH / 2)) {
				missile.velocity.x = (float) (-Missile.MISSILE_VELOCITY * 0.8);
			} else if (missile.position.x < temp.x + (Rocket.ROCKET_WIDTH / 2)) {
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
		int x = (int) rocket.stateTime;
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
			if (meteor.position.y < - Meteor.METEOR_HEIGHT) {
				meteors.remove(i);
				len = meteors.size();
			}
		}
		int x = (int) rocket.stateTime;
		int y = 1 + (x / 15);
		if (meteors.size() < y) {
			generateMeteor();
		}
	}

	private void checkCollisions() {
		// Check whether Rocket hit meteor or missile
		checkRocketHitShit();
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
				if (missile.position.y < meteor.position.y) {
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

	private void checkRocketHitShit() {
		int meteorLen = meteors.size();
		for (int i = 0; i < meteorLen; i++) {
			Meteor meteor = meteors.get(i);
			if (rocket.position.y > meteor.position.y) {
				if (BoundryTester.RectangleBoundryTest(rocket.bounds,
						meteor.bounds)) {
					rocket.hitShit();
					break;
				}
			}
		}
		int missileLen = missiles.size();
		for (int i = 0; i < missileLen; i++) {
			Missile missile = missiles.get(i);
			if (rocket.position.y > missile.position.y) {
				if (BoundryTester.RectangleBoundryTest(rocket.bounds,
						missile.bounds)) {
					rocket.hitShit();
					break;
				}
			}
		}
	}

	private void checkGameOver() {
		// TODO Auto-generated method stub
	}
}
