package io.aidantaylor.towerdefense.gameobject;

public class TowerBullet extends GameObject {
	
	public static final int DIAMETER = 10;
	public static final int DAMAGE = 1;
	
	public TowerBullet(float x, float y) {
		super(x, y, DIAMETER, DIAMETER);
	}

}
