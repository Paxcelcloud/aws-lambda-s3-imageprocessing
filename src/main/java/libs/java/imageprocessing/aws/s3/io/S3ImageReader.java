package libs.java.imageprocessing.aws.s3.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import libs.java.imageprocessing.DataObject;
import libs.java.imageprocessing.DataReader;
import libs.java.imageprocessing.DataSource;
import libs.java.imageprocessing.ImageDataObject;
import libs.java.imageprocessing.aws.util.ImageUtil;

/**
 * S3 image reader, leverages {@link S3DataReader} to read byte[] then convert
 * it to image data
 * 
 * @author Kuldeep
 *
 */
public class S3ImageReader implements DataReader<DataObject<BufferedImage>> {

	private S3DataReader reader;

	public S3ImageReader(S3DataReader reader) {
		this.reader = reader;
	}

	public DataObject<BufferedImage> read(DataSource source) throws IOException {
		if (source == null) {
			System.err.println("Data source can't be null");
			return null;
		}
		DataObject<byte[]> data = reader.read(source);
		if (data == null) {
			System.err.println("No data returned from reader");
			return null;
		}
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(data.getData()));
		ImageDataObject imageData = new ImageDataObject(image, source);
		imageData.setMetaData(data.getMetaData());
		imageData.addMetaData("Content-Type", "image");
		imageData.setType(ImageUtil.getImageType(source.getName()));
		return imageData;
	}

}
