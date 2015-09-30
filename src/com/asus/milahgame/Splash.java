package com.asus.milahgame;

import android.graphics.Color;
import android.graphics.Paint;

import com.asus.framework.Image;
import com.asus.framework.Game;
import com.asus.framework.Graphics;
import com.asus.framework.Graphics.ImageFormat;
import com.asus.framework.Screen;


public class Splash extends Screen{
	private int state = 0;
	private float timer = 0;
	
	public Splash(Game game) {
        super(game);
        Graphics g = game.getGraphics();
        Assets.splash = g.newImage("ip4.png", ImageFormat.RGB565);
        Assets.splash2 = g.newImage("teamtimot.png", ImageFormat.RGB565);
        Assets.menuBack = g.newImage("menu_back.png", ImageFormat.RGB565);
        Assets.menuAbout = g.newImage("menu_about.png", ImageFormat.RGB565);
        Assets.menuHelp = g.newImage("menu_help.png", ImageFormat.RGB565);
        Assets.menuLogo = g.newImage("menu_logo.png", ImageFormat.RGB565);
        Assets.menuMain = g.newImage("menu_play.png", ImageFormat.RGB565);
        Assets.menuTong = g.newImage("menu_trash.png", ImageFormat.RGB565);
        Assets.menuQuit = g.newImage("menu_quit.png", ImageFormat.RGB565);
        
        Assets.aboutCreator = g.newImage("about_creator.png", ImageFormat.RGB565);
        Assets.aboutLogo = g.newImage("about_logo.png", ImageFormat.RGB565);
        Assets.aboutSub = g.newImage("about_sub.png", ImageFormat.RGB565);
        
        Assets.helpBl = g.newImage("help_bl.png", ImageFormat.RGB565);
        Assets.helpBr = g.newImage("help_br.png", ImageFormat.RGB565);
        Assets.helpLogo = g.newImage("help_logo.png", ImageFormat.RGB565);
        
        Assets.helpJenis = new Image[4];
        Assets.helpJenis[0] = g.newImage("help_dangerous.png", ImageFormat.RGB565);
        Assets.helpJenis[1] = g.newImage("help_nonorganik.png", ImageFormat.RGB565);
        Assets.helpJenis[2] = g.newImage("help_organik.png", ImageFormat.RGB565);
        Assets.helpJenis[3] = g.newImage("help_recycleable.png", ImageFormat.RGB565);
        
        Assets.playScore = g.newImage("game_score.png", ImageFormat.RGB565);
        Assets.playTongB = g.newImage("game_tongb.png", ImageFormat.RGB565);
        Assets.playTongF = g.newImage("game_tongf.png", ImageFormat.RGB565);
        
        Assets.tObject = new ThrowObj[7];
        Assets.tObject[0] = new ThrowObj(1, g.newImage("game_object_1.png", ImageFormat.RGB565), 107, 96);
        Assets.tObject[1] = new ThrowObj(4, g.newImage("game_object_2.png", ImageFormat.RGB565), 73, 95);
        Assets.tObject[2] = new ThrowObj(2, g.newImage("game_object_3.png", ImageFormat.RGB565), 52, 83);
        Assets.tObject[3] = new ThrowObj(4, g.newImage("game_object_4.png", ImageFormat.RGB565), 69, 73);
        Assets.tObject[4] = new ThrowObj(2, g.newImage("game_object_5.png", ImageFormat.RGB565), 40, 118);
        Assets.tObject[5] = new ThrowObj(1, g.newImage("game_object_6.png", ImageFormat.RGB565), 33, 87);
        Assets.tObject[6] = new ThrowObj(2, g.newImage("game_object_7.png", ImageFormat.RGB565), 38, 69);
        
        
        paint = new Paint();
	    paint.setColor(Color.WHITE);
    }
	

    @Override
    public void update(float deltaTime) {
    	if (state == 0){
    		timer += deltaTime;
    		if (timer > 350) {
    			timer = 255;
    			state = 1;
        		//game.setScreen(new Coba(game));
        	}
    	}else if (state == 1){
    		timer -= deltaTime;
    		if (timer <= 0) {
    			state = 2;
    			timer = 0;
        		//game.setScreen(new Coba(game));
        	}
    	}else if(state ==2){
    		timer += deltaTime;
    		if (timer > 350) {
    			timer = 255;
    			state = 3;
        		//game.setScreen(new Coba(game));
        	}
    	}else if (state ==3){
    		timer -= deltaTime;
    		if (timer <= 0) {
    			timer = 0;
        		game.setScreen(new MainMenu(game));
        	}
    	}
    	       
    }
    
    private Paint paint;

    @Override
    public void paint(float deltaTime) {
    	Graphics g = game.getGraphics();
    	
    	g.clearScreen(Color.BLACK);
    	
    	if (state == 0){
	    	if (timer > 100){
		    	paint.setAlpha(255);
		    }else{
		    	paint.setAlpha((int)(timer * 2.55f));
		    }
	        g.drawImage(Assets.splash, 156, 400, paint);
	    }else if(state == 1){
	    	if (timer <= 0){
		    	paint.setAlpha(0);
	    	}else if (timer > 100){
		    	paint.setAlpha(255);
		    }else{
		    	paint.setAlpha((int)(timer * 2.55f));
		    }
	        g.drawImage(Assets.splash, 156, 400, paint);
	    }else if (state == 2){
	    	if (timer > 100){
		    	paint.setAlpha(255);
		    }else{
		    	paint.setAlpha((int)(timer * 2.55f));
		    }
	        g.drawImage(Assets.splash2, 135, 300, paint);
	    }else if (state == 3){
	    	if (timer <= 0){
		    	paint.setAlpha(0);
	    	}else if (timer > 100){
		    	paint.setAlpha(255);
		    }else{
		    	paint.setAlpha((int)(timer * 2.55f));
		    }
	        g.drawImage(Assets.splash2, 135, 300, paint);
	    }
	    
    	
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
    	
    }
}
