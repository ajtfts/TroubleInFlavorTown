package io.aidantaylor.towerdefense.main;

import java.awt.MouseInfo;
import java.awt.Point;

import io.aidantaylor.towerdefense.utils.GameMap;

public class RunGame {

	private static GameWindow window;
	
	private static final int TARGET_FPS = 60;
	private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // convert target frames-per-second to target time between frames in nanoseconds
	private static boolean running = true;
	
	public static void main(String[] args) {
		window = new GameWindow();
		window.loadMap(new GameMap("maptest.txt"));
		
		Player player = new Player();
		window.setPlayer(player);
		
		gameLoop();
	}
	
	private static void updateLogic(long deltaT) {
		double w = deltaT/((double) OPTIMAL_TIME);
	}
	
	private static void gameLoop() {
		
		boolean first = true;
		
		long now, deltaT;
		long lastFrame = System.nanoTime();
		
		int difX = 0, difY = 0, mapX = 0, mapY = 0, initXOffset = 0, initYOffset = 0;
		
		while (running) {
			now = System.nanoTime();
			deltaT = now - lastFrame; 
			
			lastFrame = now;
			
			//update game state
			updateLogic(deltaT);
			
			// change map view by dragging mouse
			if (window.getMouseState()) {
				if (first) {
					Point mPos = MouseInfo.getPointerInfo().getLocation();
					mapX = (int) mPos.getX();
					mapY = (int) mPos.getY();
					initXOffset = window.getXOffset();
					initYOffset = window.getYOffset();
					first = false;
				} else {
					Point mPos = MouseInfo.getPointerInfo().getLocation();
					difX = (int) mPos.getX() - mapX;
					difY = (int) mPos.getY() - mapY;
					window.setOffset(initXOffset+difX, initYOffset+difY);
				}
			} else {
				first = true;
			}
			
			// redraw
			window.getMainPanel().repaint();
			
			try {
				
				now = System.nanoTime();
				
				if (lastFrame - now >= 0)
					// Sleep for OPTIMAL_TIME - how long updateLogic and repaint took. Divide by 10^6 as Thread.sleep takes milliseconds
					Thread.sleep((lastFrame-now + OPTIMAL_TIME)/1000000); 
				else
					Thread.sleep(OPTIMAL_TIME/1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}



