package com.wave.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -726038495289796084L;
	
	static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
	
	private Thread thread;
	private boolean running=false;
	
	public static boolean paused = false;
	public int diff = 0; //0 is normal... 1 is Hard
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE{
		Menu,
		Help,
		Game,
		Select,
		End,
		Win
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler(); //Initialize Baby!
		hud = new HUD();//Initialize
		menu = new Menu(this, handler, hud);
		
		this.addMouseListener(menu);
		this.addKeyListener(new KeyInput(handler, this));
		
		spawner = new Spawn(handler,hud,this);
		r = new Random(); //Initialize
		
		//Play the Music, Johnny!
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH, HEIGHT, "Game Time, Baby!", this);
		
		if (gameState == STATE.Game) {
			//handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));//Spawn in middle of screen
			//handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH-38), r.nextInt(Game.HEIGHT-68),ID.BasicEnemy,handler));
		}else {
			for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH-38), r.nextInt(HEIGHT-68), ID.BasicEnemy, handler));
			}
		}
		
		//handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.Player2)); 2 PLAYERS!
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start(); 
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();//focuses on the game screen so you don't have to click into the box on start up
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime=now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			render();
			
			//frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("(FPS: " + frames + ")");
				//frames = 0;
			}
		}
		
		stop();
				
	}
	
	private void tick() {
		
		if (gameState == STATE.Game) {
			if (!paused) {
				handler.tick();
				hud.tick();
				spawner.tick();
				
				if(hud.HEALTH <= 0) {
					hud.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
				}
				
				if(hud.getScore() >= 5000) {
					hud.HEALTH = 100;
					gameState = STATE.Win;
					handler.clearEnemies();
				}
			}
		}else {//if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
			handler.tick();
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);//3 is max limit, num buffers
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0,0, WIDTH,HEIGHT);
		
		handler.render(g);
		
		if (paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 275,100);
		}
		
		if (gameState == STATE.Game) {
			hud.render(g);
		}else {//if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return max;
		if (var <= min)
			return min;
		else 
			return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
