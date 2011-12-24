package com.yesnote.mr.Object;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Object {
	public final Vector2 position;
	public final Rectangle bounds;
	public int type;
	
	public Object(float x, float y, float width, float height, int type){ //Type is used to decide which picture to present the object.
        this.position =new Vector2(x, y);
        this.bounds = new Rectangle(x, y, width, height);
        this.type = type;
    }
}
