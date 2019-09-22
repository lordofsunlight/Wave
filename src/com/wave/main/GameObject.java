package com.wave.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * Is going to be what we refer to as all the game objects.
 * EX: player, enemy, coins etc. are considered "GameObject" 
 * They all inherit from this 
 */
public abstract class GameObject {
	
	protected int x,y; //All objects have a location, Right?
	protected ID id;
	protected int velocityX, velocityY;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;	
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);//All objects need to be rendered, right?
	public abstract Rectangle getBounds();//Collision Handling
	
	//GETTERS 'N SETTERS
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setID(ID id) {
		this.id = id;
	}
	public ID getID() {
		return id;
	}
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
	public int getVelocityX() {
		return velocityX;
	}
	public void setVelocityY(int velocityY) {
		this.velocityY=velocityY;
	}
	public int getVelocityY() {
		return velocityY;
	}
}
