package io.aidantaylor.towerdefense.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import io.aidantaylor.towerdefense.gameobject.*;

public class ResourceLoader {
	
	public static Map<Class<? extends Tower>, BufferedImage> loadObjectImages() {
		
		Map<Class<? extends Tower>, BufferedImage> objectImages = new HashMap<Class<? extends Tower>, BufferedImage>();
		
		try {
			objectImages.put(TomTower.class, ImageIO.read(new File("TomTower.png")));
			objectImages.put(PattyTower.class, ImageIO.read(new File("PattyTower.png")));
		} catch (IOException e) {
			System.out.println("Failed to load resources.");
			e.printStackTrace();
		}
		
		return objectImages;
		
	}
	
	public static Map<Character, BufferedImage> loadTileImages() {

		Map<Character, BufferedImage> tileImages = new HashMap<Character, BufferedImage>();
		
		try {
			tileImages.put('g', ImageIO.read(new File("tile_ground.png")));
			tileImages.put('p', ImageIO.read(new File("tile_path.png")));
		} catch (IOException e) {
			System.out.println("Failed to load resources.");
			e.printStackTrace();
		}
		return tileImages;
	}
 	
	public static BufferedImage load(String fname) {
		try {
			return ImageIO.read(new File(fname));
		} catch (IOException e) {
			System.out.println("Failed to load resource: "+fname);
			e.printStackTrace();
			return new BufferedImage(0, 0, 0);
		}
	}
	
}
