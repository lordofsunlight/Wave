package com.wave.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import org.lwjgl.openal.AL;

import com.wave.main.Game.STATE;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler= handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//Play Button
		if (game.gameState == STATE.Menu) {
			
			if (mouseOver(mx, my, 210, 100, 210, 64)) {
				game.gameState = STATE.Select;
				
				AudioPlayer.getSound("menu_sound").play();
				
				return;
			}
			
			//Help Button
			if (mouseOver(mx, my, 210, 200, 210, 64)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
	
			}
			
			//Quit Button
			if(mouseOver(mx, my, 210, 300, 210,64)) {
				AL.destroy();
				System.exit(1);
			}
		}
		
		//SelectScreen
		if (game.gameState == STATE.Select) {
			
			//Normal Button
			if (mouseOver(mx, my, 210, 100, 210, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));//Spawn in middle of screen
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.BasicEnemy,handler));		
				game.diff = 0;
				AudioPlayer.getSound("menu_sound").play();
			}
					
			//Hard Button
			if (mouseOver(mx, my, 210, 200, 210, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));//Spawn in middle of screen
				handler.clearEnemies();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.BasicEnemy,handler));
				game.diff = 1;
				AudioPlayer.getSound("menu_sound").play();
			}
					
			//Back Button
			if(mouseOver(mx, my, 210, 300, 210,64)) {
				game.gameState = STATE.Menu;
			}
		}
		
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx,my,210, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
		
		//Try Again
		if(game.gameState == STATE.End ) {
			
			if(mouseOver(mx, my, 210, 250, 210, 64)) {
				game.gameState = STATE.Game;
				hud.setScore(0);
				hud.setLevel(1);
				//spawn appropriate enemy
				if (game.diff == 0) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.BasicEnemy,handler));
				}else if (game.diff == 1){
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68), ID.HardEnemy, handler));
				}
				
				AudioPlayer.getSound("menu_sound").play();
			}
			
			if(mouseOver(mx, my, 185, 350, 260, 64)) {
				game.gameState = STATE.Select;
				handler.clearPlayer();
				hud.setScore(0);
				hud.setLevel(1);
				AudioPlayer.getSound("menu_sound").play();
			}
		}
		
		//Try Again
		if(game.gameState == STATE.Win ) {
					
			if(mouseOver(mx, my, 210, 250, 210, 64)) {
				game.gameState = STATE.Game;
				hud.setScore(0);
				hud.setLevel(1);
				//spawn appropriate enemy
				if (game.diff == 0) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.BasicEnemy,handler));
				}else if (game.diff == 1){
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68), ID.HardEnemy, handler));
				}
						
				AudioPlayer.getSound("menu_sound").play();
			}
					
			if(mouseOver(mx, my, 185, 350, 260, 64)) {
				game.gameState = STATE.Select;
				handler.clearPlayer();
				hud.setScore(0);
				hud.setLevel(1);
				AudioPlayer.getSound("menu_sound").play();
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x+width){
			if(my > y && my < y+height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial",1,50);
		Font fnt2 = new Font("arial", 1, 30);
		Font fnt3 = new Font("arial", 1, 15);
		g.setColor(Color.white);
		
		if(game.gameState == STATE.Menu) {
			g.setFont(fnt);
			g.drawString("Wave", 250, 70);
			
			g.setFont(fnt2);
			//g.setColor(Color.white);
			g.drawRect(210,100,210,64);
			g.drawString("PLAY", 275, 142);
			
			//g.setColor(Color.white);
			g.drawRect(210,200,210,64);
			g.drawString("HELP", 275, 242);
			
			//g.setColor(Color.white);
			g.drawRect(210,300,210,64);
			g.drawString("QUIT", 279, 342);
			
		} else if(game.gameState == STATE.Help) {
			g.setFont(fnt);
			g.drawString("Help", 255, 100);
			
			g.setFont(fnt3);
			g.drawString("Use W, A, S, D keys to avoid touching Enemies and Projectiles", 100, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 300, 210, 64);
			g.drawString("Back", 275, 342);
			
		}else if(game.gameState == STATE.End) {
			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("GAME OVER", 165, 100);
			
			g.setFont(fnt2);
			g.drawString("You Lost With Score:  " + hud.getScore(), 130, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 250, 210, 64);
			g.drawString("Try Again", 250, 292);
			
			g.setFont(fnt2);
			g.drawRect(185, 350, 260, 64);
			g.drawString("Change Difficulty", 195, 392);
			
		}else if(game.gameState == STATE.Select) {
			g.setFont(fnt);
			g.drawString("Select Difficulty", 145, 70);
			
			g.setFont(fnt2);
			g.drawRect(210,100,210,64);
			g.drawString("Normal", 270, 142);
			
			g.drawRect(210,200,210,64);
			g.drawString("Hard", 280, 242);
			
			g.drawRect(210,300,210,64);
			g.drawString("Back", 275, 342);
		}else if(game.gameState == STATE.Win) {
			g.setFont(fnt);
			g.setColor(Color.green);
			g.drawString("You Defeated", 165, 100);
			
			g.setFont(fnt2);
			g.drawString("You Won With Score:  " + hud.getScore(), 130, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 250, 210, 64);
			g.drawString("Try Again", 250, 292);
			
			g.setFont(fnt2);
			g.drawRect(185, 350, 260, 64);
			g.drawString("Change Difficulty", 195, 392);
		}
	}
}