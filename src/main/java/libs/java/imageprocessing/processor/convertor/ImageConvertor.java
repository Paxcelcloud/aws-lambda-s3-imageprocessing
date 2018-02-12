package libs.java.imageprocessing.processor.convertor;

import java.awt.image.BufferedImage;
import java.io.IOException;

import libs.java.imageprocessing.ImageDataObject.ImageType;

/**
 * Interface to be implemented by convertor
 * @author Kuldeep
 *
 */
public interface ImageConvertor {

	public ImageType convertsTo (); 
	public BufferedImage convert(BufferedImage source) throws IOException;
}
