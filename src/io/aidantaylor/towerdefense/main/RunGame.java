package io.aidantaylor.towerdefense.main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import io.aidantaylor.towerdefense.gameobject.GameObject;
import io.aidantaylor.towerdefense.gameobject.TomTower;
import io.aidantaylor.towerdefense.gameobject.Tower;
import io.aidantaylor.towerdefense.utils.GameMap;
import io.aidantaylor.towerdefense.window.GameDisplayPanel;
import io.aidantaylor.towerdefense.window.GameWindow;



public class RunGame {

	private static GameWindow window;
	private static GameDisplayPanel display;
	private static float aspectRatio = 16.0f/9.0f;
	private static int windowHeight = 1200, windowWidth = (int) (windowHeight / aspectRatio);
	
	private static ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	
	private static final int TARGET_FPS = 60;
	private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // convert target frames-per-second to target time between frames in nanoseconds
	private static boolean running = true;
	
	public static void main(String[] args) {
		
		window = new GameWindow(windowHeight, windowWidth, new GameMap("maptest.txt"), renderList);
		display = window.getDisplayPanel();
		
		gameLoop();
	}
	
	private static void updateLogic(long deltaT) {
		double w = deltaT/((double) OPTIMAL_TIME);
		
		for (GameObject obj : renderList) {
			float[] velocity = obj.getVelocity();
			obj.move(velocity[0], velocity[1], w);
		}
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
			if (display.getMouseState()) {
				if (first) {
					Point mPos = MouseInfo.getPointerInfo().getLocation();
					mapX = (int) mPos.getX();
					mapY = (int) mPos.getY();
					initXOffset = display.getXOffset();
					initYOffset = display.getYOffset();
					first = false;
				} else {
					Point mPos = MouseInfo.getPointerInfo().getLocation();
					difX = (int) mPos.getX() - mapX;
					difY = (int) mPos.getY() - mapY;
					display.setOffset(initXOffset+difX, initYOffset+difY);
				}
			} else {
				first = true;
			}
			
			// redraw
			display.repaint();
			
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



