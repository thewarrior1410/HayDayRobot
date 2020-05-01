package haydayclient.main;

import haydayclient.util.Coordinates;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Main {

	public static void main(String[] bars) {
		
		Robot r;
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			return;
		}
		
		r.delay(5000);
		
		BufferedImage bImg = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		
		ArrayList<Coordinates> s = findWheat(bImg);
		System.out.println("Gefundene Wheat Coords");
		printCoordArray(s);
		
	}
	
	
	public static ArrayList<Coordinates> findColoredPixels(BufferedImage bImg, Color c) {
	
		ArrayList<Coordinates> a = new ArrayList<Coordinates>();
		int height = bImg.getHeight();
		System.out.println("height"+height);
		int width = bImg.getWidth();
		System.out.println("width"+width);
		
		for (int x = 0; x < width; x++) {
			
			for (int y = 0; y < height; y++) {
				
				Color bcolor = new Color(bImg.getRGB(x, y));
				//System.out.println(bcolor.toString());
				
				if (closeTo(bcolor, c)) {
					a.add(new Coordinates(x, y));
				}
				
			}
			
		}
		
		System.out.println("Vergleich Color: "+c.toString());
		
		return a;
		
		
	}
	
	public static ArrayList<Coordinates> findWheat(BufferedImage bImg) {
		
		Color c1 = new Color(254, 214, 21);
		Color c2 = new Color(246, 216, 15);
		Color c3 = new Color(244, 215, 12);
		
		
		ArrayList<Coordinates> cFound1 = findColoredPixels(bImg, c1);
		ArrayList<Coordinates> cFound2 = new ArrayList<Coordinates>();
		ArrayList<Coordinates> cFound3 = new ArrayList<Coordinates>();
		
		System.out.println("found1");
		printCoordArray(cFound1);
		
		//coord.getX()+2 darf nicht größer als Bildschirm sein !!!!!!
		for (Coordinates coords : cFound1) {
			Color bcolor = new Color(bImg.getRGB(coords.getX()+1, coords.getY()));
			if (closeTo(bcolor, c2)) {
				cFound2.add(coords);
			}
		}
		
		System.out.println("found2");
		printCoordArray(cFound2);
		
		for (Coordinates coords : cFound2) {
			Color bcolor = new Color(bImg.getRGB(coords.getX()+2, coords.getY()));
			if (closeTo(bcolor, c3)) {
				cFound3.add(coords);
			}
		}
		
		System.out.println("found3");
		printCoordArray(cFound3);
		
		return cFound3;
	}
	
	public static void printCoordArray(ArrayList<Coordinates> a) {
		for(Coordinates c : a) {
			System.out.println("x: "+c.getX()+", y: "+c.getY());
		}
	}

	public static boolean closeTo(Color c1, Color c2) {
		
		float[] c1RGB = new float[3];
		float[] c2RGB = new float[3];
		
		c1RGB[0] = c1.getRed();
		c2RGB[0] = c2.getRed();
		c1RGB[1] = c1.getGreen();
		c2RGB[1] = c2.getGreen();
		c1RGB[2] = c1.getBlue();
		c2RGB[2] = c2.getBlue();
		
		for (int i = 0; i < c1RGB.length; i++) {
			
			if (c1RGB[i]-10 < c2RGB[i] && c2RGB[i] < c1RGB[i]+10) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
}
