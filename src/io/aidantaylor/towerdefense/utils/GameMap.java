package io.aidantaylor.towerdefense.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import io.aidantaylor.towerdefense.gameobject.GameObject;
import io.aidantaylor.towerdefense.gameobject.GameObject.Anchor;

/**
 * 
 * @author Aidan Taylor
 * @date 11/29/2018
 * @description Contains the GameMap class. GameMap takes a filename representing a text file containing map information and parses it.
 */

public class GameMap {

	private int width, height;
	private String data = "";
	private int[] startPos, endPos;
	
	public GameMap(String fname) {
		
		File f = new File(fname);
		try {
			Scanner sc = new Scanner(f);
			
			int lineNum = 1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (lineNum == 1) {
					String[] dims = line.split("x");
					width = Integer.parseInt(dims[0]);
					height = Integer.parseInt(dims[1]);
				} else {
					for (int i = 0; i < line.length(); i++) {
						data += line.charAt(i);
						if (line.charAt(i) == 's') {
							startPos = new int[] {i, lineNum-2};
						}
						if (line.charAt(i) == 'e') {
							endPos = new int[] {i, lineNum-2};
						}
					}
				}
				lineNum++;
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Failed to find map file.");
			e.printStackTrace();
		}
	
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getData() {
		return data;
	}
	
	public int[] getStartPos() {
		return startPos;
	}
	
	public int[] getEndPos() {
		return endPos;
	}
	
	public static float[] MapToGamePos(int x, int y, int tileSize, Anchor a) {
		switch (a) {
		case TOP_LEFT:
			return new float[] {x*tileSize, y*tileSize};
		default:
			return new float[] {x*tileSize+tileSize/2, y*tileSize+tileSize/2};
		}
	}
	
	public static boolean PointInTile(float pos, float pos2, int tileX, int tileY, int tileSize) {
		float[] gamePos = MapToGamePos(tileX, tileY, tileSize, GameObject.Anchor.TOP_LEFT);
		return gamePos[0] <= pos && pos <= gamePos[0]+tileSize;
	}
}
