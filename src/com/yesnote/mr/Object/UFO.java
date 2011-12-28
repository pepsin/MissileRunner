package com.yesnote.mr.Object;

import android.util.Log;

import com.yesnote.mr.World;

public class UFO extends DynamicObject {
	private static final String TAG = "Rocket";
	public static final float ROCKET_WIDTH = World.WORLD_WIDTH / 6;
	public static final float ROCKET_HEIGHT = World.WORLD_WIDTH / 6;
	public static final int ROCKET_STATE_HIT = 0;
	public static final int ROCKET_STATE_GO = 1;
	public static final float ROCKET_VELOCITY = 125;// Needs concider and
													// modified

	public int state;
	public float stateTime; // Use this to confirm the animation frame number

	public UFO(float x, float y, float degree, int type) {
		super(x, y, ROCKET_WIDTH, ROCKET_HEIGHT, degree, type);
		state = ROCKET_STATE_GO;
		stateTime = 0;
	}

	public void hitShit() {
		velocity.set(0, 0);
		state = ROCKET_STATE_HIT;
		stateTime = 0;
	}


	public void update(float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x;
		bounds.y = position.y;

		if (position.x < 0) { 
			position.x = World.WORLD_WIDTH;
		}
		if (position.x > World.WORLD_WIDTH) {
			position.x = 0;
		}
		if (position.y < 0) { 
			position.y = 0;
		}
		if (position.y > World.WORLD_HEIGHT - ROCKET_HEIGHT) { 
			position.y = World.WORLD_HEIGHT - ROCKET_HEIGHT;
		}

		stateTime += deltaTime;
		Log.d(TAG, "stateTime = " + stateTime);
	}
}
