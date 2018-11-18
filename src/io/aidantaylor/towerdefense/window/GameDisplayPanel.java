package io.aidantaylor.towerdefense.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import io.aidantaylor.towerdefense.gameobject.*;
import io.aidantaylor.towerdefense.utils.GameMap;
import io.aidantaylor.towerdefense.utils.ResourceLoader;

public class GameDisplayPanel extends JPanel {
	
	private static final long serialVersionUID = 681094876978449737L;
	
	public static final int TILE_SIZE = 64;
	
	GameMap map;
	private ArrayList<GameObject> renderList = new ArrayList<GameObject>();
	
	private Map<Character, BufferedImage> tileDict;
	private Map<Class<? extends Tower>, BufferedImage> objectDict;
	
	private MouseListener mListener;
	private boolean mouseState = false;
	
	private int width, height;
	private int xOffset = 0, yOffset = 0;
	
	private Class<? extends Tower> towerPreview;
	
	public GameDisplayPanel(int w, int h, GameMap m) {
		
		width = w;
		height = h;
		
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.DARK_GRAY);
		
		loadMap(m);
	
		mListener = new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (towerPreview != null) {
					if (towerPreview == TomTower.class) {
						new TomTower(e.getX()-xOffset, e.getY()-yOffset);
					} else if (towerPreview == PattyTower.class) {
						new PattyTower(e.getX()-xOffset, e.getY()-yOffset);
					}
					
					towerPreview = null;
				}
			}
			public void mousePressed(MouseEvent e) {mouseState = true;}
			public void mouseReleased(MouseEvent e) {mouseState = false;}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		};
		addMouseListener(mListener);
		
		GameObject.linkRenderList(renderList); // link GameObject list to GameObject class
		tileDict = ResourceLoader.loadTileImages();
		objectDict = ResourceLoader.loadObjectImages();
	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawMap(g); // draw map
		drawGameObjects(g); // draw all GameObjects inside renderList
		drawSelectedTower(g); // draw a preview image of a selected tower on the cursor
	}
	
	private void drawMap(Graphics g) {
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				g.drawImage(
						tileDict.get(map.getData().charAt(i*map.getWidth()+j)),
						j*TILE_SIZE+xOffset, i*TILE_SIZE+yOffset,
						TILE_SIZE, TILE_SIZE, null);
			}
		}
	}
	
	private void drawGameObjects(Graphics g) {
		for (GameObject cur : renderList) {
			float[] pos = cur.getPos();
			int[] dims = cur.getDims();
			g.drawImage(
					objectDict.get(cur.getClass()),
					(int) (pos[0]+xOffset) - (dims[0] / 2),(int) (pos[1]+yOffset) - (dims[1] / 2),
					dims[0], dims[1], null);
		}
	}
	
	private void drawSelectedTower(Graphics g) {
		if (towerPreview != null) {
			Point pos = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(pos, this);
			int previewWidth = Tower.getDictMap().get(towerPreview)[0];
			int previewHeight = Tower.getDictMap().get(towerPreview)[1];
			g.drawImage(
					objectDict.get(towerPreview),
					(int) pos.getX() - previewWidth / 2, (int) pos.getY() - previewHeight / 2,
					previewWidth, previewHeight, null);
		}
	}
	
	public void loadMap(GameMap m) {
		map = m;
		
		// initialize xOffset and yOffset so as to center map
		xOffset = (width / 2) - TILE_SIZE * map.getWidth() / 2;
		yOffset = (height / 2) - TILE_SIZE * map.getHeight() / 2;
	}
	
	public int getXOffset() {
		return xOffset;
	}
	
	public int getYOffset() {
		return yOffset;
	}
	
	public void setOffset(int x, int y) {
		xOffset = x;
		yOffset = y;
	}
	
	public void setTowerPreview(Class<? extends Tower> type) {
		towerPreview = type;
	}
	
	public boolean getMouseState() {
		return this.mouseState;
	}

}
