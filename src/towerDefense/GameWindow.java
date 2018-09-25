package towerDefense;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {

	public static final int TILE_SIZE = 64;
	
	private BufferedImage[] resArray; // array of all images
	
	private JFrame frame;
	private JPanel panel;
	
	private ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	
	private int mapWidth, mapHeight;
	private String tileInfo = "";
	private Map<Character, Integer> tileDict = new HashMap<Character, Integer>();
	
	public void loadMap(String fname) {
		File f = new File(fname);
		try {
			Scanner sc = new Scanner(f);
			
			int num = 1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (num == 1) {
					String[] dims = line.split("x");
					mapWidth = Integer.parseInt(dims[0]);
					mapHeight = Integer.parseInt(dims[1]);
				} else {
					tileInfo += line;
				}
				System.out.println(tileInfo);
				num++;
			}
			sc.close();
			panel.setPreferredSize(new Dimension(mapWidth*TILE_SIZE, mapHeight*TILE_SIZE));
			frame.pack();
		} catch (FileNotFoundException e) {
			System.out.println("Failed to find map file.");
			e.printStackTrace();
		}
	}
	
	private void drawMap(Graphics g) {
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				g.drawImage(resArray[tileDict.get(tileInfo.charAt(i*mapWidth+j))], j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
			}
		}
	}
	
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
				
				drawMap(g);
				
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
		
		// setup a string to integer dictionary to determine what texture should be used for a particular character in the map file
		tileDict.put('g', 1);
		tileDict.put('p', 2);
	}
	
	public JPanel getMainPanel() {
		return this.panel;
	}
	
	public ArrayList<GameObject> getRenderList() {
		return this.renderList;
	}
}
