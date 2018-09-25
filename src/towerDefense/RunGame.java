package towerDefense;

import java.util.ArrayList;

public class RunGame {

	static private GameWindow window;
	static private ArrayList<GameObject> r;
	
	private static boolean running = true;
	
	public static void main(String[] args) {
		window = new GameWindow();
		r = window.getRenderList();
		// setup the initial state of the game
		window.loadMap("maptest.txt");
		
		new Tower(0.0f, 0.0f, 32, 32, r);
		new Tower(100.0f, 100.0f, 32, 32, r);
		
		// redraw panel
		window.getMainPanel().revalidate();
		window.getMainPanel().repaint();
		
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
