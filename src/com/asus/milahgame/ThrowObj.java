package com.asus.milahgame;

import com.asus.framework.Image;

public class ThrowObj {
	public int type;
	public Image image;
	public int width;
	public int height;
	
	public ThrowObj(int type, Image image, int width, int height){
		this.type = type;
		this.image = image;
		this.width = width;
		this.height = height;
	}
}
