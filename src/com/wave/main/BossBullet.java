package com.wave.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossBullet extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	
	public BossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		velocityX = r.nextInt(10) -5;// I don't know why tutsGML does (5 - -5) -5
		velocityY = 5;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	public void tick() {
		x += velocityX;
		y += velocityY;
		
		if (y>= Game.HEIGHT) handler.removeObject(this);
		
		//handler.addObject(new Trail(x,y,ID.Trail, Color.red, 16,16,0.03f,handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 16, 16);
	}

}
