package io.aidantaylor.towerdefense.gameobject;

public abstract class Tower extends GameObject {
	
	public Tower(float x, float y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Tower(float x, float y, int w, int h, Anchor a) {
		super(x, y, w, h, a);
	}
	
	public enum towerTypes {
		NONE, TOM, PATTY
	}
	
}
