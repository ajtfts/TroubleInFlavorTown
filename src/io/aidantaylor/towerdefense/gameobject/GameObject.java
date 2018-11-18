package io.aidantaylor.towerdefense.gameobject;

import java.util.ArrayList;

public abstract class GameObject {
	
	public enum Anchor {
		TOP_LEFT, CENTER
	}
	
	private float x, y;

	private int width, height;
	
	private static ArrayList<GameObject> renderList;
	
	public GameObject(float x, float y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		renderList.add(this);
	}
	
	public GameObject(float x, float y, int w, int h, Anchor a) {
		this.width = w;
		this.height = h;
		
		switch (a) {
		case TOP_LEFT:
			this.x = x;
			this.y = y;
			break;
		case CENTER:
			this.x = x - this.width / 2;
			this.y = y - this.height / 2;
		}
		
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
	
	public float[] getPos(Anchor a) {
		if (a == Anchor.TOP_LEFT)
			return new float[] {this.x, this.y};
		else
			return new float[] {this.x + this.width / 2, this.y + this.height / 2};
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPos(float x, float y, Anchor a) {
		if (a == Anchor.TOP_LEFT) {
			this.x = x;
			this.y = y;
		} else if (a == Anchor.CENTER) {
			this.x = x-this.width/2;
			this.y = y-this.height/2;
		}
	}
	
	public void setPos(float[] pos) {
		this.x = pos[0];
		this.y = pos[1];
	}
	
	// get/set image dimensions
	public int[] getDims() {
		return new int[] {this.width, this.height};
	}
	
	public void setDims(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
	public void setDims(int[] dims) {
		this.width = dims[0];
		this.height = dims[1];
	}

}
