package com.yesnote.mr;

import com.yesnote.mr.View.Screen;
import com.yesnote.mr.View.ScreenGame;
import com.yesnote.mr.View.ScreenStart;


public class MissileRunner extends Game {

	@Override
	public void create() {
        Assets.load();
        super.create();
	}

	@Override
	public Screen getStartScreen() {
		return new ScreenStart(this);                  //For test
	}

}
