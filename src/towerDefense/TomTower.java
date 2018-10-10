package towerDefense;

import java.util.ArrayList;

public class TomTower extends Tower {

	private static int imgID = 3;
	
	public TomTower(int x, int y, ArrayList<GameObject> r) {
		super(x, y, 40, 40, imgID, r);
	}
	
}
