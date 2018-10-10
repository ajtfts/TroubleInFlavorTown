package towerDefense;

import java.util.ArrayList;

public class PattyTower extends Tower {

	private static int imgID = 4;
	
	public PattyTower(int x, int y, ArrayList<GameObject> r) {
		super(x, y, 40, 40, imgID, r);
	}
	
}
