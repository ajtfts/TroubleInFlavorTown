package towerDefense;

public class MovingObject extends GameObject {
	
	private float vx, vy;
	
	public MovingObject(float x, float y, int imgID, int imgWidth, int imgHeight) {
		super(x, y, imgID, imgWidth, imgHeight);
	}
	
	public float[] getVelocity() {
		return new float[] {this.vx, this.vy};
	}
	
	public void setVelocity(float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	public void setVelocity(float[] v) {
		this.vx = v[0];
		this.vy = v[1];
	}
	
	public void move() {
		this.vx += this.vx;
		this.vy += this.vy;
	}
}