package libs.java.imageprocessing.aws.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import libs.java.imageprocessing.AbstractDataObject;
import libs.java.imageprocessing.DataObject;
import libs.java.imageprocessing.ImageDataObject.ImageType;

/**
 * Image specific utility
 * 
 * @author Kuldeep
 *
 */
public class ImageUtil {

	/**
	 * returns image type according to file extension
	 * 
	 * @param file
	 *            file name
	 * @return {@link ImageType} accoring to extension
	 */
	public static ImageType getImageType(String file) {
		if (file == null) {
			return null;
		}
		if (file.toLowerCase().endsWith(".jpg") || file.toLowerCase().endsWith(".jpeg")) {
			return ImageType.JPEG;
		}
		if (file.toLowerCase().endsWith("bmp")) {
			return ImageType.BMP;
		}

		if (file.toLowerCase().endsWith("png")) {
			return ImageType.PNG;
		}
		return ImageType.INVALID;
	}

	/**
	 * Converts image to destination type
	 * 
	 * @param source
	 *            image
	 * @param destination
	 *            destination type to convert to
	 * @return converted image
	 * @throws IOException
	 *             any exception
	 */
	public static BufferedImage convert(BufferedImage source, ImageType destination) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageOutputStream output = ImageIO.createImageOutputStream(outputStream);

		// Default way of conversion
		ImageIO.write(source, destination.value(), output);

		// read back
		return ImageIO.read(new ByteArrayInputStream(outputStream.toByteArray()));
	}

}
