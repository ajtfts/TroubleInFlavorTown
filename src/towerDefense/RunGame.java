package towerDefense;

import java.util.ArrayList;

import javax.swing.JPanel;

public class RunGame {

	static private GameWindow window;
	static private ArrayList<GameObject> r;
	static private JPanel p;
	
	private static boolean running = true;
	
	public static void main(String[] args) {
		window = new GameWindow();
		r = window.getRenderList();
		p = window.getMainPanel();
		// setup the initial state of the game
		MapLoader.load("maptest.txt", p);
		
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
