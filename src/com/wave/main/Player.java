package com.wave.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		//velocityX=r.nextInt(5)+1;
		//velocityY=r.nextInt(5);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

	public void tick() {
		/*if(x < 2) x=2; //My Solution to clamp
		else if(x > Game.WIDTH-36) x=Game.WIDTH-36;
		else x += velocityX;
		*/
		x += velocityX;
		y += velocityY;
		//if(y >= 0 && y <= Game.HEIGHT-48) y += velocityY;
		x = Game.clamp(x, 0, Game.WIDTH-38);
		y = Game.clamp(y, 0, Game.HEIGHT-64);
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i); 
			if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.Boss) {
				//collision Code
				
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2; //Amount of DMG taken upon hit by Basic Enemy
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x,y,32,32);
	}

}
