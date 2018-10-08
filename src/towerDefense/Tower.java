package towerDefense;

import java.util.ArrayList;

public class Tower extends GameObject {
	
	static int imgID = 0;
	
	public Tower(float x, float y, ArrayList<GameObject> r) {
		super(x, y, imgID, 40, 40, r);
	}
	
}
