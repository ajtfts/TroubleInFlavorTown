package io.aidantaylor.towerdefense.gameobject;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends GameObject {

	private static Map<Class<? extends Enemy>, int[]> dimsDict;
	static {
		dimsDict = new HashMap<Class<? extends Enemy>, int[]>();
		dimsDict.put(OrangeEnemy.class, new int[]{OrangeEnemy.DEFAULT_WIDTH, OrangeEnemy.DEFAULT_HEIGHT});
	}
	
	public Enemy(float x, float y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Enemy(float x, float y, int w, int h, Anchor a) {
		super(x, y, w, h, a);
	}
	
}
