package io.aidantaylor.towerdefense.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import io.aidantaylor.towerdefense.utils.GameMap;
import io.aidantaylor.towerdefense.utils.ResourceLoader;

import io.aidantaylor.towerdefense.gameobject.*;

public class GameWindow {

	public static final int TILE_SIZE = 64;
	
	private Player player;
	private GameMap map;
	
	private Map<Class<? extends Tower>, BufferedImage> objectDict;
	private Map<Character, BufferedImage> tileDict;
	
	private int windowWidth = 1200, windowHeight = 675;
	private int mainPanelWidth = (int) (windowWidth * (5.0/6.0));
	private int xOffset = 0, yOffset = 0;
	
	private JFrame frame;
	private JPanel mainPanel, menuPanel;
	private JLabel healthLabel, moneyLabel;
	private JButton addTomTower, addPattyTower;
	
	private MouseListener mListener;
	private boolean mouseState = false;
	
	private Class<? extends Tower> towerSelected;
	
	private ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	
	public void setPlayer(Player p) {
		player = p;
		healthLabel.setText("Health: " + Integer.toString(player.getHealth()));
		moneyLabel.setText("Money: " + Integer.toString(player.getMoney()));
	}
	
	public void loadMap(GameMap m) {
		
		map = m;
		
		// initialize xOffset and yOffset so as to center map
		xOffset = (mainPanelWidth / 2) - TILE_SIZE * map.getWidth() / 2;
		yOffset = (windowHeight / 2) - TILE_SIZE * map.getHeight() / 2;
		
	}
	
	private void drawMap(Graphics g) {
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				g.drawImage(
						tileDict.get(map.getData().charAt(i*map.getWidth()+j)), // grab the correct image for the current tile from resArray
						j*TILE_SIZE+xOffset, i*TILE_SIZE+yOffset,
						TILE_SIZE, TILE_SIZE, null);
			}
		}
	}
	
	private void drawGameObjects(Graphics g) {
		for (int i = 0; i < renderList.size(); i++) {
			GameObject cur = renderList.get(i);
			float[] pos = cur.getPos();
			int[] dims = cur.getDims();
			g.drawImage(
					objectDict.get(cur.getClass()),
					(int) (pos[0]+xOffset), (int) (pos[1]+yOffset),
					dims[0], dims[1], null);
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
		tileDict = ResourceLoader.loadTileImages();
		objectDict = ResourceLoader.loadObjectImages();
	
		// create mainPanel object
		mainPanel = new JPanel() {

			private static final long serialVersionUID = 681094876978449737L;
			
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				if (map != null) {
					drawMap(g);
				}
				
				// draw all GameObjects inside renderList
				drawGameObjects(g);
				
				if (towerSelected != null) {
					Point pos = MouseInfo.getPointerInfo().getLocation();
					SwingUtilities.convertPointFromScreen(pos, mainPanel);
					g.drawImage(
							objectDict.get(towerSelected),
							(int) pos.getX(), (int) pos.getY(),
							40, 40, null);
				}
				
			}
		};
		
		mainPanel.setPreferredSize(new Dimension(mainPanelWidth, windowHeight));
		mainPanel.setBackground(Color.DARK_GRAY);
		frame.add(mainPanel);
		
		// create menuPanel
		menuPanel = new JPanel(new GridBagLayout());
		menuPanel.setPreferredSize(new Dimension((int) (windowWidth * (1.0/6.0)), windowHeight)); // set menuPanel size
		
		// begin creating all the components we will add to menuPanel
		
		// health and money labels 
		healthLabel = new JLabel();
		moneyLabel = new JLabel();
		
		// button to add a tower to the map
		addTomTower = new JButton("Tom Tosser");
		addTomTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				towerSelected = TomTower.class;
			}
		});
		
		// another button to add a different tower
		addPattyTower = new JButton("Patty Tosser");
		addPattyTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				towerSelected = PattyTower.class;
			}
		});
		
		//button to start the round
		JButton startRound = new JButton("Start");
		
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
				
				if (towerSelected != null) {
					Tower t1 = null;
					
					if (towerSelected == TomTower.class) {
						player.setMoney(player.getMoney()-TomTower.getPrice());
						t1 = new TomTower(e.getX()-xOffset, e.getY()-yOffset, GameObject.Anchor.CENTER);
					} else if (towerSelected == PattyTower.class) {
						player.setMoney(player.getMoney()-PattyTower.getPrice());
						t1 = new PattyTower(e.getX()-xOffset, e.getY()-yOffset);
					}
					System.out.println(t1.getPos()[0]);
					
					moneyLabel.setText("Money: "+player.getMoney());
					towerSelected = null;
					
				}
			
			}
			public void mousePressed(MouseEvent e) {mouseState = true;}
			public void mouseReleased(MouseEvent e) {mouseState = false;}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		};
		
		mainPanel.addMouseListener(mListener);
	
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public JLabel getHealthLabel() {
		return this.healthLabel;
	}
	
	public JLabel getMoneyLabel() {
		return this.moneyLabel;
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
