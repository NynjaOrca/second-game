package com.nynjaorca.second.objects;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.nynjaorca.second.framework.GameObject;
import com.nynjaorca.second.framework.ObjectId;
import com.nynjaorca.second.framework.Texture;
import com.nynjaorca.second.window.Camera;
import com.nynjaorca.second.window.Game;

public class Block extends GameObject{
	
	Texture tex = Game.getInstance();
	private int type;

	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		
		if(type == 0) {//---LEDGE LEFT
			g.drawImage(tex.block[0], (int) x, (int) y, null);
		}
		if(type == 1) {//---MIDDLE
			g.drawImage(tex.block[1], (int) x, (int) y, null);
		}
		if(type == 2) {//---LEDGE RIGHT
			g.drawImage(tex.block[2], (int) x, (int) y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
