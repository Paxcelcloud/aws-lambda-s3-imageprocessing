package libs.java.imageprocessing;

import java.io.IOException;
import java.util.List;

import libs.java.imageprocessing.ImageDataObject.ProcessedType;
import libs.java.imageprocessing.processor.ImageProcessorFactory;

/**
 * Basic interface to be implemented by different processor. TO provide new
 * implementation extends this and register in {@link ImageProcessorFactory}
 * implementation. And provide {@link ProcessedType} for same with output bucket
 * mapping in util method. And your processor will start processing for any new
 * object added in S3
 * 
 * @author Kuldeep
 *
 */
public interface ImageProcessor {

	/**
	 * Subclass need to override this method to process image
	 * 
	 * @param source
	 *            - image received
	 * @return - list of processed image object. Implementations are free to
	 *         return single or multiple images
	 * @throws IOException any io exception
	 */
	public List<ImageDataObject> processImage(ImageDataObject source) throws IOException;

}
