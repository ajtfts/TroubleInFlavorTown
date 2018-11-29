package io.aidantaylor.towerdefense.gameobject;

public class PattyTower extends Tower {
	
	public static final int DEFAULT_WIDTH = 40;
	public static final int DEFAULT_HEIGHT = 40;
	
	public static final int PRICE = 30;
		
	public PattyTower(float x, float y) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public PattyTower(float x, float y, Anchor a) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, a);
	}
	
}
