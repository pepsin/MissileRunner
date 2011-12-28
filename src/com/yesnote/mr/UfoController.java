package com.yesnote.mr;

import com.yesnote.mr.Object.UFO;

public class UfoController {
	
	public void control(UFO ufo, float accelX, float accelY){
		if (ufo.state != UFO.ROCKET_STATE_HIT) {
			double x; // Use this to change the x axis's velocity,in case the
						// velocity in the demand direction is not change
			if (accelX <= 5 && accelX > 1) {
				ufo.degree = 30;
			} else if (accelX > 5 && accelX <= 10) {
				ufo.degree = 45;
			} else if (accelX < -1 && accelX >= -5) {
				ufo.degree = -30;
			} else if (accelX < -5 && accelX >= -10) {
				ufo.degree = -45;
			} else {
				ufo.degree = 0;
			}
			x = Math.sin(6.28 * ufo.degree / 360); // Change the degree into
														// radian.
			ufo.velocity.x = -(float) (3 * x * UFO.ROCKET_VELOCITY);

			if (accelY >= -5 && accelY < 5) {
				ufo.velocity.y = UFO.ROCKET_VELOCITY;
			} else if (accelY >= 5 && accelY <= 10 || accelY < -5
					&& accelY > -10) {
				ufo.velocity.y = -UFO.ROCKET_VELOCITY;
			} else {
				ufo.velocity.y = 0;
			}
		}

	}
}