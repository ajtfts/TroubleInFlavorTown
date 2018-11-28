package io.aidantaylor.towerdefense.gameobject;

import java.util.HashMap;
import java.util.Map;

public abstract class Tower extends GameObject {

	private static Map<Class<? extends Tower>, int[]> dimsDict;
	private static Map<Class<? extends Tower>, Integer> priceDict;
	static {
		dimsDict = new HashMap<Class<? extends Tower>, int[]>();
		dimsDict.put(TomTower.class, new int[]{TomTower.DEFAULT_WIDTH, TomTower.DEFAULT_HEIGHT});
		dimsDict.put(PattyTower.class, new int[] {PattyTower.DEFAULT_WIDTH, PattyTower.DEFAULT_HEIGHT});
		
		priceDict = new HashMap<Class<? extends Tower>, Integer>();
		priceDict.put(TomTower.class, TomTower.PRICE);
		priceDict.put(PattyTower.class, PattyTower.PRICE);
		
	}
	
	public Tower(float x, float y, int w, int h) {
		super(x, y, w, h);
		this.lookAt(0, 0);
	}
	
	public Tower(float x, float y, int w, int h, Anchor a) {
		super(x, y, w, h, a);
		this.lookAt(0, 0);
	}
	
	public abstract void Fire();
	
	public static Map<Class<? extends Tower>, int[]> getDimsMap() {
		return dimsDict;
	}
	
	public static Map<Class<? extends Tower>, Integer> getPriceMap() {
		return priceDict;
	}
	
	public enum towerTypes {
		NONE, TOM, PATTY
	}
	
}
