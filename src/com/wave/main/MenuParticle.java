package com.wave.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private Color col;
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		velocityX = r.nextInt(16)-8;
		velocityY = r.nextInt(16)-8;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}
	
	public void tick() {
		x += velocityX;
		y += velocityY;
		
		if(y <= 0 || y >= Game.HEIGHT - 48) velocityY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velocityX *= -1;
		
		handler.addObject(new Trail(x,y,ID.Trail, col, 16,16,0.08f,handler));
	}
	
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, 16, 16);
	}

}
