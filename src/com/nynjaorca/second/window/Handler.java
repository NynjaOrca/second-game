package com.nynjaorca.second.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.nynjaorca.second.framework.GameObject;
import com.nynjaorca.second.framework.ObjectId;
import com.nynjaorca.second.objects.Block;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g, Camera cam) {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			 if (tempObject.getX() > -cam.getX() - 32 && tempObject.getX() < -cam.getX() + 1440) {
	                if (tempObject.getY() > -cam.getY() - 32 && tempObject.getY() < -cam.getY() + 810) {
	                    tempObject.render(g);
	            }
	        }
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void createLevel() {
		for(int xx = 0; xx < Game.WIDTH; xx += 32) {
			addObject(new Block(xx, Game.HEIGHT-32, ObjectId.Block));
		}
		for(int xy = 0; xy < Game.HEIGHT - 64; xy += 32) {
			addObject(new Block(0, xy, ObjectId.Block));
		}
		for(int xy = 0; xy < Game.HEIGHT - 64; xy += 32) {
			addObject(new Block(Game.WIDTH - 32, xy, ObjectId.Block));
		}
		for(int px = 0; px < (Game.WIDTH/2); px += 32) {
			addObject(new Block((int)(px + Game.WIDTH/3), Game.HEIGHT-224, ObjectId.Block));
		}
	}

}
