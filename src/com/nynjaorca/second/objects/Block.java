package com.nynjaorca.second.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.nynjaorca.second.framework.GameObject;
import com.nynjaorca.second.framework.ObjectId;
import com.nynjaorca.second.window.Camera;
import com.nynjaorca.second.window.Game;

public class Block extends GameObject{

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
