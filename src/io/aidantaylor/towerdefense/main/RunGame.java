package io.aidantaylor.towerdefense.main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import io.aidantaylor.towerdefense.gameobject.GameObject;
import io.aidantaylor.towerdefense.gameobject.OrangeEnemy;
import io.aidantaylor.towerdefense.utils.GameMap;
import io.aidantaylor.towerdefense.utils.IntObj;
import io.aidantaylor.towerdefense.window.GameDisplayPanel;
import io.aidantaylor.towerdefense.window.GameMenuPanel;
import io.aidantaylor.towerdefense.window.GameWindow;



public class RunGame {

	private static GameWindow window;
	private static GameDisplayPanel display;
	private static GameMenuPanel menu;
	private static float aspectRatio = 16.0f/9.0f;
	private static int windowHeight = 1200, windowWidth = (int) (windowHeight / aspectRatio);
	
	private static IntObj playerMoney = new IntObj(100), playerHealth = new IntObj(3);
	private static ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	
	private static final int TARGET_FPS = 60;
	private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // convert target frames-per-second to target time between frames in nanoseconds
	private static boolean running = true;
	
	public static void main(String[] args) {
		
		window = new GameWindow(windowHeight, windowWidth, new GameMap("maptest.txt"), playerMoney, playerHealth, renderList);
		display = window.getDisplayPanel();
		menu = window.getMenuPanel();
		
		menu.setMoneyLabel(playerMoney.value);
		menu.setHealthLabel(playerHealth.value);
		
		new OrangeEnemy(0, 0, 40, 40).setRotation(0);;
		
		
		gameLoop();
	}
	
	private static void updateLogic(long deltaT) {
		double w = deltaT/((double) OPTIMAL_TIME);
		
		for (GameObject obj : renderList) {
			float[] velocity = obj.getVelocity();
			obj.move(velocity[0], velocity[1], w);
		}
		
		menu.setMoneyLabel(playerMoney.value);
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



