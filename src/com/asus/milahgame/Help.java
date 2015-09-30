package com.asus.milahgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.asus.framework.Image;
import com.asus.framework.Game;
import com.asus.framework.Graphics;
import com.asus.framework.Screen;
import com.asus.framework.Input.TouchEvent;


public class Help extends Screen {

	private int state = 0;
	private float alpha = 0;
	private int cursampah = 0;
	
	
	private float Slider = 0;
	private Image iFrom, iTo;
	private int xFrom, xTo;
	
	private boolean bpanah;
	private float panah;
	
	
	public Help(Game game) {
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
    		alpha += deltaTime * 4.5f;
    		if (alpha >= 255) {
    			alpha = 0;
    			state++;
    		}
    		paint.setAlpha((int)alpha);
    		//break;
    	case 3:
    		if (bpanah){
        		panah += deltaTime/2.3f;
        		if (panah >= 0){
        			bpanah = false;
        		}
        	}else{
        		panah -= deltaTime/2.3f;
        		if (panah <= -15){
        			bpanah = true;
        		}
        	}
    		
    		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

            int len = touchEvents.size();
            for (int i=0; i< len; i++){
                TouchEvent event = touchEvents.get(i);
                
                if (event.type == TouchEvent.TOUCH_DOWN){
                	xFrom = event.x;
                }
                
                if (event.type == TouchEvent.TOUCH_UP) {
                	xTo = event.x;
                	
                	if (inBounds(event, 0, 320, 113, 106)){
            			state = 4; // geser kiri
                		Slider = 0;
                		iFrom = Assets.helpJenis[cursampah];
                		if (cursampah == 0)
                			cursampah = 3;
                		else
                			cursampah = cursampah - 1;
                		iTo = Assets.helpJenis[cursampah];
            		}else if(inBounds(event, 423, 320, 117, 106)){
            			state = 5; // geser kanan
                		Slider = 0;
                		iFrom = Assets.helpJenis[cursampah];
                		if (cursampah == 3)
                			cursampah = 0;
                		else
                			cursampah = cursampah + 1;
                		iTo = Assets.helpJenis[cursampah];
            		}else if (xFrom > xTo && Math.abs(xFrom - xTo) > 5){
                		state = 4; // geser kiri
                		Slider = 0;
                		iFrom = Assets.helpJenis[cursampah];
                		if (cursampah == 0)
                			cursampah = 3;
                		else
                			cursampah = cursampah - 1;
                		iTo = Assets.helpJenis[cursampah];
                	}else if (xFrom < xTo && Math.abs(xFrom - xTo) > 5){
                		state = 5; // geser kanan
                		Slider = 0;
                		iFrom = Assets.helpJenis[cursampah];
                		if (cursampah == 3)
                			cursampah = 0;
                		else
                			cursampah = cursampah + 1;
                		iTo = Assets.helpJenis[cursampah];
                	}
                }
            }
    		break;
    		
    	case 4:
    		Slider = Transisi(0, -540, Slider, deltaTime * 25f);
    		if (Slider <= -539){
    			alpha = 0;
    			state = 2;
    		}
    		break;
    	case 5:
    		Slider = Transisi(0, 540, Slider, deltaTime * 25f);
    		if (Slider >= 539){
    			alpha = 0;
    			state = 2;
    		}
    		break;
    	}
    	       
    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }
    
    private Paint paint;

    @Override
    public void paint(float deltaTime) {
    	Graphics g = game.getGraphics();
    	
    	g.clearScreen(Color.BLACK);
    	
    	g.drawImage(Assets.menuBack, 0, 0);
    	
    	switch(state){
    	case 0:
    		g.drawImage(Assets.helpLogo, 0, 0, paint);
    		break;
    	case 1:
    	
    		g.drawImage(Assets.helpLogo, 0, 0);
    		g.drawImage(Assets.helpJenis[0], 0, 0, paint);
    		break;
    	case 2:
    		g.drawImage(Assets.helpLogo, 0, 0);
    		g.drawImage(Assets.helpJenis[cursampah], 0, 0);
    		g.drawImage(Assets.helpBl, (int)( 0 + panah ), 320, paint);
    		g.drawImage(Assets.helpBr, (int)(423 - panah), 320, paint);
    		
    		break;
    	case 3:
    		g.drawImage(Assets.helpLogo, 0, 0);
    		g.drawImage(Assets.helpJenis[cursampah], 0, 0);
    		g.drawImage(Assets.helpBl, (int)( 0 + panah ), 320);
    		g.drawImage(Assets.helpBr, (int)(423 - panah), 320);
    		break;
    	case 4:
    		g.drawImage(Assets.helpLogo, 0, 0);
    		g.drawImage(iFrom,(int)( 0 + Slider ), 0);
    		g.drawImage(iTo, (int) (540 + Slider ), 0);
    		
    		break;
    	case 5:
    		g.drawImage(Assets.helpLogo, 0, 0);
    		g.drawImage(iFrom,(int)( 0 + Slider ), 0);
    		g.drawImage(iTo, (int) (-540 + Slider ), 0);
    		
    		break;
    	}
    	
    }
    
    private float Transisi(float Asal, float Tujuan, float CurPos, float Spd){
    	if (Asal > Tujuan){
    		return CurPos - Spd * Math.abs((Tujuan - CurPos)/(Asal - Tujuan));
    	}else{
    		return CurPos + Spd * Math.abs((Tujuan - CurPos)/(Asal - Tujuan));
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
