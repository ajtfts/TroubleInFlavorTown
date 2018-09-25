package towerDefense;

import java.awt.Dimension;
import java.io.*;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MapLoader {
	
	public static final int TILE_SIZE = 64;
	
	private static int mapWidth, mapHeight;
	
	public static void load(String fname, JPanel panel) {
		File f = new File(fname);
		try {
			Scanner sc = new Scanner(f);
			
			int num = 1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (num == 1) {
					String[] dims = line.split("x");
					mapWidth = Integer.parseInt(dims[0]);
					mapHeight = Integer.parseInt(dims[1]);
				}
				num++;
			}
			
			panel.setPreferredSize(new Dimension(mapWidth*TILE_SIZE, mapHeight*TILE_SIZE));
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
			frame.pack();
			
		} catch (FileNotFoundException e) {
			System.out.println("Failed to find map file.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Failed to read map file.");
			e.printStackTrace();
		}
	}
	
}
