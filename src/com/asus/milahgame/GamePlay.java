package com.asus.milahgame;


import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;

import com.asus.framework.Image;
import com.asus.framework.Game;
import com.asus.framework.Graphics;
import com.asus.framework.Screen;
import com.asus.framework.Input.TouchEvent;

public class GamePlay extends Screen{
	private int state = 0;
	private float timer = 0;
	
	private Random rnd;
	
	private Point TouchA, TouchB;
	private float sudut;
	
	private int objID;
	private Point objPos;
	private Point objStart;
	private Point objTarget;
	private boolean drawObj;
	
	private float scaling;
	
	private boolean tValid;
	
	public GamePlay(Game game) {
        super(game);
        Graphics g = game.getGraphics();
        
        rnd = new Random();
        paint = new Paint();
	    paint.setColor(Color.WHITE);
	    
	    fPaint = new Paint();
	    fPaint.setColor(Color.GREEN);
	    fPaint.setAntiAlias(true);
	    fPaint.setTextSize(40);
	    
	    
	    objID = rnd.nextInt(6);
	    
	    objPos = new Point(271 - Assets.tObject[objID].width/2, 895 - Assets.tObject[objID].height/2);
	    objStart = new Point(271 - Assets.tObject[objID].width/2, 895 - Assets.tObject[objID].height/2);
	    
	    
	    hiScore = 0;
	    scaling = 1;
	    drawObj = false;
	    
	    TouchA = new Point(0,0);
	    TouchB = new Point(0,0);
    }
	
	private int hiScore;
	
