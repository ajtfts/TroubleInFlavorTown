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
		System.out.printf("Fire TomTower at %f degrees\n", this.rotation);
		float xVelocity = (float) Math.cos(Math.toRadians(this.rotation-90));
		float yVelocity = (float) Math.sin(Math.toRadians(this.rotation-90));
		new TowerBullet(this.getX(), this.getY(), 10, 10).setVelocity(xVelocity, yVelocity);;
		
	}
	
	public static int getPrice() {
		return price;
	}
	
}
