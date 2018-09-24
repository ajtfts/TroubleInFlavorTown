package towerDefense;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {
	
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
