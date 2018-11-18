package io.aidantaylor.towerdefense.gameobject;

public class PattyTower extends Tower {
	
	public static final int DEFAULT_WIDTH = 40;
	public static final int DEFAULT_HEIGHT = 40;
	
	private static int price = 30;
	
<<<<<<< HEAD
	public PattyTower(int x, int y) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
=======
	public PattyTower(float x, float y) {
		super(x, y, width, height);
>>>>>>> 41b4b62d14a8789957bf897d6a8c80d2ac8efb28
	}
	
	public PattyTower(float x, float y, Anchor a) {
		super(x, y, width, height, a);
	}
	
	public static int getPrice() {
		return price;
	}
	
}
