package towerDefense;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow {

	public static final int TILE_SIZE = 64;
	
	private BufferedImage[] tileResArray, objectResArray; // array of all tile images and gameobject images, respectively
	
	private int windowWidth = 1200, windowHeight = 675;
	private int mainPanelWidth = (int) (windowWidth * (5.0/6.0));
	
	private JFrame frame;
	private JPanel mainPanel, menuPanel;
	private JLabel healthLabel, moneyLabel;
	private JButton addTomTower, addPattyTower;
	
	private MouseListener mListener;
	private boolean mouseState = false;
	private int towerSelected = -1;
	
	private ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	private int xOffset = 0, yOffset = 0;
	
	private int mapWidth, mapHeight;
	private String tileInfo = "";
	private Map<Character, Integer> tileDict = new HashMap<Character, Integer>();
	
	public void loadMap(String fname) { // takes text file, converts it into a string containing tile information
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
				num++;
			}
			sc.close();
		
			// initialize xOffset and yOffset so as to center map
			xOffset = (mainPanelWidth / 2) - TILE_SIZE * mapWidth / 2;
			yOffset = (windowHeight / 2) - TILE_SIZE * mapHeight / 2;
		
		} catch (FileNotFoundException e) {
			System.out.println("Failed to find map file.");
			e.printStackTrace();
		}
	}
	
	private void drawMap(Graphics g) {
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				g.drawImage(
						tileResArray[tileDict.get(tileInfo.charAt(i*mapWidth+j))], // grab the correct image for the current tile from resArray
						j*TILE_SIZE+xOffset, i*TILE_SIZE+yOffset,
						TILE_SIZE, TILE_SIZE, null);
			}
		}
	}
	
	public GameWindow() {
		
		// initialize JFrame
		frame = new JFrame("Trouble in FlavorTown");
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// link GameObject list to GameObject class
		GameObject.linkRenderList(renderList);
		
		// load resources into tileResArray and objectResArray
		tileResArray = ResourceLoader.loadTileImages();
		objectResArray = ResourceLoader.loadObjectImages();
	
		// create mainPanel object
		mainPanel = new JPanel() {

			private static final long serialVersionUID = 681094876978449737L;
			
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				drawMap(g);
				
				// draw all GameObjects inside renderList
				for (int i = 0; i < renderList.size(); i++) {
					GameObject cur = renderList.get(i);
					float[] pos = cur.getPos();
					int[] dims = cur.getImgDims();
					g.drawImage(
							objectResArray[cur.getImageID()],
							(int) (pos[0]+xOffset) - (dims[0] / 2), (int) (pos[1]+yOffset) - (dims[1] / 2),
							dims[0], dims[1], null);
				}
			}
		};
		
		mainPanel.setPreferredSize(new Dimension(mainPanelWidth, windowHeight));
		mainPanel.setBackground(Color.DARK_GRAY);
		frame.add(mainPanel);
		
		// begin creating all the components we will add to menuPanel
		
		// health and money labels 
		healthLabel = new JLabel("Health: ");
		moneyLabel = new JLabel("Money: ");
		
		// button to add a tower to the map
		addTomTower = new JButton("Tom Tosser");
		
		addTomTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				towerSelected = 0;
			}
		});
		
		// another button to add a different tower
		addPattyTower = new JButton("Patty Tosser");
		
		addPattyTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				towerSelected = 1;
			}
		});
		
		//button to start the round
		JButton startRound = new JButton("Start");
		
		// create menuPanel
		menuPanel = new JPanel(new GridBagLayout()) {

			private static final long serialVersionUID = 1L;			
			
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
			}
			
		};
		
		menuPanel.setPreferredSize(new Dimension((int) (windowWidth * (1.0/6.0)), windowHeight)); // set menuPanel size
		
		// setup GridBag Constraints for menuPanel
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		menuPanel.add(healthLabel);
		
		c.gridx = 0;
		c.gridy = 1;
		menuPanel.add(moneyLabel);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		menuPanel.add(addTomTower, c);
		
		c.gridx = 1;
		c.gridy = 2;
		menuPanel.add(addPattyTower, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		menuPanel.add(startRound, c);
		
		frame.add(menuPanel);
		
		frame.pack();
		
		// mouse events
		mListener = new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				
				switch (towerSelected) {
				case 0:
					new TomTower(e.getX()-xOffset, e.getY()-yOffset);
					towerSelected = -1;
					break;
				case 1:
					new PattyTower(e.getX()-xOffset, e.getY()-yOffset);
					towerSelected = -1;
				}
			}
			public void mousePressed(MouseEvent e) {mouseState = true;}
			public void mouseReleased(MouseEvent e) {mouseState = false;}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		};
		
		mainPanel.addMouseListener(mListener);
		
		// setup a string to integer dictionary to determine what texture should be used for a particular character in the map file
		tileDict.put('g', 1);
		tileDict.put('p', 2);
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public ArrayList<GameObject> getRenderList() {
		return this.renderList;
	}
	
	public boolean getMouseState() {
		return this.mouseState;
	}
	
	public int getXOffset() {
		return this.xOffset;
	}
	
	public int getYOffset() {
		return this.yOffset;
	}
	
	public void setOffset(int x, int y) {
		this.xOffset = x;
		this.yOffset = y;
	}
}
