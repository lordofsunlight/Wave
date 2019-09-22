package com.wave.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	private int timer = 55;
	private int timer2 = 120;
	
	public Boss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		
		velocityX = 0;
		velocityY = 2;
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,96,128);
	}
	
	public void tick() {
		x += velocityX;
		y += velocityY;
		
		if(timer <= 0) velocityY = 0;
		else {
			timer--;
			}
		
		if(timer <= 0) timer2--;
			if(timer2 <= 0) {
			if(velocityX == 0) velocityX = 2; //Starts the Boss Moving
			int spawn = r.nextInt(10);
			if (spawn == 0) handler.addObject(new BossBullet(x+32,y+60,ID.BasicEnemy, handler));
			
			/*  need to cast position as float in order to increase speed evenly
			if(velocityX > 0) velocityX += 0.1;
			else if (velocityX < 0) velocityX-= 0.1;
			*/
		}
		
		if(x <= 0 || x >= Game.WIDTH - 96) velocityX *= -1;
	}
	
	public void render(Graphics g) {
		g.fillRect(x, y, 96, 128);
	}

}
