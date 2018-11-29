package io.aidantaylor.towerdefense.gameobject;

import io.aidantaylor.towerdefense.main.RunGame;

public class TomTower extends Tower {
	
	public static final int DEFAULT_WIDTH = 40;
	public static final int DEFAULT_HEIGHT = 40;
	
	public static final int PRICE = 20;

	public TomTower(float x, float y) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public TomTower(float x, float y, Anchor a) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, a);
	}

	public void Fire() {
		float xVelocity = (float) Math.cos(Math.toRadians(this.rotation-90));
		float yVelocity = (float) Math.sin(Math.toRadians(this.rotation-90));
		RunGame.queueCallback(() -> {
			new TowerBullet(this.getX(), this.getY(), 10, 10).setVelocity(xVelocity, yVelocity);
		});
		
	}
	
}
