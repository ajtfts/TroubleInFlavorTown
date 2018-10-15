package io.aidantaylor.towerdefense.gameobject;

public class TomTower extends Tower {
	
	private static int width = 40;
	private static int height = 40;
	
	private static int price = 20;
	
	public TomTower(int x, int y) {
		super(x, y, width, height);
	}
	
	public static int getPrice() {
		return price;
	}
	
}
