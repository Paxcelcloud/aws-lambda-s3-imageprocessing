package libs.java.imageprocessing;

import libs.java.imageprocessing.ImageDataObject.ImageType;
import libs.java.imageprocessing.io.IOFactory;
import libs.java.imageprocessing.processor.ImageConvertorProcessor;
import libs.java.imageprocessing.processor.ImageProcessorFactory;
import libs.java.imageprocessing.processor.NegativeImageProcessor;
import libs.java.imageprocessing.processor.ThumbnailImageProcessor;
import libs.java.imageprocessing.processor.convertor.BMPImageConvertor;
import libs.java.imageprocessing.processor.convertor.JPEGImageConvertor;
import libs.java.imageprocessing.processor.convertor.PNGImageConvertor;

/**
 * Used to initialize/setup application. Create and register different
 * processors and initialize IO operators
 * 
 * @author Kuldeep
 *
 */
public class AppInitializer {

	private final static AppInitializer instance = new AppInitializer();

	public static final AppInitializer instance() {
		return instance;
	}

	public AppInitializer() {
	}

	/**
	 * Initializes processor and registers with factory. Initialzes IO factory as well
	 */
	public void initialize() {
		ImageProcessorFactory.instance().addProcessor(new NegativeImageProcessor());
		ImageProcessorFactory.instance().addProcessor(new ThumbnailImageProcessor());
		ImageConvertorProcessor convertor = new ImageConvertorProcessor();
		convertor.addImageConvertor(ImageType.BMP, new BMPImageConvertor());
		convertor.addImageConvertor(ImageType.JPEG, new JPEGImageConvertor());
		convertor.addImageConvertor(ImageType.PNG, new PNGImageConvertor());
		ImageProcessorFactory.instance().addProcessor(convertor);
		IOFactory.getDefaultFactory(); // init only

	}
}
