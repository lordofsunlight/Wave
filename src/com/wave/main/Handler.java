package com.wave.main;

import java.awt.Graphics;
import java.util.LinkedList;

/*
 * Is going to maintain or update and render all objects.
 * Since there are multiple objects, need a handler to manage all of them.
 * This will loop through objects and render them
 * Need a list of all game object(LinkedList)
 */
public class Handler {

	LinkedList<GameObject> object= new LinkedList<GameObject>();
	
	public void tick() {
		
		for(int i = 0; i < object.size(); i++) {
			//GameObject tempObject = object.get(i);
			object.get(i).tick();
			//tempObject.tick();
		}
	}
	
	//Thanks to youtube comments again, iterating through shrinking linked lists is crazy stuff
	public void clearEnemies() {
		for (int i = 0; i < this.object.size(); i++) {
            GameObject tempObject = this.object.get(i);
            if (tempObject.getID() != ID.Player) {
                this.removeObject(tempObject);
                i--;
            }
        }
	}
	
	public void clearPlayer() {
		for(int i = 0; i < object.size(); i++) {
			if(object.get(i).getID() == ID.Player) {
				this.removeObject(object.get(i));
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			//GameObject tempObject = object.get(i);
			object.get(i).render(g);//Is this easier?
			//tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	
}
