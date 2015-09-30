package com.asus.milahgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.asus.framework.Game;
import com.asus.framework.Graphics;
import com.asus.framework.Graphics.ImageFormat;
import com.asus.framework.Screen;
import com.asus.framework.Input.TouchEvent;

public class Coba extends Screen{
	public Coba(Game game) {
        super(game);
        Graphics g = game.getGraphics();
        Assets.botol = g.newImage("botol.png", ImageFormat.RGB565);
        
        paint = new Paint();
        paint.setColor(Color.WHITE);
        
        start = new Point(100, 700);
        finish = new Point(200, 200);
        transit = new Point(100, 700);
    }

	private String markus = "MARKUS";
	private Paint paint;
	private int state = 0;
	
	private class Point{
		Point(float x, float y){
			this.x = x;
			this.y = y;
		}
		
		public float x;
		public float y;
	}
	
	private Point start;
	private Point transit;
	private Point finish;
	
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
		
		float spdY = Spd * 2;
		float spdX = Spd * Math.abs((Tujuan.x - start.x)/(Tujuan.y - start.y));
		
		if (start.y > Tujuan.y){
    		tmp.y = (CurPos.y - spdY * Math.abs((start.y - CurPos.y)/(start.y - Tujuan.y))- 1f);
    	}else{
    		tmp.y = (CurPos.y + spdY * Math.abs((start.y - CurPos.y)/(start.y - Tujuan.y))+ 1f);
    	}
		
		if (start.x > Tujuan.x){
			tmp.x = CurPos.x - spdX;
		}else{
			tmp.x = CurPos.x + spdX;
		}
		
		return tmp;
	}
	
	
    @Override
    public void update(float deltaTime) {
//        Assets.splash = g.newImage("splash.jpg", ImageFormat.RGB565);
        if (!(Math.abs(transit.x - finish.x) < 3)){
    		if (start.x == 100){
    			transit = Kurva1(start, transit, finish, deltaTime * 2f);
    		}else{
    			transit = Kurva2(start, transit, finish, deltaTime * 2f);
    		}
    	}else{
    		if (start.x == 100){
    			start = new Point(transit.x, transit.y);
    	        finish = new Point(300, 700);
    		}
    	}
        
        
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DRAGGED){
            	markus = "DRAG";
            }
            
            if (event.type == TouchEvent.TOUCH_HOLD){
            	markus = "HOLD";
            }
            if (event.type == TouchEvent.TOUCH_DOWN){
            	markus = "DOWN!";
            }
            
            if (event.type == TouchEvent.TOUCH_UP) {

                if (inBounds(event, 0, 0, 300, 300)) {
                	markus = "UP1";
                    //game.setScreen(new GameScreen(game));
                }else{
                	markus = "UP2";
                }

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
    
    private float progress = 0;
    
    @Override
    public void paint(float deltaTime) {
    	Graphics g = game.getGraphics();
    	

		//g.clearScreen(Color.DKGRAY);
    	
    		
    	//	g.drawScaledImage(Assets.botol, (int)(120 + faktorX), (int)(120 + faktorY), (int)(46 * sX), (int)(135 * sY), 0, 0, 46, 135);
    	
		g.drawImage(Assets.botol, (int)transit.x, (int)transit.y);
		
        
    	Paint paint;
	    paint = new Paint();
		paint.setTextSize(50);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		
		g.drawString(markus,10, 60, paint);
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
    	markus = "BACK";
    }
}
