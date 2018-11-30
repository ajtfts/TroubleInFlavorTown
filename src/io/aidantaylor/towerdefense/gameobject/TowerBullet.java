package io.aidantaylor.towerdefense.gameobject;

/**
 * 
 * @author Aidan Taylor
 * @date 11/29/2018
 * @description TowerBullet class, extends GameObject. It's the class for the bullets that the towers shoot.
 */

public class TowerBullet extends GameObject {
	
	public static final int DIAMETER = 10;
	public static final int DAMAGE = 1;
	
	public TowerBullet(float x, float y) {
		super(x, y, DIAMETER, DIAMETER);
	}

}
