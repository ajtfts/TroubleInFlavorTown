package io.aidantaylor.towerdefense.gameobject;

import java.util.HashMap;
import java.util.Map;

import io.aidantaylor.towerdefense.main.Callback;
import io.aidantaylor.towerdefense.main.RecursiveCallback;
import io.aidantaylor.towerdefense.main.RunGame;

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
	
	protected boolean firing = false;
	protected int fireRate = 5;
	
	public Tower(float x, float y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Tower(float x, float y, int w, int h, Anchor a) {
		super(x, y, w, h, a);
	}
	
	public void Fire() {
		float xVelocity = (float) Math.cos(Math.toRadians(this.rotation-90));
		float yVelocity = (float) Math.sin(Math.toRadians(this.rotation-90));
		RunGame.queueCallback(() -> {
			TowerBullet bullet = new TowerBullet(this.getX(), this.getY());
			bullet.setVelocity(xVelocity*20, yVelocity*20);
			RunGame.queueCallback(() -> {
				// after the bullet has been alive for 6 seconds, remove it from renderList
				GameObject.getRenderList().remove(bullet);
			}, 6000);
		});
	}
	
	public void beginFiring() {
		firing = true;
		RecursiveCallback<Callback> recursive = new RecursiveCallback<>(); // apparently java doesn't let you recursively execute lambda functions. this is a workaround I found on stackoverflow, and wow does it look like shit
		recursive.func = () -> {
			RunGame.queueCallback(() -> {
				if (firing) {
					Fire();
					recursive.func.execute();
				}
			}, 1000);
		};
		RunGame.queueCallback(recursive.func);
	}
	
	public void stopFiring() {
		firing = false;
	}
	
	public boolean isFiring() {
		return firing;
	}
	
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
