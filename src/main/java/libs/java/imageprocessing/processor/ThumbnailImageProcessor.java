package libs.java.imageprocessing.processor;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

import libs.java.imageprocessing.ImageDataObject;
import libs.java.imageprocessing.ImageProcessor;
import libs.java.imageprocessing.ImageDataObject.ImageType;
import libs.java.imageprocessing.ImageDataObject.ProcessedType;

/**
 * Processor which creates thumbnail of size 80-80 of passed image
 * @author Kuldeep
 *
 */
public class ThumbnailImageProcessor implements ImageProcessor {

	public List<ImageDataObject> processImage(ImageDataObject source) {
		if (source == null || source.getData() == null || source.getType() == null
				|| source.getType() == ImageType.INVALID) {
			System.err.println("Invalid image to create thumbnail. " + source);
		}
		BufferedImage thumbImg = Scalr.resize(source.getData(), Method.QUALITY, Mode.AUTOMATIC, 80, 80,
				Scalr.OP_ANTIALIAS);

		ImageDataObject outputObject = new ImageDataObject(thumbImg, source.getDataSource());
		outputObject.setType(source.getType());
		outputObject.setProcessedType(ProcessedType.THUMBNAIL);
		return Arrays.asList(outputObject);

	}

}
