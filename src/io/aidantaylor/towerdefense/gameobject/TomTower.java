package io.aidantaylor.towerdefense.gameobject;

/**
 * 
 * @author Aidan Taylor
 * @date 11/29/2018
 * @description Specific type of tower.
 */

public class TomTower extends Tower {
	
	public static final int DEFAULT_WIDTH = 60;
	public static final int DEFAULT_HEIGHT = 60;
	
	public static final int PRICE = 20;

	public TomTower(float x, float y) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public TomTower(float x, float y, Anchor a) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, a);
	}	
	
	
}
