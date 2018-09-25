package towerDefense;

import java.util.ArrayList;

public class Tower extends GameObject {
	
	static int imgID = 0;
	
	public Tower(float x, float y, int imgWidth, int imgHeight, ArrayList<GameObject> r) {
		super(x, y, imgID, imgWidth, imgHeight, r);
	}
	
}
