package towerDefense;

import java.awt.image.BufferedImage;

public class RunGame {

	static GameWindow window;
	
	static BufferedImage[] resources;
	
	private static boolean running = true;
	
	public static void main(String[] args) {
		window = new GameWindow();
		
		// setup the initial state of the game
		
		gameLoop();
	}
	
	private static void updateLogic() {
		System.out.println("hey");
	}
	
	private static void gameLoop() {
		while (running) {
			updateLogic();
			running = false;
		}
	}
	
}
