package towerDefense;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {
	
	private static BufferedImage[] images;
	
	public static BufferedImage[] load() {
		try {
			images = new BufferedImage[] {
					ImageIO.read(new File("default.png")),
					ImageIO.read(new File("tile_ground.png")),
					ImageIO.read(new File("tile_path.png"))
			};
		} catch (IOException e) {
			System.out.println("Failed to load resources.");
			e.printStackTrace();
		}
		return images;
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
