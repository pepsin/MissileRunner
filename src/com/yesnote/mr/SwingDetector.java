package com.yesnote.mr;

public class SwingDetector {
	public String XSwingDetect(float accelX) {
		String left30 = "left30";
		String left45 = "left45";
		String right30 = "right30";
		String right45 = "right30";
		String normal = "normal";
		if (accelX <= 5 && accelX > 1) {
			return left30;
		} else if (accelX > 5 && accelX <= 10) {
			return left45;
		} else if (accelX < -1 && accelX >= -5) {
			return right30;
		} else if (accelX < -5 && accelX >= -10) {
			return right45;
		} else {
			return normal;
		}
	}
	
	public String YSwingDetect(float accelY){
		String forward = "forward";
		String backward = "backward";
		if(accelY > 5){
			return forward;
		}
		else{
			return backward;
		}
	}
}