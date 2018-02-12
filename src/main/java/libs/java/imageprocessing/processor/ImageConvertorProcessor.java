package libs.java.imageprocessing.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import libs.java.imageprocessing.DataSource;
import libs.java.imageprocessing.ImageDataObject;
import libs.java.imageprocessing.ImageProcessor;
import libs.java.imageprocessing.ImageDataObject.ImageType;
import libs.java.imageprocessing.ImageDataObject.ProcessedType;
import libs.java.imageprocessing.aws.s3.S3DataSource;
import libs.java.imageprocessing.processor.convertor.ImageConvertor;

/**
 * Processor which delegates image conversion to different convertors.
 * Basically, It converts image to missing types.image supported are jpg, png,
 * and bmp
 * 
 * @author Kuldeep
 *
 */
public class ImageConvertorProcessor implements ImageProcessor {

	private Map<ImageType, ImageConvertor> imageConvertor = new HashMap<ImageType, ImageConvertor>();

	@Override
	public List<ImageDataObject> processImage(ImageDataObject source) throws IOException {
		if (source == null || source.getData() == null || source.getType() == null
				|| source.getType() == ImageType.INVALID) {
			System.err.println("Invalid input to convert image. " + source);
		}

		List<ImageConvertor> convertors = getConvertors(source.getType());
		List<ImageDataObject> convertedImages = new ArrayList<ImageDataObject>();
		for (ImageConvertor convertor : convertors) {
			DataSource newSource = new S3DataSource(source.getDataSource().getPath(),
					source.getDataSource().getName().substring(0, source.getDataSource().getName().indexOf(".")));
			ImageDataObject converted = new ImageDataObject(convertor.convert(source.getData()), newSource);

			converted.setType(convertor.convertsTo());
			converted.setProcessedType(ProcessedType.CONVERSION);

			newSource.setName(newSource.getName() + "." + converted.getType().name().toLowerCase());
			convertedImages.add(converted);
		}
		return convertedImages;
	}

	// expecting, no dependency on source type for now
	public void addImageConvertor(ImageType convertTo, ImageConvertor convertor) {
		imageConvertor.put(convertTo, convertor);
	}

	/**
	 * Returns a list of convertor, skip source type convertor
	 * 
	 * @param source
	 * @return
	 */
	private List<ImageConvertor> getConvertors(ImageType source) {
		List<ImageConvertor> expectedConvertors = new ArrayList<ImageConvertor>();
		for (ImageType key : imageConvertor.keySet()) {
			if (key == source) {
				continue;
			}
			expectedConvertors.add(imageConvertor.get(key));

		}
		return expectedConvertors;
	}

}
