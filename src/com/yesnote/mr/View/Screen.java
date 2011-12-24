package com.yesnote.mr.View;

import com.yesnote.mr.Game;


public abstract class Screen {
    Game game;
   
    
    public Screen(Game game){
        this.game = game;
    }
    
    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
