package io.aidantaylor.towerdefense.gameobject;

import java.util.HashMap;
import java.util.Map;

public abstract class Tower extends GameObject {

	private static Map<Class<? extends Tower>, int[]> dimsDict;
	static {
		dimsDict = new HashMap<Class<? extends Tower>, int[]>();
		dimsDict.put(TomTower.class, new int[]{TomTower.DEFAULT_WIDTH, TomTower.DEFAULT_HEIGHT});
		dimsDict.put(PattyTower.class, new int[] {PattyTower.DEFAULT_WIDTH, PattyTower.DEFAULT_HEIGHT});
	}
	
	public Tower(float x, float y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Tower(float x, float y, int w, int h, Anchor a) {
		super(x, y, w, h, a);
	}
	
	public abstract void Fire();
	
	public static Map<Class<? extends Tower>, int[]> getDictMap() {
		return dimsDict;
	}
	
	public enum towerTypes {
		NONE, TOM, PATTY
	}
	
}
