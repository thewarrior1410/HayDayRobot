package haydayclient.main;

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
		
		ArrayList<String> s = findColoredPixels(bImg, new Color(0x000000));
		System.out.println(s.toString());
		
		
	}
	
	
	public static ArrayList<String> findColoredPixels(BufferedImage bImg, Color c) {
	
		ArrayList<String> a = new ArrayList<String>();
		int height = bImg.getHeight();
		int width = bImg.getWidth();
		
		for (int x = 0; x < height; x++) {
			
			for (int y = 0; y < width; y++) {
				
				if (new Color(bImg.getRGB(x, y)) == c) {
					a.add(x+", "+y);
				}
				
			}
			
		}
		
		return a;
		
		
	}
	
}
