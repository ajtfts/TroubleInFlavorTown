package towerDefense;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {

	private BufferedImage[] resArray; // array of all images
	
	private JFrame frame;
	private JPanel panel;
	
	private ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	
	public GameWindow() {
		
		frame = new JFrame("Trouble in FlavorTown");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		
		resArray = ResourceLoader.load();
	
		panel = new JPanel() {

			private static final long serialVersionUID = 681094876978449737L;
			
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				for (int i = 0; i < renderList.size(); i++) {
					GameObject cur = renderList.get(i);
					float[] pos = cur.getPos();
					int[] dims = cur.getImgDims();
					g.drawImage(resArray[cur.getImageID()], (int) pos[0], (int) pos[1], dims[0], dims[1], null);
				}
			}
		};
		
		panel.setPreferredSize(new Dimension(200, 200));
		
		frame.add(panel);
		frame.pack();
		
		
	}
	
	public JPanel getMainPanel() {
		return this.panel;
	}
	
	public ArrayList<GameObject> getRenderList() {
		return this.renderList;
	}
}
