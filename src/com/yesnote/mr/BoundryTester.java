package com.yesnote.mr;


import com.badlogic.gdx.math.Rectangle;

public class BoundryTester {
	public static String TAG = "BoundryTester";

	public static boolean RectangleBoundryTest(Rectangle r1, Rectangle r2) {
		if (r1.x < r2.x + r2.width && r1.x + r1.width > r2.x
				&& r1.y < r2.y + r2.height && r1.y + r1.height > r2.y) {
			return true;
		} else {
			return false;
		}
	}
}
