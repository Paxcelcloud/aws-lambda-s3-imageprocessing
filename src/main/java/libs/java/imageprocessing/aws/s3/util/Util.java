package libs.java.imageprocessing.aws.s3.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import libs.java.imageprocessing.DataSource;
import libs.java.imageprocessing.ImageDataObject;
import libs.java.imageprocessing.ImageDataObject.ProcessedType;
import libs.java.imageprocessing.aws.s3.S3DataSource;

/**
 * Contains some utility methods
 * @author Kuldeep
 *
 */
public class Util {

	public static byte[] getContents(InputStream input) throws IOException {
		return IOUtils.toByteArray(input);
	}

	/**
	 * Uses processedtype to specify destination bucket
	 * @param img image
	 * @param rootBucket root bucket
	 * @return {@link DataSource}
	 */
	public static DataSource findDestinationDataSource(ImageDataObject img, String rootBucket) {
		DataSource source = img.getDataSource();
		String bucket = "";
		String name = source.getName();
		if (img.getProcessedType() == ProcessedType.NEGATIVE) {
			bucket = rootBucket + "-" + "negative";
		}

		if (img.getProcessedType() == ProcessedType.THUMBNAIL) {
			bucket = rootBucket + "-" + "thumbnail";
		}
		if (img.getProcessedType() == ProcessedType.CONVERSION) {
			bucket = rootBucket + "-" + "converted";
		}

		DataSource destination = new S3DataSource(bucket, name);
		return destination;
	}
}
