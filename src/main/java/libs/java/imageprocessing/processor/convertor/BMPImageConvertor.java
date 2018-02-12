package libs.java.imageprocessing.processor.convertor;

import java.awt.image.BufferedImage;
import java.io.IOException;

import libs.java.imageprocessing.ImageDataObject.ImageType;
import libs.java.imageprocessing.aws.util.ImageUtil;

/**
 * Converts image to bmp
 * @author Kuldeep
 *
 */
public class BMPImageConvertor implements ImageConvertor {

	public BufferedImage convert(BufferedImage source) throws IOException {
		return ImageUtil.convert(source, ImageType.BMP);
	}

	@Override
	public ImageType convertsTo() {
		
		return ImageType.BMP;
	}

}
