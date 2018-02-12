package libs.java.imageprocessing.processor.convertor;

import java.awt.image.BufferedImage;
import java.io.IOException;

import libs.java.imageprocessing.ImageDataObject.ImageType;
import libs.java.imageprocessing.aws.util.ImageUtil;

/**
 * Converts image to JPG
 * 
 * @author Kuldeep
 *
 */
public class JPEGImageConvertor implements ImageConvertor {

	public BufferedImage convert(BufferedImage source) throws IOException {
		return ImageUtil.convert(source, ImageType.JPEG);
	}

	@Override
	public ImageType convertsTo() {
		// TODO Auto-generated method stub
		return ImageType.JPEG;
	}

}
