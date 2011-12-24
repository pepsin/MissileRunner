package com.yesnote.mr.Object;

import com.yesnote.mr.World;

public class Missile extends DynamicObject{
    public static final float MISSILE_WIDTH = World.WORLD_WIDTH / 30;     //Need to be modified later
    public static final float MISSILE_HEIGHT = (float) (World.WORLD_WIDTH / 7.5);    //Need to be modified later
    public static final float MISSILE_VELOCITY = 110;
    public static final int MISSILE_STATE_HIT = 0;
	public static final int MISSILE_STATE_GO = 1;
	public int state;

	public Missile(float x, float y, float degree, int type) {
		super(x, y, MISSILE_WIDTH, MISSILE_HEIGHT, degree, type);
    }
    public void update(float deltaTime){
    	position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.x = position.x;
        bounds.y = position.y;
    }
    
    public void followRocket(){
        //set the velocity to follow the rocket here
    }
}
