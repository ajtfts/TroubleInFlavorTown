package towerDefense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {

	private BufferedImage[] resArray; // array of all images
	
	private JFrame frame;
	private JPanel panel;
	
	ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	
	public GameWindow() {
		
		frame = new JFrame("Trouble in FlavorTown");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		resArray = ResourceLoader.load();
		
		Tower t1 = new Tower(50.0f, 50.0f, 64, 64);
		Tower t2 = new Tower(100.0f, 100.0f, 64, 64);
		renderList.add(t1);
		renderList.add(t2);
		
		panel = new JPanel() {

			private static final long serialVersionUID = 681094876978449737L;
			
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawRect(0, 0, 100, 100);
				
				for (int i = 0; i < renderList.size(); i++) {
					GameObject cur = renderList.get(i);
					float[] pos = cur.getPos();
					int[] dims = cur.getImgDims();
					g.drawImage(resArray[cur.getImageID()], (int) pos[0], (int) pos[1], dims[0], dims[1], null);
				}
			}
		};
		
		frame.add(panel);
		
		
	}
}
