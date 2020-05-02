package haydayclient.main;

import haydayclient.util.Coordinates;
import haydayclient.util.SearchableImage;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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
		
		SearchableImage sImg = new SearchableImage(bImg);
		
		
		BufferedImage wheatRef;
		try {
			wheatRef = ImageIO.read(new File("C:\\Users\\user\\Desktop\\Informatik\\Programmieren\\Java\\Robot\\HayDay\\Wheat.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Import failed!");
			return;
		}
		
		
		Coordinates s = findWheat(bImg, wheatRef);
		//Coordinates c = sImg.findSubimage(wheatRef);
		
		System.out.println("Gefundene Wheat Coords");
		
		System.out.println(s.toString());
		
		//printCoordArray(s);
		
	}
	
	
	public static ArrayList<Coordinates> findColoredPixels(BufferedImage bImg, Color c) {
	
		ArrayList<Coordinates> a = new ArrayList<Coordinates>();
		int height = bImg.getHeight();
		System.out.println("height"+height);
		int width = bImg.getWidth();
		System.out.println("width"+width);
		
		for (int x = 0; x < width; x++) {
			
			for (int y = 0; y < height; y++) {
				
				//Color bcolor = new Color(bImg.getRGB(x, y));
				//System.out.println(bcolor.toString());
				
				if (closeTo(bImg.getRGB(x, y), c.hashCode())) {
					a.add(new Coordinates(x, y));
				}
				
			}
			
		}
		
		System.out.println("Vergleich Color: "+c.toString());
		
		return a;
		
		
	}
	
	
	
	
	public static Coordinates findWheat(BufferedImage bImg, BufferedImage ref) {
		
		/*
		Color c1 = new Color(254, 214, 21);
		Color c2 = new Color(246, 216, 15);
		Color c3 = new Color(244, 215, 12);
		
		
		ArrayList<Coordinates> cFound1 = findColoredPixels(bImg, c1);
		ArrayList<Coordinates> cFound2 = new ArrayList<Coordinates>();
		ArrayList<Coordinates> cFound3 = new ArrayList<Coordinates>();
		
		System.out.println("found1");
		printCoordArray(cFound1);
		*/
		
		//coord.getX()+2 darf nicht größer als Bildschirm sein !!!!!!
		
		/*
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
		*/
		
		
		
		int height = bImg.getHeight();
		System.out.println("height"+height);
		int width = bImg.getWidth();
		System.out.println("width"+width);
		
		boolean break_loop = false;
		
		for (int x = 0; x < width; x++) {
			
			for (int y = 0; y < height; y++) {
				
				// Color bcolor = new Color(bImg.getRGB(x, y));
				//System.out.println(bcolor.toString());
				
				for (int xRef = 0; xRef < ref.getWidth(); xRef++) {
					
					for (int yRef = 0; yRef < ref.getHeight(); yRef++) {
						
						System.out.println(new Coordinates(x, y).toString());
						
						if (x+xRef>=width && y+yRef>=height) {
							break_loop = true;
							break;
						}
						
						if (closeTo(bImg.getRGB(x+xRef, y+yRef), ref.getRGB(xRef, yRef))) {
							continue;
						}else {
							break_loop = true;
							break;
						}
						
					}
					if (break_loop) {
						break;
					}
					
				}
				if (break_loop) {
					break_loop = false;
					continue;
				} else {
					//success on all ref pixels
					return new Coordinates(x, y);
				}
				
				
			}
			
		}
		
		return null;
		
		
	}
	
	
	public static void printCoordArray(ArrayList<Coordinates> a) {
		for(Coordinates c : a) {
			System.out.println("x: "+c.getX()+", y: "+c.getY());
		}
	}

	
	public static boolean closeTo(int c1, int c2) {
		
		double r1 = ((c1 >> 16) & 0xFF)/255.0; double r2 = ((c2 >> 16) & 0xFF)/255.0;
		double g1 = ((c1 >> 8) & 0xFF)/255.0;  double g2 = ((c2 >> 8) & 0xFF)/255.0;
		double b1 = (c1 & 0xFF)/255.0;         double b2 = (c2 & 0xFF)/255.0;
		
		double abweichung = Math.sqrt(Math.pow((r2-r1), 2)+Math.pow((g2-g1), 2)+Math.pow((b2-b1), 2))/Math.sqrt(3);
		
		if (abweichung<0.5) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
