package com.yesnote.mr;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static Texture ufo;
    public static Texture background;
    public static Texture meteor;
    public static Texture meteor01;
    public static Texture meteor02;
    public static Texture meteor03;
    public static Texture meteor04;
    public static Texture meteor05;
    public static Texture missile;
    public static Texture startButton;
    public static Texture settingButton;
    public static Texture aboutButton;
    public static Texture logo;
    
    public static Sound hitSound;
    
    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }
    
    public static void load(){
    	logo = new Texture(Gdx.files.internal("logo.png"));
        startButton = new Texture(Gdx.files.internal("startButton.png"));
        settingButton = new Texture(Gdx.files.internal("settingButton.png"));
        aboutButton = new Texture(Gdx.files.internal("aboutButton.png"));
        ufo = new Texture(Gdx.files.internal("ufo.png"));
        background = new Texture(Gdx.files.internal("tbackground.png"));
        meteor = new Texture(Gdx.files.internal("testrock.png"));
        meteor01 = new Texture(Gdx.files.internal("mone.png"));
        meteor02 = new Texture(Gdx.files.internal("mtwo.png"));
        meteor03 = new Texture(Gdx.files.internal("mthree.png"));
        meteor04 = new Texture(Gdx.files.internal("mfour.png"));
        meteor05 = new Texture(Gdx.files.internal("mfive.png"));
        missile = new Texture(Gdx.files.internal("missile.png"));
  
        Log.d("Hello", "Hello");
        
        //hitSound = Gdx.audio.newSound(Gdx.files.internal(""));//TODO
    }
    public static void playSound(Sound sound){

    }    
}
