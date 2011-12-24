package com.yesnote.mr;

import com.yesnote.mr.View.Screen;
import com.yesnote.mr.View.ScreenGame;


public class MissileRunner extends Game {

	@Override
	public void create() {
        Assets.load();
        super.create();
	}

	@Override
	public Screen getStartScreen() {
		return new ScreenGame(this);                  //For test
	}

}
