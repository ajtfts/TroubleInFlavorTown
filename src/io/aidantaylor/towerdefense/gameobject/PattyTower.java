package io.aidantaylor.towerdefense.gameobject;

/**
 * 
 * @author Aidan Taylor
 * @date 11/29/2018
 * @description Specific type of tower.
 */

public class PattyTower extends Tower {
	
	public static final int DEFAULT_WIDTH = 50; // made these different from the other tower just to show it does something
	public static final int DEFAULT_HEIGHT = 50;
	
	public static final int PRICE = 30;
		
	public PattyTower(float x, float y) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public PattyTower(float x, float y, Anchor a) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, a);
	}
	
}
