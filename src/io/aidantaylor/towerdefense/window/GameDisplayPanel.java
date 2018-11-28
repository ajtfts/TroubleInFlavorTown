package io.aidantaylor.towerdefense.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import io.aidantaylor.towerdefense.gameobject.*;
import io.aidantaylor.towerdefense.utils.GameMap;
import io.aidantaylor.towerdefense.utils.IntObj;
import io.aidantaylor.towerdefense.utils.ResourceLoader;

public class GameDisplayPanel extends JPanel {
	
	private static final long serialVersionUID = 681094876978449737L;
	
	public static final int TILE_SIZE = 64;
	
	GameMap map;
	
	ArrayList<GameObject> renderList; 
	
	private Map<Character, BufferedImage> tileDict;
	private Map<Class<? extends GameObject>, BufferedImage> objectDict;
	
	private MouseListener mListener;
	private boolean mouseState = false;
	
	private int width, height;
	private int xOffset = 0, yOffset = 0;
	
	private IntObj playerMoney;
	
	private Class<? extends Tower> towerPreview;
	
	public GameDisplayPanel(int w, int h, GameMap m, IntObj money, ArrayList<GameObject> rList) {
		
		width = w;
		height = h;
		
		playerMoney = money;
		
		renderList = rList;
		
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.DARK_GRAY);
		
		loadMap(m);
	
		mListener = new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (towerPreview != null) {
					if (towerPreview == TomTower.class) {
						TomTower t = new TomTower(e.getX()-xOffset, e.getY()-yOffset);
						playerMoney.value -= Tower.getPriceMap().get(TomTower.class);
						t.Fire();
					} else if (towerPreview == PattyTower.class) {
						new PattyTower(e.getX()-xOffset, e.getY()-yOffset);
						playerMoney.value -= Tower.getPriceMap().get(PattyTower.class);
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
		Graphics2D g2d = (Graphics2D) g;
		
		drawMap(g2d); // draw map
		drawGameObjects(g2d); // draw all GameObjects inside renderList
		drawSelectedTower(g2d); // draw a preview image of a selected tower on the cursor
	}
	
	private void drawMap(Graphics2D g) {
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				g.drawImage(
						tileDict.get(map.getData().charAt(i*map.getWidth()+j)),
						j*TILE_SIZE+xOffset, i*TILE_SIZE+yOffset,
						TILE_SIZE, TILE_SIZE, null);
			}
		}
	}
	
	private void drawGameObjects(Graphics2D g) {
		
		AffineTransform transform;
		BufferedImage img;
		
		
		
		for (GameObject cur : renderList) {
			
			float[] pos = cur.getPos();
			int[] dims = cur.getDims();
			
			img = objectDict.get(cur.getClass());
			
			transform = AffineTransform.getTranslateInstance((pos[0]+xOffset) - (dims[0] / 2), (pos[1]+yOffset) - (dims[1] / 2));
			transform.scale((double) dims[0]/img.getWidth(), (double) dims[1]/img.getHeight());
			transform.rotate(Math.toRadians(cur.getRotation()), img.getWidth()/2, img.getHeight()/2);
			
			
			g.drawImage(img, transform, null);
		}
	}
	
	private void drawSelectedTower(Graphics2D g) {
		if (towerPreview != null) {
			Point pos = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(pos, this);
			int previewWidth = Tower.getDimsMap().get(towerPreview)[0];
			int previewHeight = Tower.getDimsMap().get(towerPreview)[1];
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
