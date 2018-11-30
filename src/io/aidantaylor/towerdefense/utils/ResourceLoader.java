package io.aidantaylor.towerdefense.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import io.aidantaylor.towerdefense.gameobject.*;

/**
 * 
 * @author Aidan Taylor
 * @date 11/29/2018
 * @description Loads all the images required for the game and stores them in dictionaries.
 */

public class ResourceLoader {
	
	public static Map<Class<? extends GameObject>, BufferedImage> loadObjectImages() {
		
		Map<Class<? extends GameObject>, BufferedImage> objectImages = new HashMap<Class<? extends GameObject>, BufferedImage>();
		
		try {
			objectImages.put(TomTower.class, ImageIO.read(new File("TomTower.png")));
			objectImages.put(PattyTower.class, ImageIO.read(new File("PattyTower.png")));
			objectImages.put(TowerBullet.class, ImageIO.read(new File("TomBullet.png")));
			objectImages.put(OrangeEnemy.class, ImageIO.read(new File("OrangeEnemy.png")));
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
			tileImages.put('s', ImageIO.read(new File("tile_path_start.png")));
			tileImages.put('e', ImageIO.read(new File("tile_path_end.png")));
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
