package io.aidantaylor.towerdefense.window;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import io.aidantaylor.towerdefense.gameobject.GameObject;
import io.aidantaylor.towerdefense.utils.GameMap;
import io.aidantaylor.towerdefense.utils.IntObj;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 7214175154438970031L;
	
	private int width, height;

	private GameDisplayPanel display;
	private GameMenuPanel menu;

	public GameWindow(int w, int h, GameMap m, IntObj money, IntObj health, ArrayList<GameObject> rList) {
		
		// initialize JFrame
		super("Trouble in FlavorTown");
		setResizable(false);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		width = w;
		height = h;
	
		// create display panel. width should be 5/6's of the window width, height should be same as window
		display = new GameDisplayPanel((int) (width * (5.0/6.0)), height, m, money, rList);
		add(display);
		
		// create menu panel. width should take up the 1/6 of the screen that the main game display doesn't.
		menu = new GameMenuPanel((int) (width * (1.0/6.0)), height, money, display); 
		add(menu);
		
		pack();
	
	}
	
	public GameDisplayPanel getDisplayPanel() {
		return this.display;
	}
	
	public GameMenuPanel getMenuPanel() {
		return this.menu;
	}
}
