package com.yesnote.mr.Object;

import com.badlogic.gdx.math.Vector2;

public class DynamicObject extends Object{
    public Vector2 velocity;          //The speed has direction
    public final Vector2 accel;				//The accelerator
    public float degree;
    public DynamicObject(float x, float y, float width, float height, float degree, int type){
        super(x, y, width, height, type);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
