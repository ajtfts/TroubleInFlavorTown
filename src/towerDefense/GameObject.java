package towerDefense;

public class GameObject {
	
	private float x, y;
	
	private int imgID;
	private int imgWidth, imgHeight;
	
	public GameObject(float x, float y, int imgID, int w, int h) {
		this.x = x;
		this.y = y;
		this.imgID = imgID;
		this.imgWidth = w;
		this.imgHeight = h;
	}
	
	// get/set object position
	public float[] getPos() {
		return new float[] {this.x, this.y};
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPos(float[] pos) {
		this.x = pos[0];
		this.y = pos[1];
	}
	
	// get/set image dimensions
	public int[] getImgDims() {
		return new int[] {this.imgWidth, this.imgHeight};
	}
	
	public void setImgDims(int w, int h) {
		this.imgWidth = w;
		this.imgHeight = h;
	}
	
	public void setImgDims(int[] dims) {
		this.imgWidth = dims[0];
		this.imgHeight = dims[1];
	}
	
	// get/set object image
	public int getImage() {
		return imgID;
	}
	
	public void setImage(int imgID) {
		this.imgID = imgID;
	}

}
