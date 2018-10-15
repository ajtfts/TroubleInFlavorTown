package io.aidantaylor.towerdefense.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameMap {

	private int width, height;
	private String data = "";
	
	public GameMap(String fname) {
		
		File f = new File(fname);
		try {
			Scanner sc = new Scanner(f);
			
			int num = 1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (num == 1) {
					String[] dims = line.split("x");
					width = Integer.parseInt(dims[0]);
					height = Integer.parseInt(dims[1]);
				} else {
					data += line;
				}
				num++;
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
}
