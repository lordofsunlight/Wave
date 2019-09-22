package com.wave.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HardEnemy extends GameObject{
	
	private Handler handler;
	
	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		velocityX = 5;
		velocityY = 5;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	public void tick() {
		x += velocityX;
		y += velocityY;
		
		if(y <= 0 || y >= Game.HEIGHT - 48) velocityY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velocityX *= -1;
		
		//handler.addObject(new Trail(x,y,ID.Trail, Color.green, 16,16,0.03f,handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 16, 16);
	}

}
