package towerDefense;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

public class RunGame {

	private static GameWindow window;
	private static ArrayList<GameObject> r;
	
	private static final int TARGET_FPS = 60;
	private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // convert target frames-per-second to target time between frames in nanoseconds
	private static boolean running = true;
	
	private static Tower t1, t2;
	
	public static void main(String[] args) {
		window = new GameWindow();
		r = window.getRenderList();
		
		// setup the initial state of the game
		window.loadMap("maptest.txt");
		
		t1 = new Tower(0.0f, 0.0f, 32, 32, r);
		t2 = new Tower(0.0f, 100.0f, 32, 32, r);
		
		gameLoop();
	}
	
	private static void updateLogic(long deltaT) {
		double w = deltaT/((double) OPTIMAL_TIME);
		t2.move(1, 0, w);
		t1.move(0, 1, w);
	}
	
	private static void gameLoop() {
		
		boolean first = true;
		
		long lastFrame = System.nanoTime();
		
		int difX = 0, difY = 0, mapX = 0, mapY = 0, initXOffset = 0, initYOffset = 0;
		
		while (running) {
			long now = System.nanoTime();
			long deltaT = now - lastFrame; 
			
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
				// Sleep for OPTIMAL_TIME - how long updateLogic and repaint took. Divide by 10^6 as Thread.sleep takes milliseconds
				Thread.sleep((lastFrame-System.nanoTime() + OPTIMAL_TIME)/1000000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
