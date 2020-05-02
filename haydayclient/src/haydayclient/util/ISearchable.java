package haydayclient.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

public interface ISearchable {

	/**
	 * Finds the a region in one image that best matches another, smaller, image.
	 */
	public default Coordinates findSubimage(BufferedImage im2) {
		
		System.out.println("searching for subImg");
		
		SearchableImage im1;
		try {
			im1 = (SearchableImage) this;
		} 
		catch (Exception ex) {
			System.out.println("Error Converting To BufferedImage");
			System.out.println(ex.toString());
			return null;
		}
		
		int w1 = im1.getWidth();
		int h1 = im1.getHeight();
		int w2 = im2.getWidth();
		int h2 = im2.getHeight();
		
		// im1 must be bigger than im2 (sub_img)
		assert (w2 <= w1 && h2 <= h1);
		
		// will keep track of best position found
		Coordinates bestC = new Coordinates(0, 0);
		
		double lowestDiff = Double.POSITIVE_INFINITY;
		// brute-force search through whole image (slow...)
		for (int x = 0; x < w1 - w2; x++) {
			for (int y = 0; y < h1 - h2; y++) {
				System.out.println("x: "+x+", y: "+y);
				double comp = compareImages(im1.getSubimage(x, y, w2, h2), im2);
				if (comp < lowestDiff) {
					bestC = new Coordinates(x, y);
					lowestDiff = comp;
				}
			}
		}
		// output similarity measure from 0 to 1, with 0 being identical
		System.out.println(lowestDiff);
		// return best location
		return bestC;
	}

	/**
	 * Determines how different two identically sized regions are.
	 */
	public static double compareImages(BufferedImage im1, BufferedImage im2) {
		assert (im1.getHeight() == im2.getHeight() && im1.getWidth() == im2.getWidth());
		double abweichung = 0.0;
		for (int x = 0; x < im1.getWidth(); x++) {
			for (int y = 0; y < im1.getHeight(); y++) {
				abweichung += compareRGB(im1.getRGB(x, y), im2.getRGB(x, y)) / Math.sqrt(3);
			}
		}
		return abweichung / (im1.getWidth() * im1.getHeight());
	}

	/**
	 * Calculates the difference between two ARGB colors
	 * (BufferedImage.TYPE_INT_ARGB).
	 */
	public static double compareRGB(int rgb1, int rgb2) {
		Color c1 = new Color(rgb1);
		Color c2 = new Color(rgb2);
		return Math.sqrt( Math.pow(c1.getRed() - c2.getRed(), 2) + Math.pow(c1.getGreen() - c2.getGreen(), 2) + Math.pow(c1.getBlue() - c2.getBlue(), 2));
	}

}
