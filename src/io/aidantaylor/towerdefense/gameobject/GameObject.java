package io.aidantaylor.towerdefense.gameobject;

import java.util.ArrayList;

public abstract class GameObject {
	
	public enum Anchor {
		TOP_LEFT, CENTER
	}
	
	protected float x, y;
	protected int width, height;
	
	protected float vx = 0, vy = 0;
	protected float rotation = 0;
	
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
			this.x = x + this.width / 2;
			this.y = y + this.height / 2;
			break;
		default:
			this.x = x;
			this.y = y;
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
		switch (a) {
		case TOP_LEFT:
			return new float[] {this.x - this.width / 2, this.y - this.height / 2};
		default:
			return new float[] {this.x, this.y};
		}
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getX(Anchor a) {
		switch (a) {
		case TOP_LEFT:
			return this.x - this.width / 2;
		default:
			return this.x;
		}
	}
	
	public float getY(Anchor a) {
		switch (a) {
		case TOP_LEFT:
			return this.y - this.height / 2;
		default:
			return this.y;
		}
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPos(float x, float y, Anchor a) {
		switch (a) {
		case TOP_LEFT:
			this.x = x + this.width / 2;
			this.y = y + this.height / 2;
			break;
		default:
			this.x = x;
			this.y = y;
			break;
		}
	}
	
	public void setPos(float[] pos) {
		this.x = pos[0];
		this.y = pos[1];
	}
	
	public void setVelocity(float xVelocity, float yVelocity) {
		this.vx = xVelocity;
		this.vy = yVelocity;
	}
	
	public float[] getVelocity() {
		return new float[] {this.vx, this.vy};
	}
	
	public float getRotation() {
		return this.rotation;
	}
	
	public void setRotation(float r) {
		this.rotation = r;
	}
	
	public void changeRotation(float dr) {
		this.rotation += dr;
	}
	
	public void lookAt(float targetX, float targetY) {
		this.setRotation((float) Math.toDegrees(Math.atan2(targetY - this.y, targetX - this.x)) + 90);
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
	
	public static ArrayList<GameObject> getRenderList() {
		return renderList;
	}

}
