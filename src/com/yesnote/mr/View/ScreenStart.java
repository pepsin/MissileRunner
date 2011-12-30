package com.yesnote.mr.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yesnote.mr.Assets;
import com.yesnote.mr.BoundryTester;
import com.yesnote.mr.Game;
import com.yesnote.mr.World;
import com.yesnote.mr.Object.StaticObject;
import com.yesnote.mr.Object.UFO;

public class ScreenStart extends Screen {
	OrthographicCamera cam;
	static final float CAM_WIDTH = World.WORLD_WIDTH;
	static final float CAM_HEIGHT = World.WORLD_HEIGHT;
	SpriteBatch batcher;

	StaticObject startButton;
	StaticObject settingButton;
	StaticObject aboutButton;
	UFO ufo;
	float buttonX = CAM_WIDTH / 4;
	float buttonY = (CAM_HEIGHT / 5) * 2;

	public ScreenStart(Game game) {
		super(game);
		this.ufo = new UFO(0, 0, 0, 0);
		this.startButton = new StaticObject(buttonX, buttonY, 0, 0, 0);
		this.settingButton = new StaticObject(buttonX, buttonY - CAM_HEIGHT / 8 - 10, 0, 0, 0);
		this.aboutButton = new StaticObject(buttonX, buttonY - CAM_HEIGHT / 4 - 10, 0, 0, 0);

		cam = new OrthographicCamera(CAM_WIDTH, CAM_HEIGHT);
		this.cam.position.set(CAM_WIDTH / 2, CAM_HEIGHT / 2, 0);

		batcher = new SpriteBatch();
	}

	@Override
	public void update(float deltaTime) {
		if (ufo.position.x >= startButton.position.x) {
			if (BoundryTester.RectangleBoundryTest(ufo.bounds,
					startButton.bounds)) {
				Assets.playSound(Assets.hitSound);
				game.setScreen(new ScreenGame(game));
				return;
			}
		}
		if (ufo.position.x >= settingButton.position.x) {
			if (BoundryTester.RectangleBoundryTest(ufo.bounds,
					settingButton.bounds)) {
				Assets.playSound(Assets.hitSound);
				game.setScreen(new ScreenSetting(game));
				return;
			}
		}
		if (ufo.position.x >= aboutButton.position.x) {
			if (BoundryTester.RectangleBoundryTest(ufo.bounds,
					aboutButton.bounds)) {
				Assets.playSound(Assets.hitSound);
				game.setScreen(new ScreenAbout(game));
				return;
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		GLCommon gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		cam.update();
		batcher.setProjectionMatrix(cam.combined);

		batcher.disableBlending();
		batcher.begin();
		batcher.draw(Assets.background, 0, 0, World.WORLD_WIDTH,
				World.WORLD_HEIGHT);
		batcher.end();

		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.logo, 0, CAM_HEIGHT - 256, CAM_WIDTH, CAM_WIDTH / 2);
		batcher.draw(Assets.startButton, startButton.position.x, startButton.position.y, CAM_WIDTH / 2, CAM_WIDTH / 8);
		batcher.draw(Assets.settingButton, settingButton.position.x, settingButton.position.y, CAM_WIDTH / 2, CAM_WIDTH / 8);
		batcher.draw(Assets.aboutButton, aboutButton.position.x, aboutButton.position.y, CAM_WIDTH / 2, CAM_WIDTH / 8);
		batcher.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}