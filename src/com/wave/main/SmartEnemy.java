package com.wave.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	 
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}
	
	public void tick() {
		x += velocityX;
		y += velocityY;
		
		//This is GREAT! thank you Bastion Jerome on YOUTUBE
		if(x>player.getX())velocityX=-1;
		else if(x<player.getX())velocityX=1;
		else if(x==player.getX())velocityX=0;
		if(y>player.getY())velocityY=-1;
		else if(y<player.getY())velocityY=1;
		else if(y==player.getY())velocityY=0;
		
		/*
		float diffX	= x - player.getX()-8;
		float diffY = x - player.getY()-8;
		float distance = (float) Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));
		
		velocityX = (int) ((-1.0/distance) * diffX);
		velocityY = (int) ((-1.0/distance) * diffY); 
		
		if(y <= 0 || y >= Game.HEIGHT - 48) velocityY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velocityX *= -1;
		*/
		//handler.addObject(new Trail(x,y,ID.Trail, Color.gray, 16,16,0.03f,handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(x, y, 16, 16);
	}

}
