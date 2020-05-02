package haydayclient.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class SearchableImage extends BufferedImage implements ISearchable{

	/*
	public SearchableImage(BufferedImage bImg) {
		super(bImg.getWidth(), bImg.getHeight(), bImg.getType());
	}
	*/
	
	public SearchableImage(BufferedImage bImg) {
		super(bImg.getColorModel(), bImg.getRaster(), bImg.isAlphaPremultiplied(), buffImg_properties(bImg));
	}
	
	public static Hashtable<String, Object> buffImg_properties(BufferedImage bImg) {
		Hashtable<String, Object> b = new Hashtable<String, Object>();
		if (bImg.getPropertyNames() != null) {
			for (String s : bImg.getPropertyNames()) {
				b.put(s, bImg.getProperty(s));
			}
		}
		return b;
	}
	
	
	public SearchableImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied,
			Hashtable<?, ?> properties) {
		super(cm, raster, isRasterPremultiplied, properties);
	}
	
	public SearchableImage(int width, int height, int imageType) {
		super(width, height, imageType);
	}
	
	public SearchableImage(int width, int height, int imageType, IndexColorModel cm) {
		super(width, height, imageType, cm);
	}
	

}
