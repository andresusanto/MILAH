package com.asus.milahgame;

import com.asus.framework.Image;
import com.asus.framework.Music;
import com.asus.framework.Sound;

public class Assets {
    public static Image botol;
	
	public static Image splash, splash2;
	
	public static Image menuBack, menuAbout, menuHelp, menuLogo, menuMain, menuTong, menuQuit;
	
	public static Image aboutCreator, aboutLogo, aboutSub;
	
	public static Image helpBl, helpBr, helpLogo;
	
	public static Image[] helpJenis;

    public static Image playScore, playTongB, playTongF;
    
    public static ThrowObj[] tObject;
    
	public static Sound click;
    public static Music theme;
   
    public static void load(MainGame sampleGame) {
        // TODO Auto-generated method stub
        //theme = sampleGame.getAudio().createMusic("menutheme.mp3");
        //theme.setLooping(true);
        //theme.setVolume(0.85f);
        //theme.play();
    }
}