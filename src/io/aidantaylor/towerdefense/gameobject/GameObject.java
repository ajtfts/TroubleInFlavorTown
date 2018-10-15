package io.aidantaylor.towerdefense.gameobject;

import java.util.ArrayList;

public abstract class GameObject {
	
	private float x, y;

	private int imgWidth, imgHeight;
	
	private static ArrayList<GameObject> renderList;
	
	public GameObject(float x, float y, int w, int h) {
		this.x = x;
		this.y = y;
		this.imgWidth = w;
		this.imgHeight = h;
		renderList.add(this);
	}
	
	public static void linkRenderList(ArrayList<GameObject> r) {
		renderList = r;
	}
	
	public void move(float xSpeed, float ySpeed, double weight) {
		this.x = (float) (this.x + xSpeed * weight);
		this.y = (float) (this.y + ySpeed * weight);
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

}
