package com.yesnote.mr.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yesnote.mr.Game;
import com.yesnote.mr.World;
import com.yesnote.mr.World.WorldListener;
import com.yesnote.mr.WorldRenderer;

public class ScreenGame extends Screen {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;

	int state;
	World world;
	WorldRenderer renderer;
	SpriteBatch batcher;
    OrthographicCamera guiCam;
	WorldListener worldListener;

	public ScreenGame(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		world = new World(worldListener){};
		batcher = new SpriteBatch();
		renderer = new WorldRenderer(batcher, world);
	}

	@Override
	public void update(float deltaTime) {
		if (deltaTime > 0.1f)
			deltaTime = 0.1f;

		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		case GAME_LEVEL_END:
			updateLevelEnd();
			break;
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	private void updateReady() {
        if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;
		}

	}

	private void updateRunning(float deltaTime) {
		world.update(deltaTime, Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY());
	}

	private void updatePaused() {
		// TODO Auto-generated method stub

	}

	private void updateLevelEnd() {
		// TODO Auto-generated method stub

	}

	private void updateGameOver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void present(float deltaTime) {
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		renderer.render();
		batcher.enableBlending();
		batcher.begin();
		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_LEVEL_END:
			presentLevelEnd();
			break;
		case GAME_OVER:
			presentGameOver();
			break;
		}
		batcher.end();
	}

	private void presentReady() {
		// TODO Auto-generated method stub
		
	}

	private void presentRunning() {
		
	}

	private void presentPaused() {
		// TODO Auto-generated method stub
		
	}

	private void presentLevelEnd() {
		// TODO Auto-generated method stub
		
	}

	private void presentGameOver() {
		// TODO Auto-generated method stub
		
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
