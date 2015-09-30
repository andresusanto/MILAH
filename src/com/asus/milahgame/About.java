package com.asus.milahgame;


import android.graphics.Color;
import android.graphics.Paint;

import com.asus.framework.Game;
import com.asus.framework.Graphics;
import com.asus.framework.Screen;

public class About extends Screen{
	
	private int state = 0;
	private float alpha = 0;
	
	public About(Game game) {
        super(game);
        
        paint = new Paint();
	    paint.setColor(Color.WHITE);
    }
	

    @Override
    public void update(float deltaTime) {
    	switch(state){
    	case 0:
    	case 1:
    	case 2:
    		alpha += deltaTime * 2.5f;
    		if (alpha >= 255) {
    			alpha = 0;
    			state++;
    		}
    		paint.setAlpha((int)alpha);
    		break;
    		
    	}
    	       
    }
    
    private Paint paint;

    @Override
    public void paint(float deltaTime) {
    	Graphics g = game.getGraphics();
    	
    	g.clearScreen(Color.BLACK);
    	
    	g.drawImage(Assets.menuBack, 0, 0);
    	
    	switch(state){
    	case 0:
    		g.drawImage(Assets.aboutLogo, 0, 0, paint);
    		break;
    	case 1:
    	
    		g.drawImage(Assets.aboutLogo, 0, 0);
    		g.drawImage(Assets.aboutSub, 0, 80, paint);
    		break;
    	case 2:
    		g.drawImage(Assets.aboutLogo, 0, 0);
    		g.drawImage(Assets.aboutSub, 0, 80);
    		g.drawImage(Assets.aboutCreator, 40, 670, paint);
    		break;
    	case 3:
    		g.drawImage(Assets.aboutLogo, 0, 0);
    		g.drawImage(Assets.aboutSub, 0, 80);
    		g.drawImage(Assets.aboutCreator, 40, 670);
    		break;
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
    	game.setScreen(new MainMenu(game));
    }
}
