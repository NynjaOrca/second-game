package com.nynjaorca.second.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.nynjaorca.second.framework.GameObject;
import com.nynjaorca.second.framework.KeyInput;
import com.nynjaorca.second.framework.ObjectId;
import com.nynjaorca.second.objects.Block;
import com.nynjaorca.second.objects.Player;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	private BufferedImage level = null;
	
	//OBJECT
	Handler handler;
	Camera cam;
	
	private void init() {
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level1.png"); //LOADING THE LEVEL
		
		handler = new Handler();
		cam = new Camera(0,0);
		
		loadImageLevel(level);
		
//		handler.addObject(new Player(100, 100, handler, ObjectId.Player));
		
//		handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
		
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		
		
		init();
		this.requestFocus();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		
	}
	
	private void tick() {
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ObjectId.Player) {
				cam.tick(handler.object.get(i));
			}
		}
	}
	
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		////////////////////////////////////
		
		
		/////////DRAW HERE//////////////////
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(cam.getX(), cam.getY());   //BEGIN CAM
		
		handler.render(g, cam);
		
		g2d.translate(-cam.getX(), -cam.getY());   //END CAM
		
		////////////////////////////////////
		g.dispose();
		bs.show();
			
	}
	
	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("Width: " + w + "Height: " + h);
		
		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) handler.addObject(new Block(xx*32, yy*32, ObjectId.Block));
				if(red == 0 && green == 0 && blue == 255) handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));

				
				
			}
		}
	}
	
	public static void main(String args[]) {
		new Window(1440, 810, "Second Game", new Game());
	}

}
