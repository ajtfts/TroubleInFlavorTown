package io.aidantaylor.towerdefense.gameobject;

/**
 * 
 * @author Aidan Taylor
 * @date 11/29/2018
 * @description Specific type of enemy.
 */

public class OrangeEnemy extends Enemy {

	public static final int DEFAULT_WIDTH = 40;
	public static final int DEFAULT_HEIGHT = 40;

	public OrangeEnemy(float x, float y) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	public OrangeEnemy(float x, float y, Anchor a) {
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, a);
	}
	
}
