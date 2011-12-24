package com.yesnote.mr;

import com.badlogic.gdx.backends.android.AndroidApplication;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class Starter extends AndroidApplication {
    /** Called when the activity is first created. */
	static int screenHeight;
	static int screenWidth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new MissileRunner(), false);
        
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display d = wm.getDefaultDisplay(); 
        screenHeight = d.getHeight(); 
        screenWidth = d.getWidth(); 
    }
}
