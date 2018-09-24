package towerDefense;

public class Tower extends GameObject {
	
	static int imgID = 0;
	
	public Tower(float x, float y, int imgWidth, int imgHeight) {
		super(x, y, imgID, imgWidth, imgHeight);
	}
	
}
