package com.yesnote.mr.Object;

import com.yesnote.mr.World;

public class Meteor extends DynamicObject{
    public static final float METEOR_WIDTH = World.WORLD_WIDTH / 8;      //Need to be modified later
    public static final float METEOR_HEIGHT = World.WORLD_WIDTH/ 8;     //Need to be modified later
    public static final float METEOR_VELOCITY = -60;   //Need to fix to adjust the gamePlay
    
	public Meteor(float x, float y, float degree, int type) {
		super(x, y, METEOR_WIDTH, METEOR_HEIGHT, degree, type);
        velocity.set(0, 0);							  //Let meteor goes 45 degree down.Later filled in the solution
	}
    public void update(float deltaTime){
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.x = position.x;
        bounds.y = position.y;
    }
}
