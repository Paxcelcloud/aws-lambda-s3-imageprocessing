package libs.java.imageprocessing.processor;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import libs.java.imageprocessing.ImageDataObject;
import libs.java.imageprocessing.ImageProcessor;
import libs.java.imageprocessing.ImageDataObject.ImageType;
import libs.java.imageprocessing.ImageDataObject.ProcessedType;

/**
 * Image processor which creates negative of passed image
 * 
 * @author Kuldeep
 *
 */
public class NegativeImageProcessor implements ImageProcessor {

	public List<ImageDataObject> processImage(ImageDataObject source) throws IOException {
		if (source == null || source.getData() == null || source.getType() == null
				|| source.getType() == ImageType.INVALID) {
			System.err.println("Invalid image to create negative. " + source);
			return null;
		}
		try {
			// BufferedImage image = ImageIO.read(new
			// ByteArrayInputStream(source.getData()));
			ColorModel cm = source.getData().getColorModel();
			boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
			WritableRaster raster = source.getData().copyData(null);
			BufferedImage image = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
			// copy to new image data before operation
			int height = image.getHeight();
			int width = image.getWidth();
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int p = image.getRGB(x, y);
					int a = (p >> 24) & 0xff;
					int r = (p >> 16) & 0xff;
					int g = (p >> 8) & 0xff;
					int b = p & 0xff;
					// subtract RGB from 255
					r = 255 - r;
					g = 255 - g;
					b = 255 - b;
					// set new RGB value
					p = (a << 24) | (r << 16) | (g << 8) | b;
					image.setRGB(x, y, p);
				}
			}
			ImageDataObject outputObject = new ImageDataObject(image, source.getDataSource());
			outputObject.setType(source.getType());
			outputObject.setProcessedType(ProcessedType.NEGATIVE);
			return Arrays.asList(outputObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
