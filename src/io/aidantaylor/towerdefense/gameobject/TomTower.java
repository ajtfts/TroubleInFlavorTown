package io.aidantaylor.towerdefense.gameobject;

public class TomTower extends Tower {
	
	public static final int DEFAULT_WIDTH = 40;
	public static final int DEFAULT_HEIGHT = 40;
	
	private static int price = 20;

	public TomTower(float x, float y) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public TomTower(float x, float y, Anchor a) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, a);
	}

	public void Fire() {
		System.out.println("Fire TomTower");
		new TowerBullet(this.getX(), this.getY(), 10, 10).changeVelocity(0, -12);;
		
	}
	
	public static int getPrice() {
		return price;
	}
	
}
