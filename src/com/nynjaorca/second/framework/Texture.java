package com.nynjaorca.second.framework;

import java.awt.image.BufferedImage;

import com.nynjaorca.second.window.BufferedImageLoader;

public class Texture {
	
	SpriteSheet ts, ps;
	private BufferedImage terrain_sheet = null;
	private BufferedImage player_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[3];
	
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			terrain_sheet = loader.loadImage("/terrain/snow-rock.png");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ts = new SpriteSheet(terrain_sheet);
		ps = new SpriteSheet(player_sheet);
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = ts.grabImage(1, 1, 32, 32); //---LEDGE LEFT
		block[1] = ts.grabImage(2, 1, 32, 32); //---FILLER
		block[2] = ts.grabImage(3, 1, 32, 32); //---LEDGE RIGHT
		
	}

}