	private int isFit (float x, float y){
		if (y > 603 && y < 713) {
			if (x > 54 && x < 122)
				return 4;
			
			if (x > 155 && x < 225)
				return 3;
			
			if (x > 314 && x < 386)
				return 2;
			
			if (x > 431 && x < 503)
				return 1;
		}
		
		return -1;
	}
	

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }
	
    @Override
    public void update(float deltaTime) {
    	
    	switch (state){
    	case 0:
    		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
            
        	int len = touchEvents.size();
            if (len == 1) {
                TouchEvent event = touchEvents.get(0);
                if (event.type == TouchEvent.TOUCH_DOWN){
                	if (inBounds(event, (int)objPos.x - 20, (int)objPos.y - 20, Assets.tObject[objID].width + 20, Assets.tObject[objID].height + 20)){
	                	TouchA.x = event.x;
	                	TouchA.y = event.y;
	                	
	                	objStart = new Point(objPos.x, objPos.y);
	                	tValid = true;
                	}else{
                		tValid = false;
                	}
                
                }else if (event.type == TouchEvent.TOUCH_DRAGGED) {
                		
                	if (tValid){
                		objPos.x = objStart.x - (TouchA.x - event.x);
                		objPos.y = objStart.y - (TouchA.y - event.y);
                	}
                	
                }else if (event.type == TouchEvent.TOUCH_UP) {
                	if (isFit(objPos.x + Assets.tObject[objID].width/2, objPos.y + Assets.tObject[objID].height/2) == Assets.tObject[objID].type) {
                		hiScore++;

                	    objID = rnd.nextInt(6);
                	    
                	    objPos = new Point(271 - Assets.tObject[objID].width/2, 895 - Assets.tObject[objID].height/2);
                	    objStart = new Point(271 - Assets.tObject[objID].width/2, 895 - Assets.tObject[objID].height/2);
                	    
                	}else if(isFit(objPos.x + Assets.tObject[objID].width/2, objPos.y + Assets.tObject[objID].height/2) != -1){
                		hiScore = 0;

                	    objID = rnd.nextInt(6);
                	    
                	    objPos = new Point(271 - Assets.tObject[objID].width/2, 895 - Assets.tObject[objID].height/2);
                	    objStart = new Point(271 - Assets.tObject[objID].width/2, 895 - Assets.tObject[objID].height/2);
                	    
                	}
                }
            }   
            break;
            
    	case 1:
    		/*
    		if (Math.abs(objTarget.x - objPos.x) < 1){
        		if (objStart.x == 217){
        			
        			objStart.x = objPos.x;
        			objStart.y = objPos.y;
        			
        			
        			objTarget = new Point((float)(271 + (251)/Math.tan(sudut)) ,644);
        			
        			drawObj = true;
        		}
        	}else{
        		if (drawObj && objPos.y + Assets.tObject[objID].height >= 603){
        			drawObj = false;
        			if (isFit(objPos.x, Assets.tObject[objID].width * scaling)){
        				hiScore++;
        			}else{
        				hiScore = 0;
        			}
        		}
        		scaling = 0.5f + 0.5f * Math.abs((float)(271 + (292-96)/Math.tan(0.872)) - objPos.x)/Math.abs((float)(271 + (292-96)/Math.tan(0.872)) - 217);
        		//scaling -= deltaTime * 0.0015f;
        		
        		if (objStart.x == 217)
        			objPos = Kurva1(objStart, objPos, objTarget, deltaTime * 7f);
        		else
        			objPos = Kurva2(objStart, objPos, objTarget, deltaTime * 3f);
        	}
        	*/
    		break;
            
    	}
    	
    	
    	

        
    }
    
    private Paint paint;
    private Paint fPaint;

    @Override
    public void paint(float deltaTime) {
    	Graphics g = game.getGraphics();
    	g.clearScreen(Color.BLACK);
    	

    	g.drawImage(Assets.menuBack, 0, 0);
    	g.drawImage(Assets.playScore, 0, 0);
    	
    	g.drawImage(Assets.playTongB, 0, 580);
    	
    	if (drawObj)
    		g.drawScaledImage(Assets.tObject[objID].image, (int)objPos.x, (int)objPos.y, (int)(Assets.tObject[objID].width * scaling), (int)(Assets.tObject[objID].height * scaling), 0, 0, Assets.tObject[objID].height, Assets.tObject[objID].height);
    		
    	g.drawImage(Assets.playTongF, 0, 580);
    	

    	if (!drawObj)
    		g.drawScaledImage(Assets.tObject[objID].image, (int)objPos.x, (int)objPos.y, (int)(Assets.tObject[objID].width * scaling), (int)(Assets.tObject[objID].height * scaling), 0, 0, Assets.tObject[objID].height, Assets.tObject[objID].height);
		
    	
    	g.drawString(hiScore+"", 255, 84, fPaint);
    }
    
    private class Point{
		Point(float x, float y){
			this.x = x;
			this.y = y;
		}
		
		public float x;
		public float y;
	}

	private Point Kurva1 (Point start, Point CurPos, Point Tujuan, float Spd){
		Point tmp = new Point(0,0);
		
		float spdY = Spd * 2;
		float spdX = Spd * Math.abs((Tujuan.x - start.x)/(Tujuan.y - start.y));
		
		if (start.y > Tujuan.y){
    		tmp.y = (CurPos.y - spdY * Math.abs((Tujuan.y - CurPos.y)/(start.y - Tujuan.y)));
    	}else{
    		tmp.y = (CurPos.y + spdY * Math.abs((Tujuan.y - CurPos.y)/(start.y - Tujuan.y)));
    	}
		
		if (start.x > Tujuan.x){
			tmp.x = CurPos.x - spdX;
		}else{
			tmp.x = CurPos.x + spdX;
		}
		
		return tmp;
	}
	
	
	
	private Point Kurva2 (Point start, Point CurPos, Point Tujuan, float Spd){
		Point tmp = new Point(0,0);
		
		float spdY = Spd * 4;
		float spdX = Spd * Math.abs((Tujuan.x - start.x)/(Tujuan.y - start.y));
		
		if (start.y > Tujuan.y){
    		tmp.y = (CurPos.y - spdY * Math.abs((start.y - CurPos.y)/(start.y - Tujuan.y))- .1f);
    	}else{
    		tmp.y = (CurPos.y + spdY * Math.abs((start.y - CurPos.y)/(start.y - Tujuan.y))+ .1f);
    	}
		
		if (start.x > Tujuan.x){
			tmp.x = CurPos.x - spdX;
		}else{
			tmp.x = CurPos.x + spdX;
		}
		
		return tmp;
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
    	game.setScreen(new MainMenu(game));
    }
}
