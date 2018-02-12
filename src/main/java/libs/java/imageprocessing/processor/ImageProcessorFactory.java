package libs.java.imageprocessing.processor;

import java.util.ArrayList;
import java.util.List;

import libs.java.imageprocessing.ImageProcessor;

/**
 * Factory where different image processors can be added. All registered
 * processors are called in sequence of addition to process the uploaded image
 * 
 * Currently 3 processors are provided - {@link ImageConvertorProcessor},
 * {@link NegativeImageProcessor}, {@link ThumbnailImageProcessor}
 * 
 * @author Kuldeep
 *
 */
public class ImageProcessorFactory {

	private static final ImageProcessorFactory factory = new ImageProcessorFactory();

	public static final ImageProcessorFactory instance() {
		return factory;
	}

	private List<ImageProcessor> processors = new ArrayList<ImageProcessor>();

	public List<ImageProcessor> getImageProcessors() {
		return processors;
	}

	public void addProcessor(ImageProcessor processor) {
		if (processor != null) {
			processors.add(processor);
		}
	}

	public void removeProcessor(ImageProcessor processor) {
		if (processor != null) {
			processors.remove(processor);
		}
	}
}
