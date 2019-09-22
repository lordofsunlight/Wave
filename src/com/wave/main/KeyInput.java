package com.wave.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.wave.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		
		this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID()==ID.Player) {
				//key events for player1
				
				if(key== KeyEvent.VK_W) { tempObject.setVelocityY(-5); keyDown[0] = true;}
				if(key== KeyEvent.VK_S) { tempObject.setVelocityY(5); keyDown[1] = true;}
				if(key== KeyEvent.VK_D) { tempObject.setVelocityX(5); keyDown[2] = true;}
				if(key== KeyEvent.VK_A) { tempObject.setVelocityX(-5);keyDown[3] = true;}
			}
			/* For 2 Player Implementation
			if(tempObject.getID()==ID.Player2) {
				//key events for player2
				
				if(key== KeyEvent.VK_UP) tempObject.setVelocityY(-5);
				if(key== KeyEvent.VK_DOWN) tempObject.setVelocityY(5);
				if(key== KeyEvent.VK_RIGHT) tempObject.setVelocityX(5);
				if(key== KeyEvent.VK_LEFT) tempObject.setVelocityX(-5);
			}
			*/
			if(key == KeyEvent.VK_P) {
				 if(game.gameState == STATE.Game) {
					if (Game.paused) Game.paused = false;
					else Game.paused = true;
				}
			}
			if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID()==ID.Player) {
				//key events for player1
				
				if(key== KeyEvent.VK_W) { 
					//tempObject.setVelocityY(0);
					keyDown[0] = false;}
				if(key== KeyEvent.VK_S) { 
					//tempObject.setVelocityY(0); 
					keyDown[1] = false;}
				if(key== KeyEvent.VK_D) { 
					//tempObject.setVelocityX(0); 
					keyDown[2] = false;}
				if(key== KeyEvent.VK_A) { 
					//tempObject.setVelocityX(0); 
					keyDown[3] = false;}
				
				//Vertical Movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelocityY(0);
				//Horizontal Movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelocityX(0);
			}
			
			/* For 2 Player Implementation
			if(tempObject.getID()==ID.Player2) {
				//key events for player2
				
				if(key== KeyEvent.VK_UP) tempObject.setVelocityY(-0);
				if(key== KeyEvent.VK_DOWN) tempObject.setVelocityY(0);
				if(key== KeyEvent.VK_RIGHT) tempObject.setVelocityX(0);
				if(key== KeyEvent.VK_LEFT) tempObject.setVelocityX(0);
			}
			*/
		}
	}
}