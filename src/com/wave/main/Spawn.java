package com.wave.main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Game game;
	
	//private int scoreKeep=0;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler=handler;
		this.hud=hud;
		this.game=game;	
	}
	
	public void tick() {
		//scoreKeep++;
		
		if(hud.getScore()%300 == 0 && hud.getScore()<= 3000) {
			//scoreKeep = 0;
			hud.setLevel(hud.getLevel()+1);
			if(game.diff == 0) {
				if (hud.getLevel() < 10) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.BasicEnemy,handler));
				if (hud.getLevel() == 3) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.FastEnemy,handler));
				if (hud.getLevel() == 5) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.SmartEnemy,handler));
				if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new Boss(Game.WIDTH/2, -100,ID.Boss,handler));
				}
			}
			if(game.diff == 1) {
				if (hud.getLevel() < 10) handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.BasicEnemy,handler));
				if(hud.getLevel()%3 == 0) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.FastEnemy,handler));
				if (hud.getLevel() == 5) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.SmartEnemy,handler));
				if (hud.getLevel()==10) {
					handler.clearEnemies();
					handler.addObject(new Boss(Game.WIDTH/2, -100,ID.Boss,handler));
				}
			}
		}
	}

}
