package com.yesnote.mr;

import android.util.Log;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yesnote.mr.Object.Meteor;
import com.yesnote.mr.Object.Missile;
import com.yesnote.mr.Object.Rocket;

public class WorldRenderer {
	private static final String TAG = "WorldRederer";
	static final float CAM_WIDTH = 480;
	static final float CAM_HEIGHT = 854;
	World world;
	OrthographicCamera cam;
	SpriteBatch batch;

	public WorldRenderer(SpriteBatch batch, World world) {
		this.world = world;
		this.batch = batch;
		this.cam = new OrthographicCamera(CAM_WIDTH, CAM_HEIGHT);
		this.cam.position.set(CAM_WIDTH / 2, CAM_HEIGHT / 2, 0);
	}

	public void render() {
		/*
		 * if (world.rocket.position.y > cam.position.y ||
		 * world.rocket.position.y < -cam.position.y) { cam.position.y =
		 * world.rocket.position.y; Log.d(TAG, "cam's y position = " +
		 * cam.position.y); }
		 */
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObject();
	}

	public void renderBackground() {
		batch.disableBlending();
		batch.begin();
		// batch.draw(Assets.background, cam.position.x - CAM_WIDTH / 2,
		// cam.position.y - CAM_HEIGHT / 2, 512, 2048);
		batch.end();
	}

	public void renderObject() {
		batch.enableBlending();
		batch.begin();
		renderRocket();
		renderMeteor();
		renderMissile();
		batch.end();
	}

	public void renderRocket() {
		Texture keyFrame;
		Log.d(TAG, "render rocket success");
		switch (world.rocket.state) { // Use this to change the image of rocket
										// to present the turning left and right
										// style~
		case Rocket.ROCKET_STATE_GO:
			keyFrame = Assets.rocket;
			break;
		case Rocket.ROCKET_STATE_HIT:
			keyFrame = Assets.rocket;
			break;
		default:
			keyFrame = Assets.rocket;
			break;
		}
		Sprite rocket = new Sprite(keyFrame);
		rocket.rotate(world.rocket.degree); // Degfine by degrees. "-180 ~ 180"
		rocket.setPosition(world.rocket.position.x, world.rocket.position.y);
		rocket.setSize(Rocket.ROCKET_WIDTH, Rocket.ROCKET_HEIGHT);
		rocket.draw(batch);
	}

	public void renderMeteor() {
		Texture keyFrame = Assets.meteor;
		int len = world.meteors.size();
		for (int i = 0; i < len; i++) {
			Meteor meteor = world.meteors.get(i);

			switch (meteor.type) {
			case 1:
				keyFrame = Assets.meteor01;
				break;
			case 2:
				keyFrame = Assets.meteor02;
				break;
			case 3:
				keyFrame = Assets.meteor03;
				break;
			case 4:
				keyFrame = Assets.meteor04;
				break;
			case 5:
				keyFrame = Assets.meteor05;
				break;
			default:
				keyFrame = Assets.meteor;
				break;
			}
			batch.draw(keyFrame, meteor.position.x, meteor.position.y, Meteor.METEOR_WIDTH, Meteor.METEOR_HEIGHT);
		}
	}

	public void renderMissile() {
		Texture keyFrame = Assets.missile;
		Sprite m = new Sprite(keyFrame);
		int len = world.missiles.size();
		for (int i = 0; i < len; i++) {
			Missile missile = world.missiles.get(i);
			m.rotate(missile.degree);
			m.setPosition(missile.position.x, missile.position.y);
			m.setSize(Missile.MISSILE_WIDTH, Missile.MISSILE_HEIGHT);
			m.draw(batch);
		}
	}
}
