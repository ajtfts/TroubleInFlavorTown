package io.aidantaylor.towerdefense.gameobject;

public abstract class Enemy extends GameObject {

	public Enemy(float x, float y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Enemy(float x, float y, int w, int h, Anchor a) {
		super(x, y, w, h, a);
	}
	
}
