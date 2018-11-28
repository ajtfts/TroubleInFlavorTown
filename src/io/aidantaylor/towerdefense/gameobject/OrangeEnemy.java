package io.aidantaylor.towerdefense.gameobject;

public class OrangeEnemy extends Enemy {

	public OrangeEnemy(float x, float y, int w, int h) {
		super(x, y, w, h);
		this.vx = 1;
	}

	public OrangeEnemy(float x, float y, int w, int h, Anchor a) {
		super(x, y, w, h, a);
	}
	
}
