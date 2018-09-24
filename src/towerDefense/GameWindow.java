package towerDefense;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {

	private JFrame frame;
	private JPanel panel;
	
	ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	
	public GameWindow() {
		
		frame = new JFrame("Trouble in FlavorTown");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		new Tower(50.0f, 50.0f, 50, 50);
		
		System.out.println(renderList);
		
		panel = new JPanel() {

			private static final long serialVersionUID = 681094876978449737L;
			
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawRect(0, 0, 100, 100);
			}
		};
		
		frame.add(panel);
		
		
	}
	
	public void draw() {
		
	}
}
