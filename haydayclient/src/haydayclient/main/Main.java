package haydayclient.main;

import haydayclient.util.Coordinates;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
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
		
		BufferedImage bImg = r.createScreenCapture(new Rectangle(0,0,500,500));
		
		ArrayList<Coordinates> s = findColoredPixels(bImg, new Color(47, 47, 47));
		//System.out.println(s.toString());
		
		for(Coordinates c : s) {
			System.out.println("x: "+c.getX()+", y: "+c.getY());
		}
		
		
	}
	
	
	public static ArrayList<Coordinates> findColoredPixels(BufferedImage bImg, Color c) {
	
		ArrayList<Coordinates> a = new ArrayList<Coordinates>();
		int height = bImg.getHeight();
		int width = bImg.getWidth();
		
		for (int x = 0; x < height; x++) {
			
			for (int y = 0; y < width; y++) {
				
				Color bcolor = new Color(bImg.getRGB(x, y));
				System.out.println(bcolor.toString());
				
				if (bcolor.equals(c)) {
					a.add(new Coordinates(x, y));
				}
				
			}
			
		}
		
		System.out.println("Vergleich Color: "+c.toString());
		
		return a;
		
		
	}
	
}
