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
}
