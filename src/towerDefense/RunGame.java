package towerDefense;

import java.util.ArrayList;

public class RunGame {

	static private GameWindow window;
	static private ArrayList<GameObject> r;
	
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
		
		// redraw panel
		window.getMainPanel().revalidate();
		window.getMainPanel().repaint();
		
		gameLoop();
	}
	
	private static void updateLogic(long deltaT) {
		double w = deltaT/((double) OPTIMAL_TIME);
		t2.move(1, 0, w);
		t1.move(0, 1, w);
	}
	
	private static void gameLoop() {
		long lastFrame = System.nanoTime();
		
		
		while (running) {
			long now = System.nanoTime();
			long deltaT = now - lastFrame; 
			lastFrame = now;
			
			//update game state
			updateLogic(deltaT);
			
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
