package com.asus.milahgame;


import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.asus.framework.Game;
import com.asus.framework.Graphics;
import com.asus.framework.Screen;
import com.asus.framework.Input.TouchEvent;


public class MainMenu extends Screen{
	private int state = 0;
	private int sstate;
	private float curAlpha = 255;
	private Paint paint;
	private Paint hiPaint;
	
	private boolean bplayX = false;
	private float playX = 0;
	
	private float faktorX, faktorY;
	
	
	public MainMenu(Game game) {
        super(game);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        
        hiPaint = new Paint();
        hiPaint.setColor(Color.WHITE);
        
    }
	
    @Override
    public void update(float deltaTime) {

    	switch (state){
        case 0:
        	if (bplayX){
        		playX += deltaTime/1.5f;
        		if (playX >= 0){
        			bplayX = false;
        		}
        	}else{
        		playX -= deltaTime/1.5f;
        		if (playX <= -20){
        			bplayX = true;
        		}
        	}
        	
        	List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

            int len = touchEvents.size();
            for (int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if (event.type == TouchEvent.TOUCH_UP) {
                    if (inBounds(event, 0, 710, 183, 86)) {
                    	faktorX = 0;
                    	faktorY = 0;
                    	bplayX = false;
                    	state = 1;
                    }else if (inBounds(event, 0, 790, 187, 95)) {
                    	faktorX = 0;
                    	faktorY = 0;
                    	bplayX = false;
                    	state = 2;
                    }else if (inBounds(event, 0, 880, 178, 72)) {
                    	faktorX = 0;
                    	faktorY = 0;
                    	bplayX = false;
                    	state = 3;
                    }else if (inBounds(event, 360, 650, 181, 312)) {
                    	faktorX = 0;
                    	faktorY = 0;
                    	bplayX = false;
                    	state = 4;
                    }
                    
                }
            }
        	break;
        case 1:
        case 2:
        case 3:
        case 4:
        	sstate = state;
        	if (bplayX){
        		faktorX -= deltaTime/1.5f;
	        	faktorY -= deltaTime/1.5f;
	        	
	        	if (faktorX <= 0){
	        		state = 5;
	        		bplayX = false;
	        	}
        	}else{
	        	faktorX += deltaTime/1.5f;
	        	faktorY += deltaTime/1.5f;
	        	
	        	if (faktorX >= 20){
	        		bplayX = true;
	        	}
        	}
        	break;
        case 5:
        	curAlpha -= deltaTime * 2.5f;
        	if (curAlpha <= 0){
        		switch(sstate){
        		case 1:
        			
        			game.setScreen(new Help(game));
        			break;
        		case 2:
        			game.setScreen(new About(game));
        			break;
        		case 3:
        			System.exit(10);
        			break;
        		case 4:
        			game.setScreen(new GamePlay(game));
        			break;
        		}
        	}else{
        		paint.setAlpha((int)curAlpha);
        	}
        }
        
//        
//        if (inBounds(event, 50, 350, 250, 450)) {
//            game.setScreen(new GameScreen(game));
//        }

    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
    	Graphics g = game.getGraphics();
    	g.clearScreen(Color.BLACK);
    	
    	switch(state){
    	case 5:
    	case 0:
    		g.drawImage(Assets.menuBack, 0, 0, hiPaint);
    		g.drawImage(Assets.menuLogo, 0, 0, paint);
    		g.drawImage(Assets.menuHelp, 0, 710, paint);
    		g.drawImage(Assets.menuAbout, 0, 790, paint);
    		g.drawImage(Assets.menuQuit, 0, 880, paint);
    		
    		g.drawImage(Assets.menuMain, 360, (int)(650 + playX), paint);
    		g.drawImage(Assets.menuTong, 360, 720, paint);
    		
    		break;
    		
    	case 1:
    		g.drawImage(Assets.menuBack, 0, 0, hiPaint);
    		g.drawImage(Assets.menuLogo, 0, 0, paint);
    		//g.drawImage(Assets.menuHelp, 0, 710, paint);
    		
    		g.drawScaledImage(Assets.menuHelp, (int)( -faktorX/2), (int)(710 - faktorY/2), (int)(183 + faktorX), (int)(86 + faktorY), 0, 0, 183, 	86);
    		g.drawImage(Assets.menuAbout, 0, 790, paint);
    		g.drawImage(Assets.menuQuit, 0, 880, paint);
    		
    		g.drawImage(Assets.menuMain, 360, (int)(650 + playX), paint);
    		g.drawImage(Assets.menuTong, 360, 720, paint);
    		
    		
    		break;
    		
    	case 2:
    		g.drawImage(Assets.menuBack, 0, 0, hiPaint);
    		g.drawImage(Assets.menuLogo, 0, 0, paint);
    		g.drawImage(Assets.menuHelp, 0, 710, paint);
    		
    		g.drawScaledImage(Assets.menuAbout, (int)( -faktorX/2), (int)(790 - faktorY/2), (int)(187 + faktorX), (int)(95 + faktorY), 0, 0, 187, 95);
    		//g.drawImage(Assets.menuAbout, 0, 790, paint);
    		g.drawImage(Assets.menuQuit, 0, 880, paint);
    		
    		g.drawImage(Assets.menuMain, 360, (int)(650 + playX), paint);
    		g.drawImage(Assets.menuTong, 360, 720, paint);
    		
    		
    		break;
    	case 3:
    		g.drawImage(Assets.menuBack, 0, 0, hiPaint);
    		g.drawImage(Assets.menuLogo, 0, 0, paint);
    		g.drawImage(Assets.menuHelp, 0, 710, paint);
    		
    		g.drawImage(Assets.menuAbout, 0, 790, paint);
    		//g.drawImage(Assets.menuQuit, 0, 880, paint);
    		g.drawScaledImage(Assets.menuQuit, (int)( -faktorX/2), (int)(880 - faktorY/2), (int)(178 + faktorX), (int)(72 + faktorY), 0, 0, 178, 72);
    		
    		g.drawImage(Assets.menuMain, 360, (int)(650 + playX), paint);
    		g.drawImage(Assets.menuTong, 360, 720, paint);
    		break;
    	case 4:
    		g.drawImage(Assets.menuBack, 0, 0, hiPaint);
    		g.drawImage(Assets.menuLogo, 0, 0, paint);
    		g.drawImage(Assets.menuHelp, 0, 710, paint);
    		
    		g.drawImage(Assets.menuAbout, 0, 790, paint);
    		g.drawImage(Assets.menuQuit, 0, 880, paint);
    		
    		g.drawImage(Assets.menuMain, 360, (int)(650 + playX), paint);
    		//g.drawImage(Assets.menuTong, 360, 720, paint);
    		g.drawScaledImage(Assets.menuTong, (int)(360 -faktorX/2), (int)(720 - faktorY/2), (int)(181 + faktorX), (int)(231 + faktorY), 0, 0, 181, 231);
    		
    		break;
    	}
		
		
		//g.drawString(markus,10, 60, paint);
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
    	//markus = "BACK";
    }
}
