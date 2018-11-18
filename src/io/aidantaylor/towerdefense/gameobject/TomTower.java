package io.aidantaylor.towerdefense.gameobject;

public class TomTower extends Tower {
	
	public static final int DEFAULT_WIDTH = 40;
	public static final int DEFAULT_HEIGHT = 40;
	
	private static int price = 20;
	
<<<<<<< HEAD
	public TomTower(int x, int y) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
=======
	public TomTower(float x, float y) {
		super(x, y, width, height);
>>>>>>> 41b4b62d14a8789957bf897d6a8c80d2ac8efb28
	}
	
	public TomTower(float x, float y, Anchor a) {
		super(x, y, width, height, a);
	}

	public static int getPrice() {
		return price;
	}
	
}
