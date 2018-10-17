package io.aidantaylor.towerdefense.gameobject;

public class PattyTower extends Tower {
	
	private static int width = 40;
	private static int height = 40;
	
	private static int price = 30;
	
	public PattyTower(float x, float y) {
		super(x, y, width, height);
	}
	
	public PattyTower(float x, float y, Anchor a) {
		super(x, y, width, height, a);
	}
	
	public static int getPrice() {
		return price;
	}
	
}
